package com.example.ajax.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Service
public class HttpRe {
    protected static Logger logger = LoggerFactory.getLogger(HttpRe.class);
    //请求超时时间,这个时间定义了socket读数据的超时时间，
    // 也就是连接到服务器之后到从服务器获取响应数据需要等待的时间,发生超时，会抛出SocketTimeoutException异常。
    private static final int SOCKET_TIME_OUT = 60000;
    //连接超时时间,这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间。发生超时，会抛出ConnectionTimeoutException异常
    private static final int CONNECT_TIME_OUT = 60000;

    private static List<NameValuePair> createParam(Map<String, Object> param) {
        //建立一个NameValuePair数组，用于存储欲传送的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (param != null) {
            for (String k : param.keySet()) {
                NameValuePair nameValuePair = new BasicNameValuePair(k, param.get(k).toString());
                nvps.add(nameValuePair);
            }
        }
        return nvps;
    }

    /**
     * 发送  post 请求
     *
     * @return
     * @throws Exception
     */
    public static String postForAPP(String url, JSONObject param, Map<String, Object> headers) throws Exception {
        //目前HttpClient最新版的实现类为CloseableHttpClient
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {

            //建立Request的对象，一般用目标url来构造，Request一般配置addHeader、setEntity、setConfig
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
            System.out.println(uri);
            HttpPost req = new HttpPost(uri);
            if (param != null) {
                //设置请求参数,中文 设置UTF-8
                req.setEntity(new StringEntity(param.toString(), "UTF-8"));
            }
            //setHeader,添加头文件
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                req.setHeader(key, headers.get(key).toString());
                System.out.println("key"+key);
                System.out.println("headers.get(key).toString()"+headers.get(key).toString());
            }
            //setConfig,添加配置,如设置请求超时时间,连接超时时间
            RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
                    .setConnectTimeout(CONNECT_TIME_OUT).build();
            req.setConfig(reqConfig);
            //执行Request请求,CloseableHttpClient的execute方法返回的response都是CloseableHttpResponse类型
            //其常用方法有getFirstHeader(String)、getLastHeader(String)、headerIterator（String）
            // 取得某个Header name对应的迭代器、getAllHeaders()、getEntity、getStatus等
            response = client.execute(req);
            entity = response.getEntity();
            //用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,防止服务器返回的数据带有中文,
            // 所以在转换的时候将字符集指定成utf-8就可以了
            String result = EntityUtils.toString(entity, "UTF-8");
            //JSON字符串 转obj
            JSONObject res = JSONObject.parseObject(result, JSONObject.class);
            logger.info(result);
            System.out.println("(result=============="+result);
            System.out.println("data======================"+res.get("data"));
            if (response.getStatusLine().getStatusCode() == 200) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception();
        } finally {
            //一定要记得把entity fully consume掉，否则连接池中的connection就会一直处于占用状态
            EntityUtils.consume(entity);
            System.out.println("---------------------------------------------------");
        }
    }

        //get請求
    public static void doGet() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //http://www.thepaper.cn"
        HttpGet get = new HttpGet("https://mail.qq.com/cgi-bin/frame_html?sid=zsX_c_yunv_2Qncr&r=b508027a2a5a35b1dd3efcf6d42d3131");
        try {
            // 很奇怪，使用CloseableHttpClient来请求澎湃新闻的首页，GTE请求也必须加上下面这个Header，但是使用HTTPClient则不需要
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
            HttpResponse response = client.execute(get);
            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取图片byte数组
     *
     * @param url
     * @return
     */
    public static byte[] getImage(String url) {

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        HttpEntity entity = null;
        byte[] data = null;
        try {

            //建立Request的对象，一般用目标url来构造，Request一般配置addHeader、setEntity、setConfig
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri = uriBuilder.build();
            HttpGet req = new HttpGet(uri);

            //setConfig,添加配置,如设置请求超时时间,连接超时时间
            RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
            req.setConfig(reqConfig);
            //执行Request请求,CloseableHttpClient的execute方法返回的response都是CloseableHttpResponse类型
            //其常用方法有getFirstHeader(String)、getLastHeader(String)、headerIterator（String）取得某个Header name对应的迭代器、getAllHeaders()、getEntity、getStatus等
            response = client.execute(req);
            if (response.getStatusLine().getStatusCode() == 200) {
                //得到实体
                entity = response.getEntity();
                data = EntityUtils.toByteArray(entity);
                System.out.println("图片下载成功!!!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要记得把entity fully consume掉，否则连接池中的connection就会一直处于占用状态
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------------------------------");
        }

        return data;

    }


    public static void main(String[] args) throws Exception {
        JSONObject param = new JSONObject();
        param.put("userName", "admin");
        param.put("password","147258");
        Map<String, Object> headers=new HashMap<String, Object>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Accept","application/json;charset=UTF-8");
//        "http://localhost:5005/login"
        postForAPP("http://localhost:5005/login", param, headers);

//        doGet();
    }


}
