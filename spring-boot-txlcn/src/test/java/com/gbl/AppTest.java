package com.gbl;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        Map<String, String> map = new HashMap<>();
        map.put("guo", "baolin");
        String name = map.remove("guo");
        System.out.println(name);
    }

    @Test
    public void test1() {
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> integers = new ArrayList<>();
        integers.add(666);
        integers.add(777);
        map.put("guobaolin", integers);

        List<Integer> guo = map.get("guobaolin");
        if (CollectionUtils.isEmpty(guo)) {
            return;
        }
        guo.remove(666);

        List<Integer> bao = map.get("guobaolin");
        System.out.println(guo);
    }

    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("productId", 1234);
        map.put("deviceId", 111);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("productId", 12345555);

        map.putAll(map1);

        String deviceId = map.get("productId").toString();
        System.out.println(deviceId);
    }

    @Test
    public void test3() {
        List<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("guo");
        strings.add(null);

        System.out.println(strings);

    }
}
