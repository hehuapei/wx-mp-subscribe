package com.wiken.codeplus.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wiken.codeplus.server.entity.SubscribeMessage;
import com.wiken.codeplus.server.repository.SubscribeMessageRepository;
import com.wiken.codeplus.server.service.MpService;
import jodd.http.HttpRequest;
import jodd.util.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by hehuapei
 * Time 2020/12/10 10:43 下午
 */

@Service
public class MpServiceImpl implements MpService {

    @Autowired
    private SubscribeMessageRepository subscribeMessageRepository;
    @Value("${wx.mp.appid}")
    private String appid;
    @Value("${wx.mp.secret}")
    private String secret;
    @Value("${wx.api.host}")
    private String host;

    /**
     * 增加订阅次数
     * @param templateId
     */
    @Override
    public void subscribe(String templateId, String code) {

        //根据code获取用户openId
        String openId = this.getOpenId(code);

        //查找数据库中是否有该用户的记录
        SubscribeMessage subscribeMessage = subscribeMessageRepository.findByTemplateIdAndWeChatOpenid(templateId, openId);
        //如果没有则新建用户
        if(subscribeMessage == null){
            subscribeMessage = new SubscribeMessage();
            subscribeMessage.setCreateTime(System.currentTimeMillis());
            subscribeMessage.setTemplateId(templateId);
            subscribeMessage.setWeChatOpenid(openId);
            subscribeMessage.setTotal(0);
        }
        //订阅次数+1
        subscribeMessage.setTotal(subscribeMessage.getTotal() + 1);
        //保存到数据库
        subscribeMessageRepository.save(subscribeMessage);
    }

    /**
     * 每天中午11点,下午5点,给订阅的用户推送消息
     */
    @Override
    @Scheduled(cron = "0 0 11,17 * * ?")
    public void send(){
        //要发送的消息模板ID
        String tempId = "P3_l-4yF_8uDikq_xxxx";
        //查找数据库中所有订阅了该模板的用户(若用户量大[1w以上],此处有oom风险,请自行处理)
        List<SubscribeMessage> subscribeMessages = subscribeMessageRepository.findByTemplateId(tempId);
        //订阅用户为空则不往下执行
        if(subscribeMessages == null || subscribeMessages.isEmpty()){
            return;
        }

        //获取微信api调用token
        String token = this.getToken();

        //遍历订阅用户,逐一推送消息
        for (SubscribeMessage subscribeMessage: subscribeMessages){

            //如果订阅次数为0则不给该用户推送
            if(subscribeMessage.getTotal() < 1){
                continue;
            }

            //构建消息实体
            JSONObject object = new JSONObject();
            //接收用户的openid
            object.put("touser", subscribeMessage.getWeChatOpenid());
            //消息模板id(从小程序后台获取)
            object.put("template_id", subscribeMessage.getTemplateId());
            //点击消息后打开的小程序页面
            object.put("page", "/pages/index/index");

            //data参数(参数个数和类型请在小城市后台消息模板中查看)
            JSONObject data = new JSONObject();
            //参数1
            JSONObject thing1 = new JSONObject();
            thing1.put("value","你的隐藏大额外卖红包已生成");
            data.put("thing1",thing1);
            //参数2
            JSONObject thing4 = new JSONObject();
            thing4.put("value","快来领取吧！\n工作再忙，也要按时吃饭哦！");
            data.put("thing4",thing4);

            object.put("data", data);

            //推送消息
            this.sendMessage(token, object.toJSONString());
            //数据库中的订阅次数-1
            subscribeMessage.setTotal(subscribeMessage.getTotal() - 1);
            subscribeMessageRepository.save(subscribeMessage);
        }
    }

    /**
     * 发送消息
     * @param token
     * @param body
     */
    private void sendMessage(String token, String body){
        String url = this.host + "/cgi-bin/message/subscribe/send?access_token=" + token;
        HttpRequest request = HttpRequest.post(url);
        if (body != null) {
            request.bodyText(body);
        }
        request.contentType("application/json", StringPool.UTF_8);
        jodd.http.HttpResponse httpResponse = request.send();
        httpResponse.charset(StringPool.UTF_8);
        String s = httpResponse.bodyText();
        System.out.println(s);
    }

    /**
     * 获取微信api凭证
     * @return
     */
    private String getToken(){
        String url = this.host + "/cgi-bin/token?grant_type=client_credential&appid=" + this.appid + "&secret=" + this.secret;
        HttpRequest req = HttpRequest.get(url);
        jodd.http.HttpResponse resp = req.send();
        resp.charset(StringPool.UTF_8);
        String respBody = resp.bodyText();
        JSONObject object = JSON.parseObject(respBody);
        return object.getString("access_token");
    }

    /**
     * 获取用户openId
     * @param code
     * @return
     */
    private String getOpenId(String code){
        String url = this.host + "/sns/jscode2session?appid=" + this.appid +"&secret=" + this.secret + "&js_code="+code+"&grant_type=authorization_code";
        HttpRequest req = HttpRequest.get(url);
        jodd.http.HttpResponse resp = req.send();
        resp.charset(StringPool.UTF_8);
        String respBody = resp.bodyText();
        JSONObject object = JSON.parseObject(respBody);
        return object.getString("openid");
    }

}
