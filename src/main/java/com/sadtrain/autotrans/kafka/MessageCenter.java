package com.sadtrain.autotrans.kafka;

import com.alias.common.message.Message;
import com.alias.common.message.MessageEntity;
import com.alias.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.config.MessageCenterConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageCenter {

    private static final String URL = "/sendMsg?topic=";

    private static final String topic = "mirai_qq_msg";
    public static void sendMessage(MessageEntity message){
        log.info("send message to kafka: {}", message);

        HttpUtil.post(MessageCenterConfig.url + URL + topic, JSON.toJSONString(message));
    }
}
