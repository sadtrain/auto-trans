//package com.sadtrain.autotrans.mirai.listener;
//
//import com.sadtrain.autotrans.life.LifeCycle;
//import com.sadtrain.autotrans.mirai.resolver.MessageResolver;
//import net.mamoe.mirai.Bot;
//import net.mamoe.mirai.contact.Group;
//import net.mamoe.mirai.event.events.MessageEvent;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupMessageConsumer implements LifeCycle, Consumer{
//
//    private Bot bot;
//
//    private List<Long> toGroupIds = new ArrayList<>();
//
//    private List<Group> toGroups = new ArrayList<>();
//
//    private MessageResolver messageResolver;
//
//
//
//
//    public List<Long> getToGroupIds() {
//        return toGroupIds;
//    }
//
//    public void setToGroupIds(List<Long> toGroupIds) {
//        this.toGroupIds = toGroupIds;
//    }
//
//    public List<Group> getToGroups() {
//        return toGroups;
//    }
//
//    public void setToGroups(List<Group> toGroups) {
//        this.toGroups = toGroups;
//    }
//
//    public MessageResolver getMessageResolver() {
//        return messageResolver;
//    }
//
//    public void setMessageResolver(MessageResolver messageResolver) {
//        this.messageResolver = messageResolver;
//    }
//
//    public Bot getBot() {
//        return bot;
//    }
//
//    public void setBot(Bot bot) {
//        this.bot = bot;
//    }
//
//    @Override
//    public void start() {
//        for (Long groupId : getToGroupIds()) {
//            Group group = bot.getGroup(groupId);
//            if(group != null){
//                toGroups.add(group);
//            }
//        }
//    }
//
//    public void onMessage(MessageEvent event){
//        for (Group toGroup : toGroups) {
//            messageResolver.resolve(event,toGroup);
//        }
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    @Override
//    public void restart() {
//
//    }
//}
