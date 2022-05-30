package com.sadtrain.autotrans.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zgs
 * @since 2022-05-29
 */
@TableName("t_consumer")
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer listenerId;

    private String toGroupNums;

    @TableField(exist = false)
    private List<Long> toGroupNumList;

    public List<Long> getToGroupNumList() {
        if(toGroupNumList == null){
            String[] split = toGroupNums.split(",");
            toGroupNumList = new ArrayList<>();
            for (String s : split) {
                toGroupNumList.add(Long.parseLong(s));
            }
        }
        return toGroupNumList;
    }

    public void setToGroupNumList(List<Long> toGroupNumList) {
        this.toGroupNumList = toGroupNumList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListenerId() {
        return listenerId;
    }

    public void setListenerId(Integer listenerId) {
        this.listenerId = listenerId;
    }

    public String getToGroupNums() {
        return toGroupNums;
    }

    public void setToGroupNums(String toGroupNums) {
        this.toGroupNums = toGroupNums;
    }

    @Override
    public String toString() {
        return "Consumer{" +
        "id=" + id +
        ", listenerId=" + listenerId +
        ", toGroupNums=" + toGroupNums +
        "}";
    }
}
