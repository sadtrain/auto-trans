package com.sadtrain.autotrans.resolver;

import com.sadtrain.autotrans.api.ShortUrlConvertor;
import com.sadtrain.autotrans.api.TKLConvertor;
import com.sadtrain.autotrans.api.TKLExtractor;
import com.sadtrain.autotrans.mirai.resolver.AssignMessageResolver;
import org.junit.Test;

public class AssignMessageResolverTest {

    @Test
    public void test(){
        AssignMessageResolver resolver = new AssignMessageResolver();
        resolver.setTklConvertor(new TKLConvertor());
        resolver.setTklExtractor(new TKLExtractor());
        resolver.setShortUrlConvertor(new ShortUrlConvertor());
        String str = "39亓 强生旗下&安视优\n" +
                "安视优舒日隐形日抛10片\n" +
                "https://m.Tb.cn/h.Ud3PSC0 \n" +
                "6￥fY8F2B68gMD￥:/ AC666/\n" +
                "50-1200度数 水凝胶材质\n" +
                "主打高透氧性和高清晰度！\n" +
                "久戴不干涩 性价比直接拉满";
        resolver.handlerText(str);
    }
}
