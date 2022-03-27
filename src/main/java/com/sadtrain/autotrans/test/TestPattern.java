package com.sadtrain.autotrans.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(".*(￥.*￥).*");
        String str = "我是￥date4564￥地方噶";
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));
        boolean b = matcher.find();
        System.out.println(matcher.group());
    }
}
