package com.sadtrain.autotrans.mirai.resolver;

import com.alibaba.fastjson.JSON;
import com.sadtrain.autotrans.api.GoodsConvertor;
import com.sadtrain.autotrans.api.JDUrlConvertor;
import com.sadtrain.autotrans.api.KuaiZhanConvertor;
import com.sadtrain.autotrans.api.ShortUrlConvertor;
import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.TBActivityConvertor;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.TKLExtractor;
import com.sadtrain.autotrans.api.response.DtkActivityLinkResponse;
import com.sadtrain.autotrans.api.response.DtkGetPrivilegeLinkResponse;
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
import org.apache.commons.lang3.StringUtils;
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
public class AssignMessageResolver implements MessageResolver {
    private static Logger logger = LoggerFactory.getLogger(AssignMessageResolver.class);
    @Autowired
    private TKLConvertor tklConvertor;
    @Autowired
    private JDUrlConvertor jdUrlConvertor;
    @Autowired
    private TKLExtractor tklExtractor;
    @Autowired
    private TBActivityConvertor tbActivityConvertor;
    @Autowired
    private GoodsConvertor goodsConvertor;
    @Autowired
    private ShortUrlConvertor shortUrlConvertor;
    @Autowired
    private KuaiZhanConvertor kuaiZhanConvertor;
    static Pattern pattern = Pattern.compile("([￥(]\\w{8,12}[￥)])");
    static Pattern jdUrlpattern = Pattern.compile("(https\\:\\/\\/(?:item\\.m|u)\\.jd\\.com\\/[0-9A-Za-z._?=&/]+)");
//    static Pattern jdGoodsUrlpattern = Pattern.compile("(https\\:\\/\\/(?:item|coupon)\\.m\\.jd\\.com\\/[0-9A-Za-z._?=&]+)");
    ;
    static Pattern shortTbUrlpattern = Pattern.compile("(https\\:\\/\\/m\\.[tT][bB]\\.cn\\/[0-9A-Za-z._?=&]+)");
    ;
    static Pattern sClickUrlpattern = Pattern.compile("(https\\:\\/\\/s\\.click\\.[Tt]aobao\\.com\\/[0-9A-Za-z._?=&]+)");
    ;
    static Pattern kuaiZhanUrlpattern = Pattern.compile("(https://yun095\\.kuaizhan\\.com/[0-9A-Za-z._?=&]+)");

//    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("[0-9A-Za-z._?=&]+");
//        Matcher matcher = pattern.matcher("100035493252.html?utm_user=plusmember&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL");
//        matcher.find();
//        System.out.println(matcher.group(1));
//        matcher.find();
//        System.out.println(matcher.group(1));
//    }

    public MessageChain resolve(MessageEvent event) {
        MessageChain messageChain = event.getMessage();
        MessageChainBuilder newMassageBuilder = new MessageChainBuilder();
        for (SingleMessage message : messageChain) {
            if (message instanceof PlainText) {
                String text = ((PlainText) message).getContent();
                if(text.contains("淘宝+京东自助查券网站")){
                    return null;
                }
                String content = handlerText(text);
                if (content != null) {
                    newMassageBuilder.append(new PlainText(content));
                }
            } else if (message instanceof Image) {
                newMassageBuilder.append(message);
            } else if (message instanceof AtAll) {
                newMassageBuilder.append(message);
            }
        }
        MessageChain newMessage = newMassageBuilder.build();
        return newMessage;

    }

    public String handlerText(String content) {
        //todo 可能有多个淘口令，都要转
        if (content == null || content.length() == 0) {
            logger.error("content is null");
            return null;
        }

        Matcher matcher = pattern.matcher(content);
        StringBuilder sb0 = new StringBuilder();
        while (matcher.find()) {
            String group = matcher.group(1);
            logger.info("tkl {} found", group);
            //解析接口，可能是商品或者活动
            DtkParseContentResponse convert = tklExtractor.convert(group);
            if (convert != null) {
                String dataType = convert.getDataType();
                if (TKLExtractor.DATATYPE_ACTIVITY.equals(dataType)) {
                    logger.info("{} is a activity", group);
                    String goodsId = convert.getGoodsId();
                    DtkActivityLinkResponse dtkActivityLinkResponse = tbActivityConvertor.convert(goodsId);
                    if (dtkActivityLinkResponse == null) {
                        logger.error("convert failed{}", group);
                        throw new RuntimeException("convert failed");
                    }
                    matcher.appendReplacement(sb0,dtkActivityLinkResponse.getTpwd());
                } else {
                    logger.info("{} is a goods", group);
                    DtkTwdToTwdResponse dtkTwdToTwdResponse = tklConvertor.convert(group);
                    if (dtkTwdToTwdResponse == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    matcher.appendReplacement(sb0,dtkTwdToTwdResponse.getTpwd());
                }
//                        System.out.println(matcher.group(1));
            }

        }
        matcher.appendTail(sb0);
        content = sb0.toString();
        Matcher sClickMatcher = sClickUrlpattern.matcher(content);
        StringBuilder sclickSB = new StringBuilder();
        while (sClickMatcher.find()) {
            String sclick = sClickMatcher.group(1);
            //sclick 只能转换商品的，618大促活动的不行
            DtkParseContentResponse response = tklExtractor.convert(sclick);
            if (response == null) {
                //转换失败就尝试用活动去转
//                DtkActivityLinkResponse myTKL = tbActivityConvertor.convert(activityId);
//                if (myTKL == null) {
//                    logger.error("convert failed{}", content);
//                    throw new RuntimeException("convert failed");
//                }
//                content = content.replaceAll(sclick, myTKL.getLongTpwd());
            } else {
                String dataType = response.getDataType();
                if (TKLExtractor.DATATYPE_ACTIVITY.equals(dataType)) {
                    DtkActivityLinkResponse myTKL = tbActivityConvertor.convert(response.getGoodsId());
                    if (myTKL == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    sClickMatcher.appendReplacement(sclickSB,myTKL.getLongTpwd());

                } else {
                    String goodsId = response.getGoodsId();
                    DtkGetPrivilegeLinkResponse convert = goodsConvertor.convert(goodsId);
                    if (convert == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    sClickMatcher.appendReplacement(sclickSB,convert.getShortUrl());
                }
            }
        }
        sClickMatcher.appendTail(sclickSB);
        content = sclickSB.toString();

        Matcher jdMatcher = jdUrlpattern.matcher(content);
        StringBuilder sbJD = new StringBuilder();
        while (jdMatcher.find()) {
            String tkl = jdMatcher.group(1);
            String myTKL = jdUrlConvertor.convert(tkl);
            if (myTKL == null) {
                logger.error("convert failed{}", content);
                throw new RuntimeException("convert failed");
            }
            jdMatcher.appendReplacement(sbJD,myTKL);
        }
        jdMatcher.appendTail(sbJD);
        content = sbJD.toString();
        Matcher tbUrl = shortTbUrlpattern.matcher(content);
        StringBuilder sb1 = new StringBuilder();
        while (tbUrl.find()) {
            String tkl = tbUrl.group(1);
            DtkParseContentResponse response = tklExtractor.convert(tkl);
            if (response == null) {
                //todo 转成短链接
                DtkActivityLinkResponse dtkActivityLinkResponse = shortUrlConvertor.convert(tkl);
                if (dtkActivityLinkResponse == null) {
                    logger.error("convert failed{}", content);
                    throw new RuntimeException("convert failed");
                }
                String longTpwd = dtkActivityLinkResponse.getLongTpwd();
                tbUrl.appendReplacement(sb1,longTpwd);
            } else {
                String dataType = response.getDataType();
                if (TKLExtractor.DATATYPE_ACTIVITY.equals(dataType)) {
                    DtkActivityLinkResponse myTKL = tbActivityConvertor.convert(response.getGoodsId());
                    if (myTKL == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    tbUrl.appendReplacement(sb1,myTKL.getLongTpwd());
                } else {
                    String goodsId = response.getGoodsId();
                    DtkGetPrivilegeLinkResponse convert = goodsConvertor.convert(goodsId);
                    if (convert == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    tbUrl.appendReplacement(sb1,convert.getShortUrl());
                }
            }
        }
        tbUrl.appendTail(sb1);
        content = sb1.toString();
        Matcher kuaizhanMatcher = kuaiZhanUrlpattern.matcher(content);
        StringBuilder sb = new StringBuilder();
        while (kuaizhanMatcher.find()) {
            String kuaiZhanUrl = kuaizhanMatcher.group(1);
            DtkGetPrivilegeLinkResponse convert = kuaiZhanConvertor.convert(kuaiZhanUrl);
            if (convert != null) {
                kuaizhanMatcher.appendReplacement(sb,convert.getKuaiZhanUrl());
//                content = content.replaceAll(kuaiZhanUrl, convert.getKuaiZhanUrl());
            }
        }
        kuaizhanMatcher.appendTail(sb);
        content = sb.toString();
        return content;


    }

//    public static void main(String[] args) {
//        String content="牙膏8件套装！6牙膏+2牙刷\n" +
//                "黑妹官方旗舰店 按照要求加车\n" +
//                "16块左右！共八件套！！\n" +
//                "~~~\n" +
//                "第一步【牙膏加购物车1份】\n" +
//                "https://yun095.kuaizhan.com/?3EWl1g \n" +
//                "-\n" +
//                "第二步【凑单款加购物车1份】\n" +
//                "https://yun095.kuaizhan.com/?49nI9Q ";
//        String str1="https://yun095.kuaizhan.com/?3EWl1g";
//        String str2="https://08gea.kuaizhan.com/?_s=BM3qy";
//        System.out.println(content.indexOf(str1));
//        content = content.replaceAll(str1,str2);
//        System.out.println(content);
////        String str2=""
//    }
}
