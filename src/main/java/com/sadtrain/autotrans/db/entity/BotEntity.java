package com.sadtrain.autotrans.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zgs
 * @since 2022-05-29
 */
@TableName("t_bot")
public class BotEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Long botNum;

    private String username;

    private String password;

    private Boolean enable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBotNum() {
        return botNum;
    }

    public void setBotNum(Long botNum) {
        this.botNum = botNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Bot{" +
        "id=" + id +
        ", botNum=" + botNum +
        ", username=" + username +
        ", password=" + password +
        ", enable=" + enable +
        "}";
    }
}
