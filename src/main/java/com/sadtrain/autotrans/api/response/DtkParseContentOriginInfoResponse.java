package com.sadtrain.autotrans.api.response;

import java.math.BigDecimal;

/**
 * 淘系万能解析响应链接中的信息实体
 *
 * @author 1
 * @date 2020/11/11 16:06
 */

public class DtkParseContentOriginInfoResponse {
    private String title;
    private String shopName;
    private String shopLogo;
    private String image;
    private String startTime;
    private String endTime;
    private BigDecimal amount;
    private BigDecimal startFee;
    private BigDecimal price;
    private BigDecimal actualPrice;
    private String activityId;
    private String pid;
    private Integer status;

    @Override
    public String toString() {
        return "DtkParseContentOriginInfoResponse{" +
                "title='" + title + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopLogo='" + shopLogo + '\'' +
                ", image='" + image + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", amount=" + amount +
                ", startFee=" + startFee +
                ", price=" + price +
                ", actualPrice=" + actualPrice +
                ", activityId='" + activityId + '\'' +
                ", pid='" + pid + '\'' +
                ", status=" + status +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getStartFee() {
        return startFee;
    }

    public void setStartFee(BigDecimal startFee) {
        this.startFee = startFee;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
