package com.gbl.reflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: guobaolin
 * @date: 2020/6/16
 */
public class ReflectTest {

    @Test
    public void test1() {

        Method[] methods = Cat.class.getMethods();

        Method[] declaredMethods = Cat.class.getDeclaredMethods();

        System.out.println("methods =====> ");
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println("declaredMethods ======> ");
        Arrays.stream(declaredMethods).forEach(System.out::println);

        Cat cat = new Cat();
        Class<? extends Cat> clazz = cat.getClass();

        try {
            Class<?> aClass = Class.forName("com.gbl.reflect.Cat");

            Object o = aClass.newInstance();

            Method run = aClass.getDeclaredMethod("run");
            Object invoke = run.invoke(o, null);
            System.out.println(invoke);


            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(111);

        Class<?> clazz = Class.forName("java.util.ArrayList");
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list, "zhangsan");

        for (Object o : list) {
            System.out.println(o);
        }
    }


}
