package com.sadtrain.autotrans.api;

import com.sadtrain.autotrans.api.response.DtkTwdToTwdResponse;
import com.sadtrain.autotrans.api.response.base.BaseResponse;
import com.sadtrain.autotrans.mirai.listener.LJLGroupListener;
import net.mamoe.mirai.message.data.PlainText;
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
public class TKLConvertor {

    private static Logger logger = LoggerFactory.getLogger(TKLConvertor.class);

    private RestTemplate restTemplate;

    String appSecret = "53aed3457f5fd4f04c72b6378eccb03f";//应用sercret
    String appKey = "623e87e1c0d83"; //应用key
    String host = "https://openapi.dataoke.com/api/tb-service/twd-to-twd?version={version}&appKey={appKey}&pid={pid}&content={content}&sign={sign}";//应用服务接口

    public String convert(String tkl){
        TreeMap<String,String> paraMap = new TreeMap<>();
        paraMap.put("appKey",appKey);
        paraMap.put("content", tkl);
        paraMap.put("pid", "mm_50644486_2601550374_111953300012");
        paraMap.put("version","v1.0.0");
        String signStr = SignMD5Util.getSignStr(paraMap, appSecret);
        System.out.println(signStr);
        paraMap.put("sign", signStr);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BaseResponse<DtkTwdToTwdResponse>> forEntity = restTemplate.exchange(host, HttpMethod.GET,null, new ParameterizedTypeReference<BaseResponse<DtkTwdToTwdResponse>>() {}, paraMap);
//        ResponseEntity<DtkTwdToTwdResponse> forEntity = restTemplate.getForEntity(host, DtkTwdToTwdResponse.class, paraMap);
        logger.info(forEntity.getBody().toString());
        return forEntity.getBody().getData().getTpwd();
    }

    public static void main(String[] args) {
        TKLConvertor convertor = new TKLConvertor();
        String str = "\n" +
                "官方标配华强北4代蓝牙耳机，开机自动匹配，高清音质，物理降噪，开盖显示电量！！可用六小时的电量，支持无线充电，智能指纹触控，入耳检测,单双耳切换，支持双耳通话,听歌电影游戏都不延迟。\n" +
                "--------------------------------------\n" +
                "【下单方法】长按复制这条信息，打开手机淘宝，可领券并下单口令￥MEhf26dbqNt￥" +
                "安卓手机还可以下载我们的自助找优惠券app 下载地址（官方邀请码000000）：http://app1.ffquan.com/apk/1444625/richangxiaohui.apk?t=16404156551612";
        str = "1￥NrsL263IyXE￥/";

        Pattern pattern = Pattern.compile("[\\s\\S]*(￥.*￥)[\\s\\S]*");
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            String tkl = matcher.group(1);
//                        System.out.println(matcher.group(1));
            String myTKL = convertor.convert(tkl);
            assert myTKL != null;
            System.out.println(myTKL);
        }else{

        }
    }
}
