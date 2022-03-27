//package com.sadtrain.autotrans.test;
//
//import com.sadtrain.autotrans.api.SignMD5Util;
//import net.mamoe.mirai.Bot;
//import net.mamoe.mirai.BotFactory;
//import net.mamoe.mirai.contact.ContactList;
//import net.mamoe.mirai.contact.Group;
//import net.mamoe.mirai.event.EventChannel;
//import net.mamoe.mirai.event.events.BotEvent;
//import net.mamoe.mirai.event.events.GroupMessageEvent;
//import net.mamoe.mirai.message.data.MessageChain;
//import net.mamoe.mirai.message.data.MessageChainBuilder;
//import net.mamoe.mirai.message.data.MessageContent;
//import net.mamoe.mirai.message.data.PlainText;
//import net.mamoe.mirai.message.data.SingleMessage;
//import net.mamoe.mirai.utils.BotConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.File;
//import java.util.List;
//import java.util.TreeMap;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Test {
//
//    private static Logger logger = LoggerFactory.getLogger(Test.class);
//    public static void main(String[] args) {
//        String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
//        String appKey = "623e87e1c0d83"; //应用key
//        String host = "https://openapi.dataoke.com/api/tb-service/parse-taokouling?version={version}&appKey={appKey}&content={content}&sign={sign}";//应用服务接口
//
//
//        BotFactory.INSTANCE instance = BotFactory.INSTANCE;
//        BotConfiguration botConfiguration = new BotConfiguration();
//        botConfiguration.setWorkingDir(new File("D:/mirai"));
//        botConfiguration.fileBasedDeviceInfo();
//        //上线后需要关闭
////        botConfiguration.enableContactCache();
//        BotConfiguration.ContactListCache contactListCache = botConfiguration.getContactListCache();
//        contactListCache.setFriendListCacheEnabled(true); // 开启好友列表缓存
//        contactListCache.setGroupMemberListCacheEnabled(true); // 开启群成员列表缓存
//        contactListCache.setSaveIntervalMillis(60000); // 可选设置有更新时的保存时间间隔, 默认 60 秒
//        Bot alias2011 = instance.newBot(1621924168L, "Alias2011", botConfiguration);
//        alias2011.login();
//        List<Bot> instances = Bot.getInstances();
//        for (Bot bot : instances) {
//            System.out.println(bot);
//        }
//
//        ContactList<Group> groups = alias2011.getGroups();
//        Group jiuGroup = null;
//        for (Group group : groups) {
//            if(group.getName().equals("就")){
//                jiuGroup = group;
//                break;
//            }
//        }
//        assert(jiuGroup != null);
//        EventChannel<BotEvent> eventChannel = alias2011.getEventChannel();
//        //add filter
////        eventChannel.filter(botEvent -> botEvent != null && botEvent instanceof BotEvent)
//        Group finalJiuGroup = jiuGroup;
//        eventChannel.subscribeAlways(GroupMessageEvent.class, event -> {
//            logger.info("received a message from " + event.getGroup() + event);
//            MessageChain messageChain = event.getMessage();
//            AtomicBoolean needParse = new AtomicBoolean(false);
//            MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
//            for (SingleMessage message : messageChain) {
//                if (message instanceof PlainText){
//                    String content = ((PlainText) message).getContent();
//                    Pattern pattern = Pattern.compile(".*(￥.*￥).*");
//                    Matcher matcher = pattern.matcher(content);
//                    if(matcher.matches()){
//                        String tkl = matcher.group(1);
////                        System.out.println(matcher.group(1));
//                        TreeMap<String,String> paraMap = new TreeMap<>();
//                        paraMap.put("version","v1.0.0");
//                        paraMap.put("appKey",appKey);
//                        paraMap.put("content","1.左下角进入店铺入会 2.如图先把119-30券领取了 3.最后一步 拍第二个选项 ￥BN7A2TsMhbm￥// 直接拍2套 到手4件 39.9元 反馈:这个价格超值，小神价需要的冲 ");
//                        String signStr = SignMD5Util.getSignStr(paraMap, appSecret);
//                        System.out.println(signStr);
//                        paraMap.put("sign", signStr);
////        System.out.println(SignMD5Util.getSignStr(paraMap,appSecret));
//                        RestTemplate restTemplate = new RestTemplate();
//                        ResponseEntity<String> forEntity = restTemplate.getForEntity(host, String.class, paraMap);
//                        String myTKL = forEntity.getBody();
//                        assert myTKL != null;
//                        String newContent = content.replaceAll(tkl, myTKL);
//                        newMassageBuilder.add(new PlainText(newContent));
//                    }
//                }else{
//                    newMassageBuilder.add(message);
//                }
//
//            }
//            finalJiuGroup.sendMessage(newMassageBuilder.build());
//        });
//        eventChannel.exceptionHandler((coroutineContext, e) -> logger.error("error",e));
//    }
//}
