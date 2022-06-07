package com.sadtrain.autotrans.db.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private String keyWords;

    @TableField(exist = false)
    private List<String> keyWordsList;

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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getKeyWordsList() {
        if(keyWordsList == null){
            String[] split = keyWords.split(",");
            keyWordsList = new ArrayList<>();
            keyWordsList.addAll(Arrays.asList(split));
        }
        return keyWordsList;
    }

    public void setKeyWordsList(List<String> keyWordsList) {
        this.keyWordsList = keyWordsList;
    }

    @Override
    public String toString() {
        return "Listener{" +
                "id=" + id +
                ", botNum=" + botNum +
                ", groupNum=" + groupNum +
                ", keyWords=" + keyWords +
                "}";
    }
}
