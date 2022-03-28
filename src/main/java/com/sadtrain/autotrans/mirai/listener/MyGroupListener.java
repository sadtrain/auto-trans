package com.sadtrain.autotrans.mirai.listener;

import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.life.LifeCycle;
import com.sadtrain.autotrans.mirai.bots.MyBot;
import com.sadtrain.autotrans.mirai.resolver.AssignMessageResolver;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
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
public class MyGroupListener implements LifeCycle {

    private static Logger logger = LoggerFactory.getLogger(LJLGroupListener.class);

    @Autowired
    private MyBot myBot;

    @Autowired
    private TKLConvertor tklConvertor;

    @Autowired
    private AssignMessageResolver assignMessageResolver;

    Group sqkbGroup;

    Group myGroup;

    public static final Long sqxbGroupId = 639164037L;

    public static final Long myGroupId = 197315399L;


    @Override
    public void start() {
        for (Group group : myBot.myBot.getGroups()) {
            if(group.getId() == myGroupId){
                myGroup = group;
                continue;
            }
            if(group.getId() == sqxbGroupId){
                sqkbGroup = group;
            }
        }
        EventChannel<BotEvent> eventChannel = myBot.myBot.getEventChannel();
        //add filter
//        eventChannel.filter(botEvent -> botEvent != null && botEvent instanceof BotEvent)
//        Group finalLJLGroup = ljlGroup;
//        Group finalMyGroup = myGroup;
//        eventChannel.filter(botEvent -> )
        eventChannel.subscribeAlways(GroupMessageEvent.class, event -> {
            logger.info("received a message from " + event.getGroup() + event);
            if(event.getGroup().getId() == myGroupId){
                assignMessageResolver.resolve(event,sqkbGroup);
            }
        });

    }

    @Override
    public void close() {

    }

    @Override
    public void restart() {

    }
}
