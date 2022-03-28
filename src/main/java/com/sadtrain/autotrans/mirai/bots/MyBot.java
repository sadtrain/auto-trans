package com.sadtrain.autotrans.mirai.bots;

import com.sadtrain.autotrans.life.LifeCycle;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MyBot implements LifeCycle {
    private static Logger logger = LoggerFactory.getLogger(MyBot.class);

    public Bot myBot;

    private static Long myQQ = 425497793L;

//    private static String myPassword = "$.sadtrain(503)";
    private static String myPassword = "Alias2011";
    @Override
    public void start() {
        BotFactory.INSTANCE instance = BotFactory.INSTANCE;
        BotConfiguration botConfiguration = new BotConfiguration();
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        botConfiguration.setWorkingDir(new File(this.getClass().getClassLoader().getResource("").getPath()));
        botConfiguration.fileBasedDeviceInfo();
        //上线后需要关闭
//        botConfiguration.enableContactCache();
//        BotConfiguration.ContactListCache contactListCache = botConfiguration.getContactListCache();
//        contactListCache.setFriendListCacheEnabled(true); // 开启好友列表缓存
//        contactListCache.setGroupMemberListCacheEnabled(true); // 开启群成员列表缓存
//        contactListCache.setSaveIntervalMillis(60000); // 可选设置有更新时的保存时间间隔, 默认 60 秒

        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
        myBot = instance.newBot(myQQ, myPassword, botConfiguration);
        myBot.login();
        logger.info(myBot + "started!");
    }

    @Override
    public void close() {
        myBot.close();
        logger.info(myBot + "exited!");
    }

    @Override
    public void restart() {

    }
}
