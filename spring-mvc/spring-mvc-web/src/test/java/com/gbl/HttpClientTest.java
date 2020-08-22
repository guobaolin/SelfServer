package com.gbl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

    public static void main(String[] args) {


        String url = "https://200.200.200.50/v1/web/open/server/test";
//        String url = "https://www.baidu.com";

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;

        try{
            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
            httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity,"UTF-8");

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testString() {

        System.out.println("aa");
    }

}
