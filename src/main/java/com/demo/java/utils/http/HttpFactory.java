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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.demo.java.commons.Config.CHARSET;

/**
 * HTTP请求工具类
 *
 * @author zhanghanlin
 */
public class HttpFactory {

    private final static Logger logger = LoggerFactory.getLogger(HttpFactory.class);

    private final static int SOCKET_TIMEOUT = 5000;
    private final static int REQUEST_TIMEOUT = 5000;
    private final static int TIMEOUT = 5000;

    /**
     * 构造公共请求配置
     *
     * @param socketTimeout  连接超时时间
     * @param timeout        超时时间
     * @param requestTimeout 请求超时时间
     * @return RequestConfig
     */
    private static RequestConfig requestConfig(int socketTimeout, int timeout, int requestTimeout) {
        return RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(requestTimeout).build();
    }

    /**
     * 构造公共请求Header
     *
     * @param http   HttpRequestBase
     * @param header List<NameValuePair>
     * @return HttpRequestBase
     */
    private static void setHeader(HttpRequestBase http, List<NameValuePair> header) {
        if (header != null && !header.isEmpty()) {
            for (NameValuePair n : header) {
                http.setHeader(n.getName(), n.getValue());
            }
        }
    }

    /**
     * execute
     *
     * @param url      url
     * @param mapParam param
     * @param header   header
     * @param method   method
     * @return result
     * @throws IOException IOException
     */
    public static String execute(String url, Map<String, String> mapParam, List<NameValuePair> header, HttpMethod method) throws IOException {
        List<NameValuePair> param = new ArrayList<>();
        if (mapParam != null) {
            for (Map.Entry<String, String> entry : mapParam.entrySet()) {
                param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return execute(url, param, header, method, SOCKET_TIMEOUT, TIMEOUT, REQUEST_TIMEOUT);
    }

    /**
     * execute
     *
     * @param url      url
     * @param mapParam param
     * @param method   method
     * @return result
     * @throws IOException IOException
     */
    public static String execute(String url, Map<String, String> mapParam, HttpMethod method) throws IOException {
        List<NameValuePair> param = new ArrayList<>();
        if (mapParam != null) {
            for (Map.Entry<String, String> entry : mapParam.entrySet()) {
                param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return execute(url, param, null, method, SOCKET_TIMEOUT, TIMEOUT, REQUEST_TIMEOUT);
    }


    /**
     * execute
     *
     * @param url    url
     * @param param  param
     * @param method method
     * @return result
     * @throws IOException IOException
     */
    public static String execute(String url, List<NameValuePair> param, List<NameValuePair> header, HttpMethod method,
                                 int socketTimeout, int timeout, int requestTimeout) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpRequestBase requestBase = null;
        if (method == HttpMethod.POST) {
            HttpPost httpPost = new HttpPost(url);
            if (param != null && !param.isEmpty()) {
                httpPost.setEntity(new UrlEncodedFormEntity(param, CHARSET));
            }
            requestBase = httpPost;
        } else if (method == HttpMethod.GET) {
            if (param != null && !param.isEmpty()) {
                url += url.indexOf("?") > 0 ? "&" : "?" + EntityUtils.toString(new UrlEncodedFormEntity(param, CHARSET));
            }
            requestBase = new HttpGet(url);
        }
        if (requestBase == null) {
            return null;
        }
        setHeader(requestBase, header);
        requestBase.setConfig(requestConfig(socketTimeout, timeout, requestTimeout));
        CloseableHttpResponse httpResponse = httpClient.execute(requestBase);
        try {
            int code = httpResponse.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
            } else {
                logger.error("HttpFactory.execute statusCode:{}", code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpResponse.close();
            httpClient.close();
        }
        return result;
    }

    /**
     * HTTP Method Enum
     * POST,GET
     */
    public enum HttpMethod {
        /**
         * POST请求
         */
        POST,
        /**
         * GET请求
         */
        GET
    }
}