package com.gbl;

import static org.junit.Assert.assertTrue;

import com.gbl.entity.Student;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test1(){
        int[] a = AppTest.of(1, 2, 3, 4);
        System.out.println(a);
    }

    public static int[] of(int... values){
        return values;
    }

    @Test
    public void test2(){
        Set<Student> hashSet = new HashSet<>();
        hashSet.add(new Student(1, "zhao", 11, "het"));
        hashSet.add(new Student(2, "qian", 12, "het"));
        hashSet.add(new Student(3, "sun", 13, "het"));
        System.out.println(hashSet);

        Set<Student> treeSet = new TreeSet<>(hashSet);
        System.out.println(treeSet);
    }
}
