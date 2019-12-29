package com.gbl;


import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void thresholdTest(int initialCapacity) throws Exception {
        Map<String, String> map = new HashMap<>(initialCapacity);

        // 获取map扩容时临界阈值 阈值 = 容量 * 加载因子
        // 默认容量 为16，加载因子 默认为0.75
        Field threshold = map.getClass().getDeclaredField("threshold");
        Field size = map.getClass().getDeclaredField("size");
        Method capacity = map.getClass().getDeclaredMethod("capacity");

        threshold.setAccessible(true);
        size.setAccessible(true);
        capacity.setAccessible(true);

        // 未存放对象时，各项值测试
        System.out.println("start:临界值" + threshold.get(map));
        System.out.println("start:size" + size.get(map));
        System.out.println("start:容量" + capacity.invoke(map));

        // 临界值、容量测试
        for (int i = 1; i < 15; i++) {
            map.put(String.valueOf(i), i + "**");
            System.out.println("第" + i + "个对象，size为" + size.get(map) + ",临界值为" + threshold.get(map) + ",容量为" + capacity.invoke(map));
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
        thresholdTest(9);
    }

    @Test
    public void test2() {
        HttpMethod httpMethod = HttpMethod.GET;
    }
}
