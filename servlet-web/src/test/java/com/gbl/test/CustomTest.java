package com.gbl.test;

import org.junit.Test;

/**
 * Created by guobaolin on 2019/9/27.
 */
public class CustomTest extends MyTestBean {

    @Test
    public void test1() {
//        int a = Integer.MAX_VALUE;
//        System.out.println(a);
//
//        String b = Outer.Inner1.con;

        try {
            System.exit(0);
        } catch (Exception e) {
            System.out.println("hello world");
        }
    }

    public String toBean() {
        return "";
    }

    public String toBean(String name) {
        return "";
    }

}
