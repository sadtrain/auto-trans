package com.sadtrain.autotrans.kafka;

import com.alias.common.enums.MessageTypeEnum;
import com.alias.common.message.Message;
import com.alias.common.message.MessageEntity;
import com.alias.common.message.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class KafkaUtil {

    public void sendMessage(){

    }

    public static void main(String[] args) {
        System.setProperty("console.encoding", "UTF-8");
        String property = System.getProperty("os.name");
        if (property.contains("Windows")) {
            System.setProperty("osbriefname", "windows");
        } else if (property.contains("Mac")) {
            System.setProperty("osbriefname", "mac");
        } else {
            System.setProperty("osbriefname", "default");
        }
        List<Message> messages = new ArrayList<>();
        TextMessage message = new TextMessage();
        message.setType(MessageTypeEnum.TEXT);
        message.setText("test");
        messages.add(message);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageList(messages);
        MessageCenter.sendMessage(messageEntity);
    }
}
