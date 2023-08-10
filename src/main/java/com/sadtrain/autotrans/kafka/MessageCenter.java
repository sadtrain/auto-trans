package com.sadtrain.autotrans.kafka;

import com.alias.common.message.Message;
import com.alias.common.message.MessageEntity;
import com.alias.common.message.TextMessage;
import com.alias.util.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.config.MessageCenterConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageCenter {

    private static final String TEXT_URL = "/msg/sendTextMsg?topic=";
    private static final String IMAGE_URL = "/msg/sendImgMsg?topic=";
    private static final String topic = "mirai_qq_msg";

    public static void sendMessage(MessageEntity message){
        log.info("send message to kafka: {}", message);

        for (Message message1 : message.getMessageList()) {
            if (message1 instanceof TextMessage){
                HttpUtil.post(MessageCenterConfig.url + TEXT_URL + topic, JSON.toJSONString(message1));
            } else {
                HttpUtil.post(MessageCenterConfig.url + IMAGE_URL + topic, JSON.toJSONString(message1));
            }
        }
    }
}
