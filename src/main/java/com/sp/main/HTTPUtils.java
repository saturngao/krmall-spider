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
        //��ȡ��Ӧ�ļ�����html������get������ȡ��Ӧ����
        HttpGet getMethod = new HttpGet(personalUrl);
        setHeaders(getMethod, header);
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
                HttpStatus.SC_OK, "OK");
        try {
            //ִ��get����
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