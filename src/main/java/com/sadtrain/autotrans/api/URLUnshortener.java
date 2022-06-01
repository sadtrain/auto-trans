package com.sadtrain.autotrans.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Closeables;

/**
 * Expand short urls. Works with all the major url shorteners (t.co, bit.ly, fb.me, is.gd, goo.gl, etc).
 * @author Pedro Oliveira
 *
 */
public class URLUnshortener {

    public static void main(String[] args) throws IOException {
        //jar包都是JDK自带的，不需要添加额外的JAR包
        String location = "https://s.click.taobao.com/gEGiMXu?unappch=df_event&ext_share_param=%7B%22img%22%3A%22https%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2FO1CN0194Olpp1UfCLjJmQYm_%21%216000000002544-2-tps-800-450.png%22%2C%22text%22%3A%22618%E8%B6%85%E7%BA%A2%E4%B8%BB%E4%BC%9A%E5%9C%BA%22%2C%22type%22%3A%22event%22%7D&pid=mm_50644486_2603600474_111953300373&traffic_flag=lm&scm=20140618.1.01010001.s101c6&spm=a2e0r.27077076.1_117-MCYxwf20210308.dslot_1_share_20150318020008790&union_lens=lensId%3AAPP%401653911906%40213ff09a_0ea8_18114d4e9d5_aacd%400210qMThtm9hw1SwP2Id6UtV&un=460ede3304401a343dd3af49e2902fce&share_crt_v=1&un_site=0&ut_sk=1.utdid_null_1653911906793.TaoPassword-Outside.lianmeng-app&sp_tk=VFo4RDJseEFmNm8%3D&cpp=1&shareurl=true&short_name=h.ftMATZQ";
        String url = "https://s.click.taobao.com/gEGiMXu?unappch=df_event&ext_share_param=%7B%22img%22%3A%22https%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi1%2FO1CN0194Olpp1UfCLjJmQYm_%21%216000000002544-2-tps-800-450.png%22%2C%22text%22%3A%22618%E8%B6%85%E7%BA%A2%E4%B8%BB%E4%BC%9A%E5%9C%BA%22%2C%22type%22%3A%22event%22%7D&pid=mm_50644486_2603600474_111953300373&traffic_flag=lm&scm=20140618.1.01010001.s101c6&spm=a2e0r.27077076.1_117-MCYxwf20210308.dslot_1_share_20150318020008790&union_lens=lensId%3AAPP%401653911906%40213ff09a_0ea8_18114d4e9d5_aacd%400210qMThtm9hw1SwP2Id6UtV&un=460ede3304401a343dd3af49e2902fce&share_crt_v=1&un_site=0&ut_sk=1.utdid_null_1653911906793.TaoPassword-Outside.lianmeng-app&sp_tk=VFo4RDJseEFmNm8%3D&cpp=1&shareurl=true&short_name=h.ftMATZQ";//这个链接就是上面写的链接
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.connect();
            location = conn.getHeaderField("Location");
            String tulj = location.replace("https://s.click.taobao.com/t_js?tu=", "");
            String refchangtu = URLDecoder.decode(tulj, "utf-8");
            String changrealurl = tulianjie(refchangtu, location);
            System.err.println("获取到的真实url：" + changrealurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String tulianjie(String url,String tu){
        String location = "";
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.addRequestProperty("Referer", tu);
            conn.connect();
            location = conn.getHeaderField("Location");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

}