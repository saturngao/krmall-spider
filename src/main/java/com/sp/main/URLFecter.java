package com.sp.main;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    public static void URLParser (Map<String, String> header, String url) throws Exception {

//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
//        JSONObject object = new JSONObject();
//        //获取响应状态码
//        int StatusCode = response.getStatusLine().getStatusCode();
//        //如果状态响应码为200，则获取html实体内容或者json文件
//        if(StatusCode == 200){
//            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
//            System.out.println("entity ------- " + entity);
//            object = JSONObject.parseObject(entity);
//            EntityUtils.consume(response.getEntity());
//        }else {
//            //否则，消耗掉实体
//            EntityUtils.consume(response.getEntity());
//        }


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
        // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
//        CloseableHttpResponse response = httpclient.execute(httpGet);
        // 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        try {
            HttpEntity entity = response.getEntity();
            // 如果状态响应码为200，则获取html实体内容或者json文件
            if (statusCode == 200) {
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                // 提取HTML得到商品信息结果
                Document doc = Jsoup.parse(html);
                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析,这都需要对html代码进行分析了
                Elements ulList = doc.select("#J_goodsList");
                Elements liList = ulList.select(".gl-item");
                System.out.println(doc.body().toString());
                // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
//                for (Element item : liList) {
//                    // 商品ID
//                    String id = item.attr("data-sku");
//                    System.out.println("商品ID：" + id);
//                    // 商品名称
//                    String name = item.select(".p-name").select("em").text();
//                    System.out.println("商品名称：" + name);
//                    // 商品价格
//                    String price = item.select(".p-price").select("i").text();
//                    System.out.println("商品价格：" + price);
//                    // 商品网址
//                    String goodsUrl = item.select(".p-name").select("a").attr("href");
//                    System.out.println("商品网址：" + goodsUrl);
//                    // 商品图片网址
//                    String imgUrl = item.select(".p-img").select("a").select("img").attr("src");
//                    System.out.println("商品图片网址：" + imgUrl);
//                    // 商品店铺
//                    String goodsShop = item.select(".p-shop").select("span").select("a").attr("title");
//                    System.out.println("商品店铺名称：" + goodsShop);
//                    System.out.println("------------------------------------");
//                }
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            } else {
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            }
        } finally {
//            response.close();
        }
    }
}