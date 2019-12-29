package com.gbl.test;

/**
 * Created by guobaolin on 2019/9/27.
 */
public class HelloNative {

    static {
        System.loadLibrary("Test");
    }

    public static void main(String[] args) {
        new HelloNative().sayHello("gbl");
    }

    public native void sayHello(String name);
}
