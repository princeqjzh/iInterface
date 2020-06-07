package com.hogwarts.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.net.SocketTimeoutException;

/**
 * Created by jizhi.qian on 2019/3/13.
 */
public class HttpClient {
    private static Logger logger = Logger.getLogger(HttpClient.class);
    private static int waitTime = 2; //2秒超时

    public static void main(String[] args) {
        String reqURL = "http://www.weather.com.cn/data/cityinfo/101010100.html";
        String resp = HttpClient.sendGet(reqURL);
    }

    public static String sendGet(String url) {

        String charset = "utf-8";
        org.apache.http.client.HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;

        try {
            httpClient = new SSLClient();
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, waitTime * 1000);
            httpGet = new HttpGet(url);

            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (SocketTimeoutException ste) {

            logger.info("请求超时, URL: " + url);
            result = "超时啦!!! 服务响应时间大于" + waitTime + "秒, 稍后再试试喽!";

        } catch (ConnectTimeoutException cte) {

            logger.info("请求超时, URL: " + url);
            result = "超时啦!!! 服务响应时间大于" + waitTime + "秒, 稍后再试试喽!";

        } catch (Exception e) {
            e.printStackTrace();
            result = "内部错误, 稍后再试试喽!";
        }

        logger.info("response body:" + result);

        return result;
    }

}
