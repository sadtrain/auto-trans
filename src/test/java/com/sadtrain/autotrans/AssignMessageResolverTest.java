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
        String str = "https://item.m.jd.com/product/100035493252.html?utm_user=plusmember&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL";
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
    public void test3(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.sadtrain.autotrans.mirai.resolver","com.sadtrain.autotrans.api");
        context.refresh();
        AssignMessageResolver assignMessageResolver = context.getBean(AssignMessageResolver.class);
        String str = "4.9亓/双拖鞋！小神价\n" +
                "---\n" +
                "2件 任拍2件到手9.98亓！\n" +
                "情侣款踩屎感防滑拖鞋2双\n" +
                "￥SvT3dHoyfme￥/ CZ88 \n" +
                "\n" +
                "https://s.click.Taobao.com/8SMEZDu";
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        newMassageBuilder.append("28￥I17p2lJICHj￥ https://m.tb.cn/h.fHkdGPl?sm=2cb1f5  快抢最高22888元红包！");
        MessageChain build = newMassageBuilder.build();

        String s = assignMessageResolver.handlerText(str);
        System.out.println(s);
    }
}
