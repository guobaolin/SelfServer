package com.gbl.test;

/**
 * Created by guobaolin on 2019/8/12.
 */
public abstract class MyTestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    private String toBean() {
        return null;
    }

    private String toBean(String name) {
        return "";
    }

}
