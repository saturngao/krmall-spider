package com.sp.main;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class URLFecter {
    public static List<JdModel> URLParser (HttpClient client, String url) throws Exception {
        Map<String, String> header = new HashMap<>();
//        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        header.put("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
//        header.put("Accept-Encoding", "gzip, deflate");
//        header.put("Accept-Language", "zh-CN,zh;q=0.9");
//        header.put("Connection", "keep-alive");
        header.put("Cookie", "PCID=42435659656025452526795; ak_bmsc=A5CD95D1C75B46B1485570BF2E2A705276D6A7643225000021AAD05FC48BC077~plANIys5SqGqHxa7vh87f+KmDDSFfblWq6EyUg9kmNiwkY28r30htorHWtteKeOUcB2ozWBNdW0+AQCX9IdBn7gLsNvafCxsDebTQqrBfq8qsTi/SSyyldxxoKQ8IW+GS3iCxtlfkkegafcaNYiZyzF11FU7/bJ0TRKcfEjCWrmVbpVqVeKZUuFXUg8bJpb8xp9vJxt7COllI8yUq+avkY4rk3DEmj3dudwJFJ3rb8i44EGf+KX/9UhkqLmBYEqvaa; _fbp=fb.1.1607510563367.1166911022; baby-isWide=wide; sid=800504d2b6b64afab26d9ce4a41913e02c25582a; overrideAbTestGroup=%5B%5D; bm_sv=3FE9C9AC2C44149A50312B934A194D16~dXxBTzjfKxzEu+6jbWqD2v4OMGxD70I+uhsYTIyJUuCnFONC7QhE5NQH5fYj2G2QNEzpuzXZoqOmKzvbMYhq/rVCZ4PUN07M90X/hcZZRDJy9FxRl8S1PIFcQaKsxDmeCN0kdsjFa4QQcXu2lA+hVJyPipHH/X5m0Urid02pr+E=");
//        header.put("Host", "search.jd.com");
//        header.put("Cache-Control", "max-age=0");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        header.put(":authority", "www.coupang.com");
        header.put(":method", "GET");
//        header.put(":path", "/np/client-information");
        header.put(":scheme", "https");
        header.put("accept", "*/*");
        header.put("accept-encoding", "gzip, deflate, br");
        header.put("origin", "https://store.coupang.com");
        header.put("referer", "https://store.coupang.com");
//        header.put("sec-fetch-dest", "empty");
//        header.put("sec-fetch-mode", "cors");
//        header.put("sec-fetch-site", "same-site");
//        header.put("Upgrade-Insecure-Requests", "1");
        //用来接收解析的数据
        List<JdModel> JingdongData = new ArrayList<JdModel>();
        //获取网站响应的html，这里调用了HTTPUtils类
        HttpResponse response = HTTPUtils.getRawHtml(client, url, header);
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("entity ------- " + entity);
            JingdongData = JdParse.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return JingdongData;
    }

    /**
     * get接口
     * @param url
     * @param header
     * @return
     * @throws Exception
     */
    public static JSONObject getMethod(String url, Map<String, String> header) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
        JSONObject object = new JSONObject();
        //获取响应状态码
        int StatusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("entity ------- " + entity);
            object = JSONObject.parseObject(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        return object;
    }
}