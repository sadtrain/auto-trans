package com.sadtrain.autotrans.mirai.resolver;

import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.api.JDUrlConvertor;
import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.TBActivityConvertor;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.TKLExtractor;
import com.sadtrain.autotrans.api.response.DtkParseContentResponse;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.api.response.base.BaseResponse;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.SingleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AssignMessageResolver implements MessageResolver{
    private static Logger logger = LoggerFactory.getLogger(AssignMessageResolver.class);
    @Autowired
    private TKLConvertor tklConvertor;
    @Autowired
    private JDUrlConvertor jdUrlConvertor;
    @Autowired
    private TKLExtractor tklExtractor;
    @Autowired
    private TBActivityConvertor tbActivityConvertor;
    static Pattern pattern = Pattern.compile("[\\s\\S]*?(￥.*￥)[\\s\\S]*?");
    static Pattern jdUrlpattern = Pattern.compile(".*?(https\\:\\/\\/u\\.jd\\.com\\/\\w+).*?");;
    static Pattern tbUrlpattern = Pattern.compile(".*?(https\\:\\/\\/m\\.[tT][bB]\\.cn\\/[0-9A-Za-z\\.\\_]+).*?");;

    public static void main(String[] args) {
        Matcher matcher = tbUrlpattern.matcher("我不是https://m.tb.cn/qwersa.sdf水电费https://m.tb.cn/qwsdfersa.sdf");
        matcher.find();
        System.out.println(matcher.group(1));
        matcher.find();
        System.out.println(matcher.group(1));
    }
    public MessageChain resolve(MessageEvent event) {
        MessageChain messageChain = event.getMessage();
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        for (SingleMessage message : messageChain) {
            if (message instanceof PlainText) {
                //todo 可能有多个淘口令，都要转
                String content = ((PlainText) message).getContent();

                Matcher matcher1 = pattern.matcher(content);
                while(matcher1.find()){
                    DtkParseContentResponse convert = tklExtractor.convert(content);
                    if(convert != null){
                        Matcher matcher = pattern.matcher(content);
                        if (matcher.matches()) {
                            String tkl = matcher.group(1);
                            String dataType = convert.getDataType();
                            if(TKLExtractor.DATATYPE_ACTIVITY.equals(dataType)){
                                String myTKL = tbActivityConvertor.convert(tkl);
                                assert myTKL != null;
                                content = content.replaceAll(tkl, myTKL);
                            }else{
                                String myTKL = tklConvertor.convert(tkl);
                                assert myTKL != null;
                                content = content.replaceAll(tkl, myTKL);
                            }
//                        System.out.println(matcher.group(1));
                        }
                    }else{
                        Matcher jdMatcher = jdUrlpattern.matcher(content);
                        if (jdMatcher.matches()) {
                            String tkl = jdMatcher.group(1);
//                        System.out.println(matcher.group(1));
                            String myTKL = jdUrlConvertor.convert(tkl);
                            assert myTKL != null;
                            content = content.replaceAll(tkl, myTKL);
                        }
                    }

                }

                newMassageBuilder.append(new PlainText(content));
            } else if (message instanceof Image) {
                newMassageBuilder.append(message);
            } else if (message instanceof AtAll){
                newMassageBuilder.append(message);
            }
        }
        MessageChain newMessage = newMassageBuilder.build();
        return newMessage;

    }

}
