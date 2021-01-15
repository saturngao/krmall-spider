package com.sp.main;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHttpResponse;
/**
 *
 */
public abstract class HTTPUtils {
    public static HttpResponse getRawHtml(HttpClient client, String personalUrl, Map<String, String> header) {
        //获取响应文件，即html，采用get方法获取响应数据
        HttpGet getMethod = new HttpGet(personalUrl);
        setHeaders(getMethod, header);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            //执行get方法
            response = client.execute(getMethod);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // getMethod.abort();
        }
        return response;
    }


    private static void setHeaders(HttpRequestBase method, Map<String, String> header){
        for (Map.Entry<String, String > entry : header.entrySet()){
            method.setHeader(entry.getKey(), entry.getValue());
        }
    }
}