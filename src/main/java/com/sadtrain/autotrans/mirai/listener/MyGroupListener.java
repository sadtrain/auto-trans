package com.sadtrain.autotrans.mirai.listener;

import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.life.LifeCycle;
import com.sadtrain.autotrans.mirai.bots.MyBot;
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

    Group sqkbGroup;

    Group myGroup;

    public static final Long sqxbGroupId = 639164037L;

    public static final Long myGroupId = 197315399L;
    String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
    String appKey = "623e87e1c0d83"; //应用key
    String host = "https://openapi.dataoke.com/api/tb-service/twd-to-twd?version={version}&appKey={appKey}&content={content}&sign={sign}";//应用服务接口

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
            if(!(event.getGroup().getId() == myGroupId)){
                return;
            }
            MessageChain messageChain = event.getMessage();
            MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
            for (SingleMessage message : messageChain) {
                if (message instanceof PlainText){
                    String content = ((PlainText) message).getContent();
                    Pattern pattern = Pattern.compile(".*(￥.*￥).*");
                    Matcher matcher = pattern.matcher(content);
                    if(matcher.matches()){
                        String tkl = matcher.group(1);
//                        System.out.println(matcher.group(1));
                        TreeMap<String,String> paraMap = new TreeMap<>();
                        paraMap.put("version","v1.0.0");
                        paraMap.put("appKey",appKey);
                        paraMap.put("content", tkl);
                        String signStr = SignMD5Util.getSignStr(paraMap, appSecret);
                        System.out.println(signStr);
                        paraMap.put("sign", signStr);
                        RestTemplate restTemplate = new RestTemplate();
                        ResponseEntity<DtkTwdToTwdResponse> forEntity = restTemplate.getForEntity(host, DtkTwdToTwdResponse.class, paraMap);
                        logger.info(forEntity.getBody().toString());
                        String myTKL = forEntity.getBody().getTpwd();
                        assert myTKL != null;
                        String newContent = content.replaceAll(tkl, myTKL);
                        newMassageBuilder.append(new PlainText(newContent));
                    }
                }else{
                    newMassageBuilder.append(message);
                }

            }
            MessageChain newMessage = newMassageBuilder.build();
            logger.info(newMessage.toString());
            sqkbGroup.sendMessage(newMessage);
        });

    }

    @Override
    public void close() {

    }

    @Override
    public void restart() {

    }
}
