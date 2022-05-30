package com.sadtrain.autotrans.mirai.resolver;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

public interface MessageResolver {

    MessageChain resolve(MessageEvent event);
}
