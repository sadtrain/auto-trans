package com.sadtrain.autotrans.mirai.bots;

//import application.miraisignhandler.MiraiSignHandler;
//import application.miraisignhandler.SignEncryptService;
import com.sadtrain.autotrans.AutoTransApplication;
import com.sadtrain.autotrans.web.request.AddBotRequest;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.internal.spi.EncryptService;
import net.mamoe.mirai.internal.spi.EncryptServiceContext;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoginSolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.cssxsh.mirai.tool.FixProtocolVersion;
import xyz.cssxsh.mirai.tool.KFCFactory;

import java.io.File;
import java.util.ServiceLoader;

public class BotHelper {

    private static Logger logger = LoggerFactory.getLogger(MyBot.class);
    public static Bot newBot(Long botNum,String password){
//        FixProtocolVersion.sync(); //先执行FixProtocolVersion的修复，我的插件才能获取正确的协议版本
        FixProtocolVersion.sync(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        ServiceLoader<EncryptService> signEncryptServices = ServiceLoader.load(EncryptService.class);
        for (EncryptService signEncryptService : signEncryptServices) {
            System.out.println(signEncryptService.getClass().getName());
        }
        KFCFactory.Companion.install();
        ServiceLoader<EncryptService.Factory> factories = ServiceLoader.load(EncryptService.Factory.class);
        for (EncryptService.Factory factory : factories) {
            System.out.println(factory.getClass().getName());
        }
//        MiraiSignHandler.register();
//        KFCFactory kfcFactory = new KFCFactory(); //创建KFC工厂
//        EncryptServiceContext encryptServiceContext = new EncryptServiceContext(botNum, TypeSafeMap.EMPTY); //创建加密服务上下文
//        kfcFactory.createForBot();
//
//        EncryptService encryptService = kfcFactory.createForBot().create(botNum, encryptServiceContext); //创建加密服务
//        encryptService.qSecurityGetSign()
        BotFactory.INSTANCE instance = BotFactory.INSTANCE;
        BotConfiguration botConfiguration = new BotConfiguration();

        BotAuthorization botAuthorization = BotAuthorization.byQRCode();
        if(System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")){
            String jarPath = getJarPath();
            System.out.println(getJarPath());
            botConfiguration.setWorkingDir(new File(jarPath));
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

        botConfiguration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        FixProtocolVersion.sync(BotConfiguration.MiraiProtocol.ANDROID_PHONE); //
//        botConfiguration.setLoginSolver(LoginSolver.Companion.getSingleSolver());
        Bot myBot = instance.newBot(botNum, password, botConfiguration);
//        Bot myBot = instance.newBot(botNum, BotAuthorization.byQRCode(), botConfiguration);
        return myBot;
    }

    public static String getJarPath() {
        String jarPath = AutoTransApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(jarPath).getParent();
    }
}
