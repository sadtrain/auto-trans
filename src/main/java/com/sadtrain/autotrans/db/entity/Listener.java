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
@TableName("t_listener")
public class Listener implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Long botNum;

    private Long groupNum;


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

    public Long getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Long groupNum) {
        this.groupNum = groupNum;
    }

    @Override
    public String toString() {
        return "Listener{" +
        "id=" + id +
        ", botNum=" + botNum +
        ", groupNum=" + groupNum +
        "}";
    }
}
