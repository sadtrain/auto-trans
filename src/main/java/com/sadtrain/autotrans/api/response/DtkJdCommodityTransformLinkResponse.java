package com.sadtrain.autotrans.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author:YSH
 * @date: 2021/7/7
 * @time: 17:02
 */
public class DtkJdCommodityTransformLinkResponse {
    @ApiModelProperty("商品转链后的短链接")
    private String shortUrl;
    @ApiModelProperty("商品转链后的长链接")
    private String longUrl;

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
