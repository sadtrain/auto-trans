package com.sadtrain.autotrans;

import com.gargoylesoftware.htmlunit.javascript.host.event.MessageEvent;
import com.sadtrain.autotrans.mirai.resolver.AssignMessageResolver;
import net.mamoe.mirai.event.events.MessageRecallEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;

public class AssignMessageResolverTest {


    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.sadtrain.autotrans.mirai.resolver","com.sadtrain.autotrans.api");
        context.refresh();
        AssignMessageResolver assignMessageResolver = context.getBean(AssignMessageResolver.class);
        String str = "28￥I17p2lJICHj￥ https://m.tb.cn/h.fHkdGPl?sm=2cb1f5  快抢最高22888元红包！";
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        newMassageBuilder.append("28￥I17p2lJICHj￥ https://m.tb.cn/h.fHkdGPl?sm=2cb1f5  快抢最高22888元红包！");
        MessageChain build = newMassageBuilder.build();

        String s = assignMessageResolver.handlerText(str);
        System.out.println(s);
    }
    @Test
    public void test1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.sadtrain.autotrans.mirai.resolver","com.sadtrain.autotrans.api");
        context.refresh();
        AssignMessageResolver assignMessageResolver = context.getBean(AssignMessageResolver.class);
        String str = "【淘宝】我是测试字符串https://m.tb.cn/h.fuMb675?tk=wnIo2m1sLEC「纹身贴防水持久仿真女 小清新花图案 网红ins风锁骨花朵 贴纸50张」";
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        newMassageBuilder.append("28￥I17p2lJICHj￥ https://m.tb.cn/h.fHkdGPl?sm=2cb1f5  快抢最高22888元红包！");
        MessageChain build = newMassageBuilder.build();

        String s = assignMessageResolver.handlerText(str);
        System.out.println(s);
    }
    @Test
    public void test2(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.sadtrain.autotrans.mirai.resolver","com.sadtrain.autotrans.api");
        context.refresh();
        AssignMessageResolver assignMessageResolver = context.getBean(AssignMessageResolver.class);
        String str = "牙膏8件套装！6牙膏+2牙刷\n" +
                "黑妹官方旗舰店 按照要求加车\n" +
                "16块左右！共八件套！！\n" +
                "~~~\n" +
                "第一步【牙膏加购物车1份】\n" +
                "https://yun095.kuaizhan.com/?3EWl1g \n" +
                "-\n" +
                "第二步【凑单款加购物车1份】\n" +
                "https://yun095.kuaizhan.com/?49nI9Q ";
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        newMassageBuilder.append("28￥I17p2lJICHj￥ https://m.tb.cn/h.fHkdGPl?sm=2cb1f5  快抢最高22888元红包！");
        MessageChain build = newMassageBuilder.build();

        String s = assignMessageResolver.handlerText(str);
        System.out.println(s);
    }

    @Test
    public void testJD(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.sadtrain.autotrans.mirai.resolver","com.sadtrain.autotrans.api");
        context.refresh();
        AssignMessageResolver assignMessageResolver = context.getBean(AssignMessageResolver.class);
        String str = "会员领200-10全品奍\n" +
                "https://u.jd.com/lKfRbZ6  \n" +
                "云南白药牙膏500g 59亓\n" +
                "到手5支   赠牙刷2支\n" +
                "https://u.jd.com/lKf9e2W  ";

        String s = assignMessageResolver.handlerText(str);
        System.out.println(s);
    }
}
