package com.gbl.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guobaolin on 2018/11/24.
 */
public class DistributeClient {

    private static final String connectString = "119.29.1.159:2181";
//    private static final String connectString = "200.200.200.55:2181";
    private static final int sessionTimeout = 30000;
    private ZooKeeper zk = null;
    private static final String parentNode = "/cmbus/server";

    private volatile List<String> serverList;

    public void getConnect() throws Exception{
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getServerList() throws Exception{
        List<String> children = zk.getChildren(parentNode, true);
        List<String> servers = new ArrayList<>();
        for (String child : children){
            byte[] data = zk.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
        }
        serverList = servers;

        System.out.println(serverList.toString());
    }

    public void handleBusiness() throws Exception{
        System.out.println("client start working...");
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws Exception {
        //获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnect();

        //获取servers的子节点信息（并监听），从中获取服务器列表
        client.getServerList();

        //业务功能启动
        client.handleBusiness();
    }

}
