package com.sadtrain.autotrans.api.response;
import java.math.BigDecimal;

/**
 * 淘系万能解析响应结果实体
 *
 * @author 1
 * @date 2020/11/11 16:02
 */
public class DtkParseContentResponse {
    private String goodsId;
    private String originUrl;
    private String originType;
    private String commissionType;
    private BigDecimal commissionRate;
    private DtkParseContentOriginInfoResponse originInfo;
    private String itemId;
    private String itemName;
    private String mainPic;
    private String dataType;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public DtkParseContentOriginInfoResponse getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(DtkParseContentOriginInfoResponse originInfo) {
        this.originInfo = originInfo;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
