package com.wiken.codeplus.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * by wiken
 * Time 2019-09-20 11:28
 */
@SpringBootApplication
@EnableScheduling
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}

