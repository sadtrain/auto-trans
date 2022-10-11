package com.sadtrain.autotrans.mirai.bots;

import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.db.entity.BotEntity;
import com.sadtrain.autotrans.db.entity.Consumer;
import com.sadtrain.autotrans.db.mapper.BotMapper;
import com.sadtrain.autotrans.db.mapper.ConsumerMapper;
import com.sadtrain.autotrans.db.mapper.ListenerMapper;
import com.sadtrain.autotrans.life.LifeCycle;
import com.sadtrain.autotrans.mirai.listener.ListenerConfig;
import com.sadtrain.autotrans.mirai.resolver.AssignMessageResolver;
import com.sadtrain.autotrans.mirai.resolver.MessageResolver;
import io.swagger.models.auth.In;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BotManager implements LifeCycle {
    private static Logger logger = LoggerFactory.getLogger(BotManager.class);

    @Autowired
    private AssignMessageResolver assignMessageResolver;

    @Autowired
    private BotMapper botMapper;

    @Autowired
    private ListenerMapper listenerMapper;

    @Autowired
    private ConsumerMapper consumerMapper;

    private List<Bot> bots = new ArrayList<>();
    private Map<Long,Bot> botMap = new HashMap<>();
    private List<MessageResolver> messageResolvers;
    private Map<Integer,Listener> listenerMap = new HashMap<>();
    private Map<Long,List<Listener>> listenerBotMap = new HashMap<>();
    private Map<Integer,List<Consumer>> listenerConsumer = new HashMap<>();
    List<Listener> listeners = new ArrayList<>();


    public void addBot(Bot bot){
        if(botMap.containsKey(bot.getId())){
            return;
        }
        bots.add(bot);
        botMap.put(bot.getId(),bot);
    }

    public Bot getBot(Long botNum){
        return botMap.get(botNum);
    }
//    public List<com.sadtrain.autotrans.db.entity.Listener> getAllListeners(){
//        return listenerMap.values();
//    }

    public void removeBot(Long botNum){
        if(!botMap.containsKey(botNum)){
            return;
        }
        Bot bot = botMap.get(botNum);
        bots.remove(bot);
        botMap.remove(botNum);
    }

    public void addListener(com.sadtrain.autotrans.db.entity.Listener listener){
        if(listenerMap.containsKey(listener.getId())){
            logger.warn("listener duplicate");
            return;
        }
        Bot bot = null;
        Long botId = listener.getBotNum();
        for (Bot bot1 : bots) {
            if(bot1.getId() == botId){
                bot = bot1;
            }
        }
        if(bot == null){
            return;
        }
        EventChannel<BotEvent> eventChannel = bot.getEventChannel();
        Bot finalBot = bot;
        Listener<GroupMessageEvent> groupMessageEventListener = eventChannel.subscribeAlways(GroupMessageEvent.class, event -> {
            try {
                logger.info("received a message from " + event.getGroup() + event.getSenderName());
                if (event.getGroup().getId() == listener.getGroupNum()) {
                    List<Consumer> consumers = listenerConsumer.get(listener.getId());
                    MessageChain messageChain = assignMessageResolver.resolve(event);
                    for (Consumer consumer : consumers) {
                        logger.info("prepare to send message：" + JSON.toJSONString(messageChain));

                        List<Group> groups = consumer.getToGroupNumList().stream().map(finalBot::getGroup).collect(Collectors.toList());
                        for (Group group : groups) {
                            try {
                                group.sendMessage(messageChain);
                            }catch (Throwable e){
                                logger.error("send failed");
                            }
                        }
                    }
                }
            }catch (Throwable e){
                logger.error("handle message failed",e);
            }
        });
        listeners.add(groupMessageEventListener);
        listenerMap.put(listener.getId(), groupMessageEventListener);
    }

    public void removeListener(Integer listenerId){
        Listener listener = listenerMap.get(listenerId);
        listener.complete();
        listeners.remove(listener);
        listenerMap.remove(listenerId);
        for (Map.Entry<Long, List<Listener>> entry : listenerBotMap.entrySet()) {
            entry.getValue().remove(listener);
        }

    }

    public void registerConsumer(Consumer consumer, Integer listenerId){
        if(listenerConsumer.containsKey(listenerId)){
            listenerConsumer.get(listenerId).add(consumer);
        }else{
            ArrayList<Consumer> consumers = new ArrayList<>();
            consumers.add(consumer);
            listenerConsumer.put(listenerId,consumers);
        }
    }

    public Integer removeConsumer(Integer listenerId){
        if(listenerConsumer.containsKey(listenerId)){
            List<Consumer> remove = listenerConsumer.remove(listenerId);
            return remove.get(0).getId();
        }else{
            return null;
        }
    }

    public void addConsumer(Consumer consumer, Integer listenerId){

        if(listenerConsumer.containsKey(listenerId)){
            listenerConsumer.get(listenerId).add(consumer);
        }else{
            ArrayList<Consumer> consumers = new ArrayList<>();
            consumers.add(consumer);
            listenerConsumer.put(listenerId,consumers);
        }
    }

    public void cancelConsumer(Consumer consumer, Integer listenerId){
        if(listenerConsumer.containsKey(listenerId)){
            listenerConsumer.get(listenerId).add(consumer);
        }else{
            ArrayList<Consumer> consumers = new ArrayList<>();
            consumers.add(consumer);
            listenerConsumer.put(listenerId,consumers);
        }
    }

    public void removeListener(ListenerConfig listenerConfig){
        Listener listener = listenerMap.get(listenerConfig);
        listener.complete();
        listenerMap.remove(listenerConfig);
        listeners.remove(listener);
    }

    public List<MessageResolver> getMessageResolvers() {
        return messageResolvers;
    }

    public void setMessageResolvers(List<MessageResolver> messageResolvers) {
        this.messageResolvers = messageResolvers;
    }

    @Override
    public void start() {
        List<BotEntity> botEntities = botMapper.selectList(null);
        for (BotEntity botEntity : botEntities) {
            Bot bot = getBot(botEntity.getBotNum());
            if(bot != null){
                bot.login();
            }else{
                bot = BotHelper.newBot(botEntity.getBotNum(), botEntity.getPassword());
                bot.login();
                addBot(bot);
                logger.info("start bot {} success",bot.getId() + bot.getNick());
            }
        }
        List<com.sadtrain.autotrans.db.entity.Listener> listeners = listenerMapper.selectList(null);
        for (com.sadtrain.autotrans.db.entity.Listener listener : listeners) {
            addListener(listener);
            logger.info("add listener {} success",listener);
        }
        List<Consumer> consumers = consumerMapper.selectList(null);
        for (Consumer consumer : consumers) {
            addConsumer(consumer,consumer.getListenerId());
            logger.info("add consumer {} success",consumer);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void restart() {

    }
}
