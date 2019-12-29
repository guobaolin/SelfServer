package com.gbl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by guobaolin on 2019/8/28.
 */
public class HDFSClientTest {

    private FileSystem fs;

    @Before
    public void init() throws Exception {
        Configuration conf = new Configuration();
        // 指定使用的 hdfs文件系统
        conf.set("fs.defaultFS", "hdfs://192.168.120.139:9000");
        // 通过这种方式设置java客户端身份
        System.setProperty("HADOOP_USER_NAME", "root");
        fs = FileSystem.get(conf);
    }

    @After
    public void shutdown() throws IOException {
        if (fs != null) {
            fs.close();
        }
    }

    @Test
    public void testCopyToLocal() throws IOException {
        fs.copyToLocalFile(new Path("/books/nohup.out"), new Path("D:/test/1.txt"));
    }

    @Test
    public void testMkdir() throws IOException {
        fs.mkdirs(new Path("/books"));
    }

    @Test
    public void testDelete() throws IOException {
        fs.delete(new Path("/books"), true);
    }

    @Test
    public void testListFiles() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                System.out.println("block-length:" + blockLocation.getLength() + "-- block-offset:" + blockLocation.getOffset());
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
            System.out.println("---------------打印的分隔线---------------------");
        }
    }

    @Test
    public void testListFiles2() throws IOException {
        FileStatus[] fileStatuses = fs.globStatus(new Path("/books/*"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath());
            System.out.println(fileStatus.getPermission());
            System.out.println("============================");
        }
    }

    @Test
    public void testAddFileToHdfs() throws IOException {
        // 本地文件路径
        Path src = new Path("C:\\Users\\80021687\\Desktop\\设备数据推送设计-黎效强.doc");
        // hdfs目标路径
        Path dst = new Path("/books");
        fs.copyFromLocalFile(src, dst);
    }

    @Test
    public void testAddFileToHdfs2() throws IOException {
        // 本地文件路径
        String src = "C:\\Users\\80021687\\Desktop\\rocketmq-console-ng-1.0.1.jar";
        // hdfs目标路径
        Path dst = new Path("/books/rocketmq-console-ng-1.0.1.jar");

        InputStream in = new BufferedInputStream(new FileInputStream(src));

        OutputStream out = fs.create(dst, () ->
                System.out.print(".")
        );
        IOUtils.copyBytes(in, out, 4096, true);
    }


}
