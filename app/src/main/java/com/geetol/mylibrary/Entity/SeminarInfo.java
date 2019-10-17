package com.geetol.mylibrary.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Info     ： Create by Zeoy
 * Introduce： 专题通用bean
 * Date     ： 2018/12/5
 */
public class SeminarInfo implements Serializable {
    private String img;
    @SerializedName("name")
    private String title;
    private String info;   //简单的介绍 后台在名称里面设置  用“|”分割
    private int value;
    private String sort;
    private String code;

    @SerializedName("f4")
    private String introduce;   //简介
    private int number;         //故事数量

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        if (this.title.contains("|")){
            String[] chars = title.split("\\|");
            return chars[0];
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        if (this.title.contains("|")){
            String[] chars = title.split("\\|");
            return chars[1];
        }
        return title;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SeminarInfo{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", value='" + value + '\'' +
                ", sort='" + sort + '\'' +
                ", code='" + code + '\'' +
                ", introduce='" + introduce + '\'' +
                ", number=" + number +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
