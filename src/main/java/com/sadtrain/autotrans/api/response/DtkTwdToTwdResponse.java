package com.sadtrain.autotrans.api.response;

/**
 * 淘口令转淘口令响应结果实体
 *
 * @author 1
 * @date 2020/11/11 16:14
 */
public class DtkTwdToTwdResponse {
    private String itemId;
    private String tpwd;
    private String longTpwd;
    private String maxCommissionRate;
    private String minCommissionRate;
    private String originUrl;
    private String title;
    private String couponClickUrl;
    private String couponInfo;
    private String couponEndTime;
    private String couponStartTime;
    private String couponTotalCount;
    private String couponRemainCount;
    private String shortUrl;

    @Override
    public String toString() {
        return "DtkTwdToTwdResponse{" +
                "itemId='" + itemId + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", longTpwd='" + longTpwd + '\'' +
                ", maxCommissionRate='" + maxCommissionRate + '\'' +
                ", minCommissionRate='" + minCommissionRate + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", title='" + title + '\'' +
                ", couponClickUrl='" + couponClickUrl + '\'' +
                ", couponInfo='" + couponInfo + '\'' +
                ", couponEndTime='" + couponEndTime + '\'' +
                ", couponStartTime='" + couponStartTime + '\'' +
                ", couponTotalCount='" + couponTotalCount + '\'' +
                ", couponRemainCount='" + couponRemainCount + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTpwd() {
        return tpwd;
    }

    public void setTpwd(String tpwd) {
        this.tpwd = tpwd;
    }

    public String getLongTpwd() {
        return longTpwd;
    }

    public void setLongTpwd(String longTpwd) {
        this.longTpwd = longTpwd;
    }

    public String getMaxCommissionRate() {
        return maxCommissionRate;
    }

    public void setMaxCommissionRate(String maxCommissionRate) {
        this.maxCommissionRate = maxCommissionRate;
    }

    public String getMinCommissionRate() {
        return minCommissionRate;
    }

    public void setMinCommissionRate(String minCommissionRate) {
        this.minCommissionRate = minCommissionRate;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
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

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public static void main(String[] args) {
        double v = Double.parseDouble("32 ");
        System.out.println(v);
    }
}
