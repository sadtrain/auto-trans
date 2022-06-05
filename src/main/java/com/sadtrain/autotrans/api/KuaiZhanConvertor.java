package com.sadtrain.autotrans.api;



import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.sadtrain.autotrans.api.response.DtkGetPrivilegeLinkResponse;
import com.sadtrain.autotrans.api.response.DtkParseContentResponse;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class KuaiZhanConvertor {

    private static Logger logger = LoggerFactory.getLogger(KuaiZhanConvertor.class);

    private RestTemplate restTemplate;
    @Autowired
    private TBActivityConvertor tbActivityConvertor;

    @Autowired
    private TKLExtractor tklExtractor;

    @Autowired
    private TKLConvertor tklConvertor;

    @Autowired
    private GoodsConvertor goodsConvertor;

    String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
    String appKey = "623e87e1c0d83"; //应用key
    String host = "https://openapi.dataoke.com/api/tb-service/activity-link?promotionSceneId={promotionSceneId}&relationid={relationid}&pid={pid}&authid={authid}&sign={sign}";//应用服务接口
    public static Pattern pattern = Pattern.compile(".*?share_(\\d{17})&.*?");

    public DtkGetPrivilegeLinkResponse convert(String url) {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Browser.NewPageOptions options = new Browser.NewPageOptions();
            options.setJavaScriptEnabled(true);
            options.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
            options.setAcceptDownloads(true);
            Page page = browser.newPage(options);
            page.navigate(url);
            Locator locator = page.locator("xpath=//*[@id=\"koulin\"]");
            int i=0;
            int maxRetry=10;
            String tkl = null;
            while (i<maxRetry){
                Thread.sleep(100);
                tkl = locator.innerHTML();
                if(StringUtils.isNotBlank(tkl)){
                    break;
                }
                i++;
            }
            if(tkl == null){
                return null;
            }


            DtkParseContentResponse convert = tklExtractor.convert(tkl);
            String goodsId = convert.getGoodsId();
            DtkGetPrivilegeLinkResponse convert1 = goodsConvertor.convert(goodsId);

            if(convert == null){
                return null;
            }
            if(convert1 == null){
                return null;
            }
            return convert1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "\n" +
                "官方标配华强北4代蓝牙耳机，开机自动匹配，高清音质，物理降噪，开盖显示电量！！可用六小时的电量，支持无线充电，智能指纹触控，入耳检测,单双耳切换，支持双耳通话,听歌电影游戏都不延迟。\n" +
                "--------------------------------------\n" +
                "【下单方法】长按复制这条信息，打开手机淘宝，可领券并下单口令￥MEhf26dbqNt￥" +
                "安卓手机还可以下载我们的自助找优惠券app 下载地址（官方邀请码000000）：http://app1.ffquan.com/apk/1444625/richangxiaohui.apk?t=16404156551612";
//        str = "1￥NrsL263IyXE￥/";
//        str = "https://m.tb.cn/h.ftMATZQ";
        str = "https://yun095.kuaizhan.com/?49nI9Q";
        KuaiZhanConvertor shortUrlConvertor = new KuaiZhanConvertor();
//        DtkGetPrivilegeLinkResponse convert = shortUrlConvertor.convert(str);
//        System.out.println(convert);
//        DtkActivityLinkResponse convert = shortUrlConvertor.convert(str);
//        System.out.println(convert.toString());
//
//        Matcher matcher = pattern.matcher("https://s.click.taobao.com/gEGiMXu?unappch=df_event&ext_share_param=%7B%22img%22%3A%22https%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2FO1CN0194Olpp1UfCLjJmQYm_%21%216000000002544-2-tps-800-450.png%22%2C%22text%22%3A%22618%E8%B6%85%E7%BA%A2%E4%B8%BB%E4%BC%9A%E5%9C%BA%22%2C%22type%22%3A%22event%22%7D&pid=mm_50644486_2603600474_111953300373&traffic_flag=lm&scm=20140618.1.01010001.s101c6&spm=a2e0r.27077076.1_117-MCYxwf20210308.dslot_1_share_20150318020008790&union_lens=lensId%3AAPP%401653911906%40213ff09a_0ea8_18114d4e9d5_aacd%400210qMThtm9hw1SwP2Id6UtV&un=460ede3304401a343dd3af49e2902fce&share_crt_v=1&un_site=0&ut_sk=1.utdid_null_1653911906793.TaoPassword-Outside.lianmeng-app&sp_tk=VFo4RDJseEFmNm8%3D&cpp=1&shareurl=true&short_name=h.ftMATZQ&app=firefox");
//        System.out.println(matcher.find());

    }
}
