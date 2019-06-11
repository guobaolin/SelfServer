package com.gbl;

/**
 * Created by guobaolin on 2019/2/14.
 * @author guobaolin
 */
public class Student extends Human{

    @Deprecated
    private String name;

    public Student() {
    }

    private Student(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("hahaha");
    }

    private class Read{

    }

}
