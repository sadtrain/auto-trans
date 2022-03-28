package com.sadtrain.autotrans.mirai.listener;

import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.life.LifeCycle;
import com.sadtrain.autotrans.mirai.bots.MyBot;
import com.sadtrain.autotrans.mirai.resolver.AssignMessageResolver;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LJLGroupListener implements LifeCycle {

    private static Logger logger = LoggerFactory.getLogger(LJLGroupListener.class);

    @Autowired
    private MyBot myBot;

    @Autowired
    private AssignMessageResolver assignMessageResolver;

    Group ljlGroup;

    Group sqxbGroup;

    public static final Long ljlGroupId = 637546059L;

    public static final Long sqxbGroupId = 639164037L;

    @Override
    public void start() {
        for (Group group : myBot.myBot.getGroups()) {
            if(group.getId() == ljlGroupId){
                ljlGroup = group;
                continue;
            }
            if(group.getId() == sqxbGroupId){
                sqxbGroup = group;
            }
        }
        EventChannel<BotEvent> eventChannel = myBot.myBot.getEventChannel();
        //add filter
//        eventChannel.filter(botEvent -> botEvent != null && botEvent instanceof BotEvent)
//        Group finalLJLGroup = ljlGroup;
//        Group finalMyGroup = myGroup;
//        eventChannel.filter(botEvent -> )
        eventChannel.subscribeAlways(GroupMessageEvent.class, event -> {
            logger.info("received a message from " + event.getGroup() + event.getSenderName());
            if(event.getGroup().getId() == ljlGroupId){
                assignMessageResolver.resolve(event,sqxbGroup);
            }
        });

    }
    public static void main(String[] args) {
        String str = "sadfasgd\n￥tesfg￥\nasdfg";
        Pattern pattern = Pattern.compile("[\\s\\S]*(￥.*￥)[\\s\\S]*");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }
    @Override
    public void close() {

    }

    @Override
    public void restart() {

    }
}
