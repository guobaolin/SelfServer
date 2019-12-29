package com.gbl.test;

/**
 * Created by guobaolin on 2019/9/29.
 */
public class Outer {
    int out_x = 0;

    String b = Inner1.con;

    public static void method() {

//        Inner1 inner1 = new Inner1();
        String a = Inner1.con;
        class Inner2 {
            public void method2() {
//                out_x = 3;
                String aa = a;
            }
        }
        Inner2 inner2 = new Inner2();
    }

    public class Inner1 {

        public static final String con = "Hello";

    }

}

