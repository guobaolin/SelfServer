package com.gbl;

import static org.junit.Assert.assertTrue;

import com.gbl.test.UserService;
import org.junit.Test;

import java.io.File;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.util.List;

public class AppTest {

    @Test
    public void test1() throws ClassNotFoundException {
        Student student = new Student();
        Class<?> s1 = Student.class;
        Class<?> s2 = student.getClass();
        Class<?> s3 = Class.forName("com.gbl.Student");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
    }

    @Test
    public void test2() {
        Class<?> c = Student.class;
        Student[][] studen = {{new Student()}};
        Human human = new Student();
        System.out.println(studen.getClass().getCanonicalName());
        System.out.println(studen.getClass().getTypeName());
        System.out.println(studen.getClass().getComponentType().getCanonicalName());
        System.out.println(studen.getClass().getName());
        System.out.println("==================================");
        Constructor<?>[] cc = c.getConstructors();
        for (Constructor clazz : cc) {
            System.out.println(clazz.getName());
        }
    }

    @Test
    public void test3() {
        UserService userService = new UserService();
        System.out.println(userService.getClass().getGenericSuperclass().getTypeName());
        System.out.println(userService.getClass().getSuperclass().getTypeName());
        System.out.println(userService.getClass().getSuperclass().getName());
        System.out.println(userService.getClass().getName());
        System.out.println(userService.getClass().getPackage().getName());
        Type[] u = userService.getClass().getGenericInterfaces();
        for (Type us : u) {
            System.out.println(us.getTypeName());
        }
    }

    @Test
    public void test4() throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = Student.class;
        Constructor constructor = c.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Student student = (Student) constructor.newInstance("gbl");
        Field field = c.getDeclaredField("name");

        System.out.println(field.getDeclaringClass().getName());
        System.out.println(field.getName());
        System.out.println(field.getType().getCanonicalName());
        System.out.println(field.getGenericType().getTypeName());
        System.out.println(field.getAnnotation(Deprecated.class).annotationType().getName());
        System.out.println(field.getAnnotatedType().getType().getTypeName());

        field.setAccessible(true);
        System.out.println(field.get(student));
        field.set(student, "guobaolin");
        System.out.println(field.get(student));

        Method method = c.getMethod("print", null);
        method.invoke(student, null);

    }
}
