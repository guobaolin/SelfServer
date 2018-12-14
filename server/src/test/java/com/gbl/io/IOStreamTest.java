package com.gbl.io;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by guobaolin on 2018/11/24.
 */
public class IOStreamTest {

    @Test
    public void test() throws IOException {

        DataOutput dataOutput = new DataOutputStream(new FileOutputStream("C:/Users/80021687/Desktop/file.txt"));

        dataOutput.write(1);
    }

    @Test
    public void test2() throws IOException {
        FileWriter writer = new FileWriter("C:/Users/80021687/Desktop/file.txt", true);
        writer.write("hello world!!!");
        writer.close();
    }

    @Test
    public void test3() throws IOException {
        FileReader reader = new FileReader("C:/Users/80021687/Desktop/file.txt");

        BufferedReader bufferedReader = new BufferedReader(reader);

        String ch = null;
        int a = 0;
        while ((ch = bufferedReader.readLine()) != null) {
            System.out.println(ch);
        }
        reader.close();
    }

    @Test
    public void test4() throws IOException {
        FileInputStream reader = new FileInputStream("C:/Users/80021687/Desktop/file.txt");

        String ch = null;
        int a = 0;
        while ((a = reader.read()) != -1) {
            System.out.println((char) a);
        }
        reader.close();
    }

    @Test
    public void test5() throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/80021687/Desktop/云接入.vsdx");

        FileOutputStream fos = new FileOutputStream("C:/Users/80021687/Desktop/11.vsdx");

        byte[] bytes = new byte[1024];
        int len = 0;

        while ((len = fis.read(bytes)) != -1) {
            if (len < 1024) {
                System.out.println(bytes);
            }
            fos.write(bytes, 0, len);
        }

        fos.close();
        fis.close();
    }

    @Test
    public void test6() {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            InputStream is = System.in;
//            InputStreamReader isr = new InputStreamReader(is, "utf-8");
//
//            br = new BufferedReader(isr);

            Scanner scanner = new Scanner(is);
            scanner.nextLine();

            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String info = null;
//            while ((info = is.read()) != null){
//                bw.write(info);
//                bw.newLine();
//                bw.flush();
//            }
        } catch (Exception e) {

        }

    }

    @Test
    public void test7() throws IOException {
        PipedSend send = new PipedSend();
        PipedReader reader = new PipedReader();

        PipedOutputStream out = send.getOut();
        PipedInputStream in = reader.getIn();

        out.connect(in);

        reader.start();
        send.start();
    }

    @Test
    public void test8() throws IOException {
        String path = "C:/Users/80021687/Desktop/file.txt";
        FileOutputStream outputStream = new FileOutputStream(path);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.out.println("Hello World !!! ");
    }

    @Test
    public void test9() throws IOException {
        String path = "C:/Users/80021687/Desktop/JAVA并发编程实践JavaConcurrencyinPractice-中文-高清-带书签-完整版 Doug+Lea.pdf";
        String pathTmp1 = "C:/Users/80021687/Desktop/并发.pdf";
        String pathTmp2 = "C:/Users/80021687/Desktop/并发1.pdf";

        long time1 = System.currentTimeMillis();
        FileInputStream is = new FileInputStream(path);
        FileOutputStream os = new FileOutputStream(pathTmp1);

        byte[] f = new byte[1024];
        int fl = 0;
        while ((fl = is.read(f)) != -1) {
            os.write(f, 0, fl);
        }
        long time1end = System.currentTimeMillis();
        is.close();
        os.close();

        long time2 = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathTmp2));
        byte[] buffer = new byte[1024];
        int flag = 0;
        while ((flag = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, flag);
        }
        long time2end = System.currentTimeMillis();
        bis.close();
        bos.close();

        System.out.println("直接复制用时：" + (time1end - time1) + "毫秒");

        System.out.println("缓冲区复制用时：" + (time2end - time2) + "毫秒");

    }

    @Test
    public void test10() throws IOException {
        String path1 = "C:/Users/80021687/Desktop/file.txt";
        String path2 = "C:/Users/80021687/Desktop/新建文本文档.txt";
        String path3 = "C:/Users/80021687/Desktop/账号&地址.txt";

        String path4 = "C:/Users/80021687/Desktop/seq4.txt";

        FileInputStream fis1 = new FileInputStream(path1);
        FileInputStream fis2 = new FileInputStream(path2);
        FileInputStream fis3 = new FileInputStream(path3);

        List<FileInputStream> c = new ArrayList<>();
        c.add(fis1);
        c.add(fis2);
        c.add(fis3);
        Enumeration<FileInputStream> e = Collections.enumeration(c);

        SequenceInputStream sis = new SequenceInputStream(e);

//        FileOutputStream fos = new FileOutputStream(path4);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = sis.read(buf)) != -1) {
//            fos.write(buf, 0, len);
            System.out.println(new String(buf));
        }
//        fos.close();
        sis.close();
    }

    @Test
    public void test11() throws IOException {
        ByteBuffer buffer1 = ByteBuffer.allocate(48);
        ByteBuffer buffer2 = ByteBuffer.allocate(48);
        buffer1.put((byte) 1);
        buffer2.put((byte) 3);
        buffer2.put((byte) 2);
        System.out.println(buffer1.compareTo(buffer2));

    }

    @Test
    public void test12() throws IOException {
        String path1 = "C:/Users/80021687/Desktop/file.txt";
        String path2 = "C:/Users/80021687/Desktop/file1.txt";

        FileInputStream is = new FileInputStream(path1);
        FileOutputStream os = new FileOutputStream(path2);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        FileChannel fin = is.getChannel();
        FileChannel fo = os.getChannel();

        System.out.println("读取数据之前：" + buf);
        int read = fin.read(buf);
        System.out.println("读取数据之后：" + buf);
        buf.flip();

        System.out.println("写入数据之前：" + buf);
        int write = fo.write(buf);
        System.out.println("写入数据之后：" + buf);

    }

}
