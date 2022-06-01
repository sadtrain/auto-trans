package com.sadtrain.autotrans.api;

import java.net.HttpURLConnection;
import java.net.URL;


public class GetRedirectUrlTest {
    public void test_getRedirectUrl() throws Exception {
        String url="https://m.tb.cn/h.ftMATZQ";
        String expectUrl="http://www.zhihu.com/question/20583607/answer/16597802";
        String redictURL = getRedirectUrl(url);
        System.out.println(redictURL);
    }

    public static void main(String[] args) throws Exception {
        GetRedirectUrlTest test = new GetRedirectUrlTest();
        test.test_getRedirectUrl();
    }
    /**
     * 获取重定向地址
     * @param path
     * @return
     * @throws Exception
     */
    private String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        return conn.getHeaderField("Location");
    }
}