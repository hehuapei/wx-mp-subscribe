package com.wiken.codeplus.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 订阅消息表
 */
@Entity
@Table(name = "subscribe_message")
public class SubscribeMessage implements Serializable {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "identity")
    private Long id; //id

    private String templateId; //消息模板id

    private String weChatOpenid; //微信用户openid

    private long total; //订阅次数

    private Long createTime; //创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getWeChatOpenid() {
        return weChatOpenid;
    }

    public void setWeChatOpenid(String weChatOpenid) {
        this.weChatOpenid = weChatOpenid;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
