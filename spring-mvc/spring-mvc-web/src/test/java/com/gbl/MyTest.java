package com.gbl;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyTest {

    @Test
    public void test1() {
        int i = 1;
        i = i++;
        System.out.println("i1=" + i);
        int j = i++;
        System.out.println("i2=" + i);
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }

}
