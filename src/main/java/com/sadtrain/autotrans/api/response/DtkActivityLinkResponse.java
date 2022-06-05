package com.sadtrain.autotrans.api.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 官方活动会场转链响应结果实体
 *
 * @author:YSH
 * @date: 2021/7/05
 * @time: 09:57
 */
public class DtkActivityLinkResponse {
    @JsonAlias("page_name")
    private String pageName;
    @JsonAlias("click_url")
    private String clickUrl;
    @JsonProperty("Tpwd")
    private String tpwd;
    @JsonAlias("longTpwd")
    private String longTpwd;
    @JsonAlias("terminal_type")
    private String terminalType;
    @JsonAlias("page_start_time")
    private String pageStartTime;
    @JsonAlias("page_end_time")
    private String pageEndTime;
    @JsonAlias("wx_qrcode_url")
    private String wxQrcodeUrl;
    @JsonAlias("wx_miniprogram_path")
    private String wxMiniProgramPath;

    @Override
    public String toString() {
        return "DtkActivityLinkResponse{" +
                "pageName='" + pageName + '\'' +
                ", clickUrl='" + clickUrl + '\'' +
                ", tpwd='" + tpwd + '\'' +
                ", longTpwd='" + longTpwd + '\'' +
                ", terminalType='" + terminalType + '\'' +
                ", pageStartTime='" + pageStartTime + '\'' +
                ", pageEndTime='" + pageEndTime + '\'' +
                ", wxQrcodeUrl='" + wxQrcodeUrl + '\'' +
                ", wxMiniProgramPath='" + wxMiniProgramPath + '\'' +
                '}';
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
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

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getPageStartTime() {
        return pageStartTime;
    }

    public void setPageStartTime(String pageStartTime) {
        this.pageStartTime = pageStartTime;
    }

    public String getPageEndTime() {
        return pageEndTime;
    }

    public void setPageEndTime(String pageEndTime) {
        this.pageEndTime = pageEndTime;
    }

    public String getWxQrcodeUrl() {
        return wxQrcodeUrl;
    }

    public void setWxQrcodeUrl(String wxQrcodeUrl) {
        this.wxQrcodeUrl = wxQrcodeUrl;
    }

    public String getWxMiniProgramPath() {
        return wxMiniProgramPath;
    }

    public void setWxMiniProgramPath(String wxMiniProgramPath) {
        this.wxMiniProgramPath = wxMiniProgramPath;
    }
}
