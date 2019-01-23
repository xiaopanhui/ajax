package com.example.ajax;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class HttpClin {
    protected static org.slf4j. Logger log = LoggerFactory.getLogger(HttpClin.class);
    private  final static int SOCKET_TIME_OUT=6000;
    private final static int CONNECT_TIME_OUT=6000;
    private List<NameValuePair>  addParam(Map<String,Object> param){
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        for (String k:param.keySet()){
            NameValuePair nameValuePair=new BasicNameValuePair(k,param.get(k).toString());
            nameValuePairs.add(nameValuePair);

        }

        return  nameValuePairs;
    }

        public static String postForAPP(String url, JSONObject pram,Map<String ,Object> headers) throws URISyntaxException, IOException {
            CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
            CloseableHttpResponse closeableHttpResponse=null;
            URIBuilder uriBuilder = new URIBuilder(url);
            URI uri=    uriBuilder.build();
            HttpEntity httpEntity=null;

            HttpPost httpPost = new HttpPost(uri);
            try {
            if(pram!=null){

                httpPost.setEntity(new StringEntity(pram.toString(),"utf-8"));
            }
              Set<String> keys=  headers.keySet();
            for (String key : keys) {
                httpPost.setHeader(key,headers.get(key).toString());

            }
            RequestConfig reqConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT).setConnectTimeout(CONNECT_TIME_OUT).build();
            httpPost.setConfig(reqConfig);

                closeableHttpResponse = closeableHttpClient.execute(httpPost);
                httpEntity=closeableHttpResponse.getEntity();
               String reslut=EntityUtils.toString( httpEntity,"utf-8");
                StatusLine a=   closeableHttpResponse.getStatusLine();
                System.out.println(a.toString());

               if(a.getStatusCode()==200){
                   System.out.println("a*************************"+reslut);
                   return reslut;
               }else {

               }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                EntityUtils.consume(httpEntity);
            }

            return null;
        }

    public static void main(String[] args) throws Exception {
    }


}
