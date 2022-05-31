package com.sadtrain.autotrans.api;

import com.sadtrain.autotrans.api.response.DtkJdCommodityTransformLinkResponse;
import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.api.response.base.BaseResponse;
import com.sadtrain.autotrans.api.util.Patterns;
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
public class JDUrlConvertor {

    private static Logger logger = LoggerFactory.getLogger(JDUrlConvertor.class);

    private RestTemplate restTemplate;

    String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
    String appKey = "623e87e1c0d83"; //应用key
    String host = "https://openapi.dataoke.com/api/dels/jd/kit/promotion-union-convert?version={version}&appKey={appKey}&unionId={unionId}&materialId={materialId}&sign={sign}";//应用服务接口

    public String convert(String tkl){
        TreeMap<String,String> paraMap = new TreeMap<>();
        paraMap.put("appKey",appKey);

        paraMap.put("unionId", "2025201234");
        paraMap.put("materialId", tkl);
        paraMap.put("version","v1.0.0");
        String signStr = SignMD5Util.getSignStr(paraMap, appSecret);
        paraMap.put("sign", signStr);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaseResponse<DtkJdCommodityTransformLinkResponse>> forEntity = restTemplate.exchange(host, HttpMethod.GET,null, new ParameterizedTypeReference<BaseResponse<DtkJdCommodityTransformLinkResponse>>() {}, paraMap);
//        ResponseEntity<DtkTwdToTwdResponse> forEntity = restTemplate.getForEntity(host, DtkTwdToTwdResponse.class, paraMap);
        logger.info(forEntity.getBody().toString());
        return forEntity.getBody().getData().getShortUrl();
    }

    public static void main(String[] args) {
        JDUrlConvertor convertor = new JDUrlConvertor();
        String str = "\n" +
                "官方标配华强北4代蓝牙耳机，开机自动匹配，高清音质，物理降噪，开盖显示电量！！可用六小时的电量，支持无线充电，智能指纹触控，入耳检测,单双耳切换，支持双耳通话,听歌电影游戏都不延迟。\n" +
                "--------------------------------------\n" +
                "【下单方法】长按复制这条信息，打开手机淘宝，可领券并下单口令￥MEhf26dbqNt￥" +
                "安卓手机还可以下载我们的自助找优惠券app 下载地址（官方邀请码000000）：http://app1.ffquan.com/apk/1444625/richangxiaohui.apk?t=16404156551612";
//        str = "1￥NrsL263IyXE￥/";
        str = "京东红包每日领3次, 重复进\n" +
                "https://u.jd.com/lIKIij5";
        Pattern pattern = Pattern.compile(".*(https\\:\\/\\/u\\.jd\\.com\\/\\w+).*");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        String group = matcher.group(1);
        String myTKL = convertor.convert(group);
            assert myTKL != null;
            System.out.println(myTKL);
//        Matcher matcher = Patterns.WEB_URL.matcher(str);
//        if(matcher.matches()){
//            String tkl = matcher.group(1);
//            System.out.println(tkl);
//            String myTKL = convertor.convert(tkl);
//            assert myTKL != null;
//            System.out.println(myTKL);
//        }else{
//
//        }
    }
}
