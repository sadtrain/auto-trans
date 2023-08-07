//package com.sadtrain.autotrans.api;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.History;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.sadtrain.autotrans.api.response.DtkActivityLinkResponse;
//import com.sadtrain.autotrans.api.response.DtkGetPrivilegeLinkResponse;
//import com.sadtrain.autotrans.api.response.DtkParseContentResponse;
//import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
//import com.sadtrain.autotrans.api.response.base.BaseResponse;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.TreeMap;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Component
//public class ShortUrlConvertor {
//
//    private static Logger logger = LoggerFactory.getLogger(ShortUrlConvertor.class);
//
//    private RestTemplate restTemplate;
//    @Autowired
//    private TBActivityConvertor tbActivityConvertor;
//    @Autowired
//    private GoodsConvertor goodsConvertor;
//    @Autowired
//    private TKLExtractor tklExtractor;
//
//    String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
//    String appKey = "623e87e1c0d83"; //应用key
//    String host = "https://openapi.dataoke.com/api/tb-service/activity-link?promotionSceneId={promotionSceneId}&relationid={relationid}&pid={pid}&authid={authid}&sign={sign}";//应用服务接口
//    public static Pattern pattern = Pattern.compile(".*?share_(\\d{17})&.*?");
//
//    public String convert(String tkl) {
//        final WebClient[] webClient1 = new WebClient[1];
//
//        WebDriver webDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX, true) {
//            @Override
//            protected WebClient modifyWebClient(WebClient client) {
//                final WebClient webClient = super.modifyWebClient(client);
//                webClient1[0] = webClient;
//                return webClient;
//            }
//        };
//        webDriver.get(tkl);
//
//        WebClient webClient = webClient1[0];
//        int maxTry = 20;
//        int i = 0;
//        String url = null;
//        History history = null;
//        while (i < maxTry) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                logger.error("interupt",e);
//            }
//            history = webClient.getCurrentWindow().getHistory();
//            if (history.getLength() > 2) {
//                url = history.getUrl(1).toString();
//                logger.info(url);
//                break;
//            }
//            i++;
//        }
//
//        if(url == null){
//            logger.info("try extract as activity url failed");
//            if(history.getLength() == 2){
//                url = history.getUrl(1).toString();
//            }
//        }else{
//            logger.info("try extract as activity url {}",url);
//        }
//        if(url == null){
//            logger.error("extract short url failed");
//            return null;
//        }
//        logger.info("try extract as taolijin url {}",url);
//        webDriver.quit();
//        Matcher matcher = pattern.matcher(url);
//        if (matcher.find()) {
//            try {
//                DtkActivityLinkResponse response = tbActivityConvertor.convert(matcher.group(1));
//                return response.getLongTpwd();
//            } catch (Exception e) {
//                logger.error("error happend", e);
//                return null;
//            }
//        }
//            try {
//                DtkParseContentResponse convert = tklExtractor.convert(url);
//                String goodsId = convert.getGoodsId();
//                DtkGetPrivilegeLinkResponse goodsResult = goodsConvertor.convert(goodsId);
//                return goodsResult.getShortUrl();
//            } catch (Exception e) {
//                logger.error("error happend", e);
//                return null;
//            }
//    }
//
//    public static void main(String[] args) {
//        ShortUrlConvertor convertor = new ShortUrlConvertor();
//        String str = "\n" +
//                "官方标配华强北4代蓝牙耳机，开机自动匹配，高清音质，物理降噪，开盖显示电量！！可用六小时的电量，支持无线充电，智能指纹触控，入耳检测,单双耳切换，支持双耳通话,听歌电影游戏都不延迟。\n" +
//                "--------------------------------------\n" +
//                "【下单方法】长按复制这条信息，打开手机淘宝，可领券并下单口令￥MEhf26dbqNt￥" +
//                "安卓手机还可以下载我们的自助找优惠券app 下载地址（官方邀请码000000）：http://app1.ffquan.com/apk/1444625/richangxiaohui.apk?t=16404156551612";
////        str = "1￥NrsL263IyXE￥/";
////        str = "https://m.tb.cn/h.ftMATZQ";
//        str = "https://m.tb.cn/h.funt0IR ";
//        ShortUrlConvertor shortUrlConvertor = new ShortUrlConvertor();
//        String convert = shortUrlConvertor.convert(str);
//        System.out.println(convert.toString());
////
////        Matcher matcher = pattern.matcher("https://s.click.taobao.com/gEGiMXu?unappch=df_event&ext_share_param=%7B%22img%22%3A%22https%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2FO1CN0194Olpp1UfCLjJmQYm_%21%216000000002544-2-tps-800-450.png%22%2C%22text%22%3A%22618%E8%B6%85%E7%BA%A2%E4%B8%BB%E4%BC%9A%E5%9C%BA%22%2C%22type%22%3A%22event%22%7D&pid=mm_50644486_2603600474_111953300373&traffic_flag=lm&scm=20140618.1.01010001.s101c6&spm=a2e0r.27077076.1_117-MCYxwf20210308.dslot_1_share_20150318020008790&union_lens=lensId%3AAPP%401653911906%40213ff09a_0ea8_18114d4e9d5_aacd%400210qMThtm9hw1SwP2Id6UtV&un=460ede3304401a343dd3af49e2902fce&share_crt_v=1&un_site=0&ut_sk=1.utdid_null_1653911906793.TaoPassword-Outside.lianmeng-app&sp_tk=VFo4RDJseEFmNm8%3D&cpp=1&shareurl=true&short_name=h.ftMATZQ&app=firefox");
////        System.out.println(matcher.find());
//
//    }
//}
