package com.wiken.codeplus.server.service;

/**
 * by hehuapei
 * Time 2020/12/10 10:43 下午
 */

public interface MpService {

    void subscribe(String templateId, String code);

    void send();
}
