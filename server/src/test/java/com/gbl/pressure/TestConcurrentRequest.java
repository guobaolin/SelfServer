package com.gbl.pressure;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by guobaolin on 2018/11/26.
 */
public class TestConcurrentRequest {
    // 总的请求个数(并发数)
    public static final int requestTotal = 1050;

    public static void main(String[] args) throws InterruptedException {
        String url = "http://test.cms.clife.cn/v1/device/data/file/upload";
        Map<String, String> param = new HashMap<>();
        param.put("mac", "ACCF2356BDB4");
        param.put("deviceCode", "0000C3A1000B0301");
        param.put("data", "C:/Users/80021687/Desktop/Cvtlp1XJJaaASJ9nAAAI9DdPoek2345128");
        concurrentRequest(url, param);
    }

    public static void concurrentRequest(String url, Map<String, String> param) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        CountDownLatch mainLatch = new CountDownLatch(1);
        for (int i = 0; i < requestTotal; i++) {
            executorService.execute(() -> {
                try {
                    mainLatch.await();
                    String result = doPost(url, param);
                    System.err.println(Thread.currentThread().getName() + " result: " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        mainLatch.countDown();
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("请求完成");
    }

    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null) {
                MultipartEntity entity = new MultipartEntity();
                FileBody bin = new FileBody(new File(param.get("data")));

                entity.addPart("mac", new StringBody(param.get("mac")));
                entity.addPart("deviceCode", new StringBody(param.get("deviceCode")));
                entity.addPart("data", bin);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
