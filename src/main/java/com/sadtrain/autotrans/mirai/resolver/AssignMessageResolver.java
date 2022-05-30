package com.sadtrain.autotrans.mirai.resolver;

import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.SingleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AssignMessageResolver implements MessageResolver{
    private static Logger logger = LoggerFactory.getLogger(AssignMessageResolver.class);
    @Autowired
    private TKLConvertor tklConvertor;

    public MessageChain resolve(MessageEvent event) {
        MessageChain messageChain = event.getMessage();
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        for (SingleMessage message : messageChain) {
            if (message instanceof PlainText) {
                String content = ((PlainText) message).getContent();
                Pattern pattern = Pattern.compile("[\\s\\S]*(￥.*￥)[\\s\\S]*");
                Matcher matcher = pattern.matcher(content);
                if (matcher.matches()) {
                    String tkl = matcher.group(1);
//                        System.out.println(matcher.group(1));
                    String myTKL = tklConvertor.convert(tkl);
                    assert myTKL != null;
                    content = content.replaceAll(tkl, myTKL);
                }
                newMassageBuilder.append(new PlainText(content));
            } else if (message instanceof Image) {
                newMassageBuilder.append(message);
            } else if (message instanceof AtAll){
                newMassageBuilder.append(message);
            }
        }
        MessageChain newMessage = newMassageBuilder.build();
        return newMessage;

    }

}
