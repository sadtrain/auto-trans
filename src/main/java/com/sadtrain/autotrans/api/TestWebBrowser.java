package com.sadtrain.autotrans.api;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.History;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.StatusHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class TestWebBrowser {

//    public static void main(String[] args) throws IOException, InterruptedException {
//        String url = "https://m.tb.cn/h.ftMATZQ";
//        HtmlUnitDriver webClient = new HtmlUnitDriver(BrowserVersion.CHROME);
//        webClient.setJavascriptEnabled(true);
////        webClient.navigate().to(url);
////        webClient.setAcceptSslCertificates(true);
//
//        webClient.get(url);
//        String currentUrl = webClient.getCurrentUrl();
//        Thread.sleep(1000);
//        webClient.getCurrentWindow().getWebClient().addWebWindowListener();
//        System.out.println(currentUrl);
////        WebClient webClient = new WebClient();
////        webClient.getOptions().setJavaScriptEnabled(true);
////        webClient.getOptions().setRedirectEnabled(true);
////        webClient.getOptions().setThrowExceptionOnScriptError(false);
////        webClient.getOptions().setCssEnabled(true);
////
//////        webClient.getweb
//////
////        HtmlPage page = (HtmlPage) webClient.getPage("https://m.tb.cn/h.ftMATZQ");
////        WebResponse response = page.getWebResponse();
//////        String content = response.getContentAsString();
////        System.out.println(page.getUrl());
////        System.out.println(content);
////        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
////        webClient.getOptions().setCssEnabled(false);
////        webClient.getOptions().setJavaScriptEnabled(true);
////        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
////        webClient.getOptions().setThrowExceptionOnScriptError(false);
////        webClient.waitForBackgroundJavaScript(600*1000);
////        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
////        HtmlPage page = webClient.getPage("https://list.jd.com/list.html?cat=9987,653,655");
////        webClient.ta
////        History history = webClient.getCurrentWindow().getHistory();
////        System.out.println(history.);
////        page.getHi
////        List<HtmlDivision> divs = (List) page.getByXPath("//div[@id='plist']//ul//li[@class='gl-item']//div[@class='gl-i-wrap j-sku-item']");
////        for(HtmlDivision  div :divs) {
////            DomNodeList<DomNode> childs = div.getChildNodes();
////            String name = "";
////            String price = "";
////            String comments = "";
////            for(DomNode dn : childs) {
////                NamedNodeMap map = dn.getAttributes();
////                Node node = map.getNamedItem("class");
////                if(node != null) {
////                    String value = node.getNodeValue();
////                    if(value.contains("p-name")) {
////                        name = dn.asText();
////                    } else if(value.contains("p-price")) {
////                        price = dn.asText();
////                    } else if(value.contains("p-commit")) {
////                        comments = dn.asText();
////                    }
////                }
////            }
////            System.out.println(name+"//"+price+"//"+comments);
////        }
//    }
public static void main(String[] args) throws InterruptedException {
//    org.apache.log4j.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(org.apache.log4j.Level.FATAL);
    java.util.logging.Logger.getLogger("com.sadtrain.autotrans.api").setLevel(Level.OFF);
//    System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "error");
    final WebClient[] webClient1 = new WebClient[1];

    WebDriver webDriver = new HtmlUnitDriver(BrowserVersion.FIREFOX, true) {
        @Override
        protected WebClient modifyWebClient(WebClient client) {
            final WebClient webClient = super.modifyWebClient(client);
            webClient1[0] = webClient;
            return webClient;
        }
    };
    webDriver.get("https://m.tb.cn/h.ftMATZQ");

    WebClient webClient = webClient1[0];
    while (true) {
        Thread.sleep(10);
        History history = webClient.getCurrentWindow().getHistory();
        if(history.getLength() > 2){
            System.out.println(history.getUrl(1));
        }
    }
//    String url = "https://m.tb.cn/h.ftMATZQ";
//    webDriver.get(url);
//    Thread.sleep(1000);
//    System.out.println("开始了3");
//    System.out.println(45678);
}
}
