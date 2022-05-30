package com.sadtrain.autotrans.web.request;

public class AddBotRequest {

    private Long botNum;
    private String password;
    private Boolean directLogin;

    public Long getBotNum() {
        return botNum;
    }

    public void setBotNum(Long botNum) {
        this.botNum = botNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDirectLogin() {
        return directLogin;
    }

    public void setDirectLogin(Boolean directLogin) {
        this.directLogin = directLogin;
    }
}
