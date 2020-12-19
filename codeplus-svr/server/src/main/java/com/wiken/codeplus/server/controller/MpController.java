package com.wiken.codeplus.server.controller;

import com.wiken.codeplus.server.service.MpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * by hehuapei
 * Time 2019-09-22 19:40
 */
@RestController
public class MpController {

    @Autowired
    private MpService mpService;

    //记录订阅次数
    @GetMapping("/subscribe")
    public void subscribe(@RequestParam String templateId, @RequestParam String code){
        mpService.subscribe(templateId, code);
    }

    //手动触发推送消息
//    @GetMapping("/send")
//    public void send(){
//        mpService.send();
//    }
}
