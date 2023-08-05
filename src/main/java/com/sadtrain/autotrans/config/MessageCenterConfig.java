package com.sadtrain.autotrans.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageCenterConfig {

    public static String url = "http://localhost:17632";

    @Value("${message_center.url}")
    public void setUrl(String url) {
        MessageCenterConfig.url = url;
    }
}
