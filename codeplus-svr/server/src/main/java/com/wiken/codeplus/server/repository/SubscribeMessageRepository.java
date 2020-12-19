package com.wiken.codeplus.server.repository;

import com.wiken.codeplus.server.entity.SubscribeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * by hehuapei
 * Time 2020/12/10 11:10 下午
 */
public interface SubscribeMessageRepository extends JpaRepository<SubscribeMessage, Long>, JpaSpecificationExecutor {

    SubscribeMessage findByTemplateIdAndWeChatOpenid(String templateId, String openId);

    List<SubscribeMessage> findByTemplateId(String templateId);
}
