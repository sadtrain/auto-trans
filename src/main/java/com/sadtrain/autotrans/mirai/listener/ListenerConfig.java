package com.sadtrain.autotrans.mirai.listener;

public class ListenerConfig {

    private Integer listenerId;
    private Long botId;
    private Long groupId;

    public Long getBotId() {
        return botId;
    }

    public void setBotId(Long botId) {
        this.botId = botId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getListenerId() {
        return listenerId;
    }

    public void setListenerId(Integer listenerId) {
        this.listenerId = listenerId;
    }
}
