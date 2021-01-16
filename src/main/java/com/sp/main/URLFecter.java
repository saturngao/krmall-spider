package com.sp.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.sp.main.excel.HelpUtils;
import com.sp.main.model.SaveModel;
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

/**
 *
 * */
public class URLFecter {
    public static List<JdModel> URLParser (HttpClient client, String url) throws Exception {
        Map<String, String> header = new HashMap<>();
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        header.put(":authority", "www.coupang.com");
        header.put(":method", "GET");
        header.put(":scheme", "https");
        header.put("accept", "*/*");
        header.put("accept-encoding", "gzip, deflate, br");
        header.put("origin", "https://store.coupang.com");
        header.put("referer", "https://store.coupang.com");
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

    public static void URLParser (Map<String, String> header, String url, String productId) throws Exception {

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

                System.out.println("+++++++++++++++++++++++++++++++++++++++" + url);
//                System.out.println(html);
//                // 提取HTML得到商品信息结果
//                Document doc = Jsoup.parse(html);
//                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析,这都需要对html代码进行分析了

                // 提取HTML得到商品信息结果
                Document doc = Jsoup.parse(html);
                // 通过浏览器查看商品页面的源代码，找到信息所在的div标签，再对其进行一步一步地解析,这都需要对html代码进行分析了
//                Elements ulList = doc.select("#J_goodsList");
//                Elements liList = ulList.select(".gl-item");
//                System.out.println(doc.body().toString());

                Elements e = doc.getElementsByTag("script");
                JSONObject jsonObject = null;
                List<SaveModel> list = new ArrayList<>();
                SaveModel saveModel = null;
                StringBuffer colors = new StringBuffer();
                for (Element element : e) {
                    String jsons = element.data().toString();
                    if (jsons.contains("function")){
                        String sdpIssueTypes[] = jsons.split("exports.sdpIssueTypes");
                        for (String s1 : sdpIssueTypes){
                            if (s1.contains("function(exports)")) {
                                saveModel = new SaveModel();
                                jsonObject = JSONObject.parseObject(s1.split("exports.sdp")[1].replaceFirst("=", "").replaceFirst(";", ""));
                                saveModel.setName(jsonObject.getString("title"));
                                saveModel.setPrice(jsonObject.getString(""));
                                saveModel.setUrl(url);
                                saveModel.setId(productId);
                                JSONArray attributes = jsonObject.getJSONObject("options").getJSONArray("optionRows").getJSONObject(0).getJSONArray("attributes");
                                for (Object object : attributes) {
                                    colors.append(JSON.parseObject(object.toString()).getString("name")).append(",");
                                }
                                saveModel.setColor(colors.toString());
                                JSONObject priceJson = jsonObject.getJSONArray("quantityBase").getJSONObject(0).getJSONObject("price");
                                saveModel.setPrice(priceJson.getString("salePrice"));

                            }
                                System.out.println("******** " + jsonObject.toString());
                            }
                        }
                    }
//                    }
//                    /*取得JS变量数组*/
//                    String[] data = element.data().toString().split("exports.sdp");
//                    /*取得单个JS变量*/
//                    for(String variable : data){
//
//                        /*过滤variable为空的数据*/
//                        if(variable.contains("=")) {
//                            String[]  kvp = variable.split("=");
//                            System.out.println(kvp[1]);
//                        }
//
//                    }
//                }


//                System.out.println(doc.select("thumbnail"));
//                System.out.println(title);
//                System.out.println(value);
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

    public static List<String> urlParseImage(String html){
        Document doc = Jsoup.parse(html);
        Elements ulList = doc.getElementsByTag("img");
        List<String> images = new ArrayList<>();
        for (Element item : ulList) {
            images.add(item.attr("src"));
        }
        return images;
    }
}