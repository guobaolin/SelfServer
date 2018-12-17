package com.gbl.test.io.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by guobaolin on 2018/12/10.
 */
public class ChannelTest {

    public static void main(String[] args) throws IOException {
        String filepath = "C:/Users/80021687/Desktop/file_test.txt";
        RandomAccessFile file = new RandomAccessFile(filepath, "rw");
        System.out.println("file length is:" + file.length());
        FileChannel channel = file.getChannel();

        channel.position(90);
        channel.write((ByteBuffer) ByteBuffer.allocate(1).put((byte) 65).flip());
        System.out.println("file position in RandomAccessFile is:" + file.getFilePointer());
        System.out.println("file length is:" + file.length());
    }

    @Test
    public void test1() throws IOException {
        String filepath = "C:/Users/80021687/Desktop/file.txt";
        FileInputStream inputStream = new FileInputStream(filepath);
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        byte[] bytes = new byte[80];
        fileChannel.read(buffer);
        buffer.flip(); //flip
        System.out.println("写前position=" + buffer.position() + ", remaining=" + buffer.remaining() +
                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
        buffer.get(bytes);
//        System.out.println(new String(bytes));

        char c = buffer.getChar();
        System.out.println(c);

//        ByteBuffer slice = buffer.slice();
//
//        System.out.println("slice后position=" + slice.position() + ", remaining=" + slice.remaining() +
//                ", limit=" + slice.limit() + ", capacity=" + slice.capacity());

//        System.out.println("写后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
//        buffer.mark(); //mark
//        System.out.println("mark标记并写后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
//        bytes = new byte[20];
//        buffer.get(bytes);
////        System.out.println(new String(bytes));
//        buffer.reset(); //reset
//        System.out.println("reset标记后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
//        buffer.rewind(); //rewind
//        System.out.println("rewind标记后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
//        bytes = new byte[20];
//        buffer.get(bytes);
//        buffer.mark(); //mark
//        buffer.compact(); //compact
//        System.out.println("compact标记后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
//        buffer.rewind(); //rewind
//        buffer.clear(); //clear
//        System.out.println("clear标记后position=" + buffer.position() + ", remaining=" + buffer.remaining() +
//                ", limit=" + buffer.limit() + ", capacity=" + buffer.capacity());
    }

    @Test
    public void test2() throws IOException {
//        String reg ="((\\s*[1-9](\\d*))+(\\s*,))+(!,$)";
//        String reg ="(\\s*[1-9]\\d*\\s*)(,\\s*[1-9]\\d*\\s*)*"; // [(\s*,)|((?<!,)$)]

        String reg2 ="^[^,]+(?:,[^,]+){1,20}*$";

        String reg1 ="(\\s*^,[1-9]\\d*\\s*)";
        System.out.println(" 112312 ,34, 12,12 ".matches(reg1));


//        System.out.println("123,  0123,123".matches(reg)); //false
//        System.out.println(" 123 , 123,123".matches(reg)); //true
//        System.out.println("123, 123, 123 ".matches(reg)); //true
//        System.out.println(" 123,".matches(reg)); //false
//        System.out.println(" ,123".matches(reg)); //false
//        System.out.println("123,    1 23,      123 ".matches(reg)); //false

    }
}
