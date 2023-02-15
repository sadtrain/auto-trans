package com.sadtrain.autotrans.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfiguration {


    public static Boolean loginBot = false;

    @Value("${loginbot}")
    public void setLoginBot(Boolean loginBot) {
        BotConfiguration.loginBot = loginBot;
    }
}
