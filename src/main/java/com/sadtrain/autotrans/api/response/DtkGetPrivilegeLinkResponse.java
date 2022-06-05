package com.sadtrain.autotrans.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 高效转链响应结果实体
 *
 * @author 1
 * @date 2020/11/30 15:38
 */
public class DtkGetPrivilegeLinkResponse {
    private String couponClickUrl;

    private String couponEndTime;

    private String couponInfo;

    private String couponStartTime;

    private String itemId;

    private String couponTotalCount;

    private String couponRemainCount;

    @ApiModelProperty(value = "商品淘客链接")
    private String itemUrl;

    @ApiModelProperty(value = "淘口令")
    private String tpwd;

    @ApiModelProperty(value = "佣金比率")
    private String maxCommissionRate;

    @ApiModelProperty(value = "短链接")
    private String shortUrl;

    @ApiModelProperty(value = "当传入请求参数channelId、specialId、externalId时，该字段展示预估最低佣金率(%)")
    private String minCommissionRate;

    @ApiModelProperty(value = "针对iOS14版本，增加对应能解析的长口令")
    private String longTpwd;

    @ApiModelProperty(value = "微信防封二维码")
    private String kuaiZhanUrl;

    @ApiModelProperty(value = "商品原价(2020/12/30新增字段)")
    private String originalPrice;

    @ApiModelProperty(value = "券后价(2020/12/30新增字段)")
    private String actualPrice;

    @Override
    public String toString() {
        return "DtkGetPrivilegeLinkResponse{" +
                "couponClickUrl='" + couponClickUrl + '\'' +
                ", couponEndTime='" + couponEndTime + '\'' +
                ", couponInfo='" + couponInfo + '\'' +
                ", couponStartTime='" + couponStartTime + '\'' +
                ", itemId='" + itemId + '\'' +
                ", couponTotalCount='" + couponTotalCount + '\'' +
                ", couponRemainCount='" + couponRemainCount + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", maxCommissionRate='" + maxCommissionRate + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", minCommissionRate='" + minCommissionRate + '\'' +
                ", longTpwd='" + longTpwd + '\'' +
                ", kuaiZhanUrl='" + kuaiZhanUrl + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", actualPrice='" + actualPrice + '\'' +
                '}';
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(String couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public String getCouponRemainCount() {
        return couponRemainCount;
    }

    public void setCouponRemainCount(String couponRemainCount) {
        this.couponRemainCount = couponRemainCount;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getTpwd() {
        return tpwd;
    }

    public void setTpwd(String tpwd) {
        this.tpwd = tpwd;
    }

    public String getMaxCommissionRate() {
        return maxCommissionRate;
    }

    public void setMaxCommissionRate(String maxCommissionRate) {
        this.maxCommissionRate = maxCommissionRate;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getMinCommissionRate() {
        return minCommissionRate;
    }

    public void setMinCommissionRate(String minCommissionRate) {
        this.minCommissionRate = minCommissionRate;
    }

    public String getLongTpwd() {
        return longTpwd;
    }

    public void setLongTpwd(String longTpwd) {
        this.longTpwd = longTpwd;
    }

    public String getKuaiZhanUrl() {
        return kuaiZhanUrl;
    }

    public void setKuaiZhanUrl(String kuaiZhanUrl) {
        this.kuaiZhanUrl = kuaiZhanUrl;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }
}
