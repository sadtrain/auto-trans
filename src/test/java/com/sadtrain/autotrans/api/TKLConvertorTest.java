package com.sadtrain.autotrans.api;

import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.api.response.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TKLConvertorTest {
    public static void main(String[] args) {
        TKLConvertor convertor = new TKLConvertor();
        String str = "\n" +
                "官方标配华强北4代蓝牙耳机，开机自动匹配，高清音质，物理降噪，开盖显示电量！！可用六小时的电量，支持无线充电，智能指纹触控，入耳检测,单双耳切换，支持双耳通话,听歌电影游戏都不延迟。\n" +
                "--------------------------------------\n" +
                "【下单方法】长按复制这条信息，打开手机淘宝，可领券并下单口令￥MEhf26dbqNt￥" +
                "安卓手机还可以下载我们的自助找优惠券app 下载地址（官方邀请码000000）：http://app1.ffquan.com/apk/1444625/richangxiaohui.apk?t=16404156551612";
//        str = "1￥NrsL263IyXE￥/";
        str = "￥6kXg2BTHKeg￥";

        Pattern pattern = Pattern.compile("[\\s\\S]*(￥.*￥)[\\s\\S]*");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            String tkl = matcher.group(1);
                        System.out.println(matcher.group(1));
            DtkTwdToTwdResponse convert = convertor.convert(tkl);
            System.out.println(convert);
//            String myTKL = (String) convert;
//            assert myTKL != null;
//            System.out.println(myTKL);
        }else{

        }
    }
}
