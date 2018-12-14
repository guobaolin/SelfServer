package com.gbl.mybatis.example.entity;

/**
 * Created by guobaolin on 2018/11/5.
 */
public class NewString {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NewString{" +
                "value='" + value + '\'' +
                '}';
    }
}
