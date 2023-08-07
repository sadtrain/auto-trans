package com.sadtrain.autotrans.mirai.resolver;

import com.alias.MessageConvertor;
import com.alias.api.ShortUrlConvertor;
import com.alias.common.message.ImageMessage;
import com.alias.common.message.Message;
import com.alias.common.message.MessageEntity;
import com.alias.common.message.TextMessage;
import com.sadtrain.autotrans.api.GoodsConvertor;
import com.sadtrain.autotrans.api.JDUrlConvertor;
import com.sadtrain.autotrans.api.KuaiZhanConvertor;
import com.sadtrain.autotrans.api.SignMD5Util;
import com.sadtrain.autotrans.api.TBActivityConvertor;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.TKLExtractor;
import com.sadtrain.autotrans.api.response.DtkActivityLinkResponse;
import com.sadtrain.autotrans.api.response.DtkGetPrivilegeLinkResponse;
import com.sadtrain.autotrans.api.response.DtkParseContentResponse;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.kafka.MessageCenter;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.SingleMessage;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
        List<Message> textMessages = new ArrayList<>();
        List<Message> imageMessages = new ArrayList<>();
        for (SingleMessage message : messageChain) {
            if (message instanceof PlainText) {
                String text = ((PlainText) message).getContent();
                if(text.contains("淘宝+京东自助查券网站")){
                    return null;
                }
                MessageConvertor messageConverter = new MessageConvertor();
                String content = messageConverter.resolve(text);
                if (content != null) {
                    newMassageBuilder.append(new PlainText(content));
                }
                TextMessage textMessage = new TextMessage();
                textMessage.setText(content);
                textMessages.add(textMessage);
            } else if (message instanceof Image) {
                //todo message构造可能不像想象中那么简单
                //微信发送图片只是本地。微信的图片和文本是分开的
                newMassageBuilder.append(message);
                ImageMessage imageMessage = new ImageMessage();
                String url = Image.queryUrl((Image) message);
                //download
                byte[] bytes = download(url);
                //转为base64
                String base64 = Base64.getEncoder().encodeToString(bytes);
                imageMessage.setImageContent(base64);
                imageMessages.add(imageMessage);
            } else if (message instanceof AtAll) {
                newMassageBuilder.append(message);
            }
        }
        MessageEntity messageEntity = new MessageEntity();
        List<Message> messages = new ArrayList<>();
        messages.addAll(imageMessages);
        messages.addAll(textMessages);
        messageEntity.setMessageList(messages);
        MessageCenter.sendMessage(messageEntity);

        MessageChain newMessage = newMassageBuilder.build();
        return newMessage;

    }

    private byte[] download(String imgUrl) {
        URL url = null;
        try {
            url = new URL(imgUrl);
            try (InputStream inputStream = url.openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                IOUtils.copy(inputStream, outputStream);
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        String imgUrl = "https://freepngimg.com/thumb/anime/113892-kuriyama-mirai-download-free-image.png";
        URL url = null;
        try {
            url = new URL(imgUrl);
            try (InputStream inputStream = url.openStream(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                String localPath = "/Users/bytedance/IdeaProjects/auto-trans/lib/1.png";
                File file = new File(localPath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                IOUtils.copy(inputStream, outputStream);
//                try (FileOutputStream fos = new FileOutputStream(localPath)) {
//                    IOUtils.copy(inputStream, fos);
//                }
                byte[] byteArray = outputStream.toByteArray();
                try (FileOutputStream fos = new FileOutputStream(localPath)) {
                    fos.write(byteArray);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                    String goodsId = convert.getGoodsId();
                    DtkGetPrivilegeLinkResponse dtkGetPrivilegeLinkResponse = goodsConvertor.convert(goodsId);
//                    DtkTwdToTwdResponse dtkTwdToTwdResponse = tklConvertor.convert(group);
                    if (dtkGetPrivilegeLinkResponse == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    matcher.appendReplacement(sb0,dtkGetPrivilegeLinkResponse.getTpwd());
                }
//                        System.out.println(matcher.group(1));
            }

        }
        matcher.appendTail(sb0);
        content = sb0.toString();
        Matcher sClickMatcher = sClickUrlpattern.matcher(content);
        StringBuilder sClickSB = new StringBuilder();
        while (sClickMatcher.find()) {
            String sclick = sClickMatcher.group(1);
            //sclick 只能转换商品的，618大促活动的不行
            DtkParseContentResponse response = tklExtractor.convert(sclick);
            if (response == null) {
                //转换失败就尝试用活动去转
                DtkActivityLinkResponse myTKL = tbActivityConvertor.convert(sclick);
                if (myTKL == null) {
                    logger.error("convert failed{}", content);
                    throw new RuntimeException("convert failed");
                }
                content = content.replaceAll(sclick, myTKL.getLongTpwd());
            } else {
                String dataType = response.getDataType();
                if (TKLExtractor.DATATYPE_ACTIVITY.equals(dataType)) {
                    DtkActivityLinkResponse myTKL = tbActivityConvertor.convert(response.getGoodsId());
                    if (myTKL == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    sClickMatcher.appendReplacement(sClickSB,myTKL.getLongTpwd());

                } else {
                    String goodsId = response.getGoodsId();
                    DtkGetPrivilegeLinkResponse convert = goodsConvertor.convert(goodsId);
                    if (convert == null) {
                        logger.error("convert failed{}", content);
                        throw new RuntimeException("convert failed");
                    }
                    sClickMatcher.appendReplacement(sClickSB,convert.getShortUrl());
                }
            }
        }
        sClickMatcher.appendTail(sClickSB);
        content = sClickSB.toString();

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
//                //todo 转成短链接
//                String dtkActivityLinkResponse = shortUrlConvertor.convert(tkl);
//                if (dtkActivityLinkResponse == null) {
//                    logger.error("convert failed{}", content);
//                    throw new RuntimeException("convert failed");
//                }
//                String longTpwd = dtkActivityLinkResponse;
//                tbUrl.appendReplacement(sb1,longTpwd);
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


    public static void setLogger(Logger logger) {
        AssignMessageResolver.logger = logger;
    }

    public static void setPattern(Pattern pattern) {
        AssignMessageResolver.pattern = pattern;
    }

    public static void setJdUrlpattern(Pattern jdUrlpattern) {
        AssignMessageResolver.jdUrlpattern = jdUrlpattern;
    }

    public static void setShortTbUrlpattern(Pattern shortTbUrlpattern) {
        AssignMessageResolver.shortTbUrlpattern = shortTbUrlpattern;
    }

    public static void setsClickUrlpattern(Pattern sClickUrlpattern) {
        AssignMessageResolver.sClickUrlpattern = sClickUrlpattern;
    }

    public static void setKuaiZhanUrlpattern(Pattern kuaiZhanUrlpattern) {
        AssignMessageResolver.kuaiZhanUrlpattern = kuaiZhanUrlpattern;
    }


    public void setTklConvertor(TKLConvertor tklConvertor) {
        this.tklConvertor = tklConvertor;
    }

    public void setJdUrlConvertor(JDUrlConvertor jdUrlConvertor) {
        this.jdUrlConvertor = jdUrlConvertor;
    }

    public void setTklExtractor(TKLExtractor tklExtractor) {
        this.tklExtractor = tklExtractor;
    }

    public void setTbActivityConvertor(TBActivityConvertor tbActivityConvertor) {
        this.tbActivityConvertor = tbActivityConvertor;
    }

    public void setGoodsConvertor(GoodsConvertor goodsConvertor) {
        this.goodsConvertor = goodsConvertor;
    }

    public void setKuaiZhanConvertor(KuaiZhanConvertor kuaiZhanConvertor) {
        this.kuaiZhanConvertor = kuaiZhanConvertor;
    }
}
