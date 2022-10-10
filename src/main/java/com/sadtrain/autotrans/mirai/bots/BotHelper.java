package com.sadtrain.autotrans.mirai.bots;

import com.sadtrain.autotrans.web.request.AddBotRequest;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class BotHelper {

    private static Logger logger = LoggerFactory.getLogger(MyBot.class);
    public static Bot newBot(Long botNum,String password){
        BotFactory.INSTANCE instance = BotFactory.INSTANCE;
        BotConfiguration botConfiguration = new BotConfiguration();
        if(System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")){
            System.out.println(BotHelper.class.getClassLoader().getResource("").getPath());
            botConfiguration.setWorkingDir(new File(BotHelper.class.getClassLoader().getResource("").getPath()));
        }else{
            File file = new File("/Users/zgs/opt/mirai/work");
            file.mkdirs();
            botConfiguration.setWorkingDir(file);
        }
        botConfiguration.fileBasedDeviceInfo();
        //上线后需要关闭
//        botConfiguration.enableContactCache();
//        BotConfiguration.ContactListCache contactListCache = botConfiguration.getContactListCache();
//        contactListCache.setFriendListCacheEnabled(true); // 开启好友列表缓存
//        contactListCache.setGroupMemberListCacheEnabled(true); // 开启群成员列表缓存
//        contactListCache.setSaveIntervalMillis(60000); // 可选设置有更新时的保存时间间隔, 默认 60 秒

        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
        Bot myBot = instance.newBot(botNum, password, botConfiguration);
        return myBot;
    }
}
