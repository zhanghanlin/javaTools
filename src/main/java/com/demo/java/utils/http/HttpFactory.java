package com.demo.java.utils.http;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.demo.java.commons.Config.CHARSET;

/**
 * HTTP请求工具类
 */
public class HttpFactory {

    static int socketTimeout = 2000;
    static int requestTimeout = 2000;
    static int timeout = 2000;

    static RequestConfig requestConfig(int socketTimeout, int timeout, int requestTimeout) {
        return RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(requestTimeout).build();
    }

    static HttpRequestBase setHeader(HttpRequestBase http, List<NameValuePair> header) {
        if (header != null && !header.isEmpty()) {
            for (NameValuePair n : header)
                http.setHeader(n.getName(), n.getValue());
        }
        return http;
    }

    public static String doPost(String url, List<NameValuePair> param) throws IOException {
        return doPost(url, param, null, socketTimeout, timeout, requestTimeout);
    }

    public static String doPost(String url, List<NameValuePair> param, List<NameValuePair> header,
                                int socketTimeout, int timeout, int requestTimeout) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost = (HttpPost) setHeader(httpPost, header);
        if (param != null && !param.isEmpty()) {
            httpPost.setEntity(new UrlEncodedFormEntity(param, CHARSET));
        }
        httpPost.setConfig(requestConfig(socketTimeout, timeout, requestTimeout));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            int code = httpResponse.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpResponse.close();
            httpClient.close();
        }
        return result;
    }

    public static String doGet(String url, List<NameValuePair> param, List<NameValuePair> header,
                               int socketTimeout, int timeout, int requestTimeout) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (param != null && !param.isEmpty()) {
            url += url.indexOf("?") > 0 ? "&" : "?"
                    + EntityUtils.toString(new UrlEncodedFormEntity(param, CHARSET));
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet = (HttpGet) setHeader(httpGet, header);
        httpGet.setConfig(requestConfig(socketTimeout, timeout, requestTimeout));
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        try {
            int code = httpResponse.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpResponse.close();
            httpClient.close();
        }
        return result;
    }

    public static String doGet(String url, Map<String, String> param) {
        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            return doGet(url, list, null, socketTimeout, timeout, requestTimeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}