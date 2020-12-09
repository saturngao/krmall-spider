package com.sp.main.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
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

public class Test {

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.downJDProduct();
    }

    public void downJDProduct() throws IOException {
        String input = "辣条";// 以辣条为例，可以给这个方法加一个参数，这样就能接收用户输入进行爬取
        // 需要爬取商品信息的网站地址
        String url = "https://search.jd.com/Search?keyword=" + input + "&enc=utf-8";
        // 动态模拟请求数据
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
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
                // 循环liList的数据（具体获取的数据值还得看doc的页面源代码来获取，可能稍有变动）
                for (Element item : liList) {
                    // 商品ID
                    String id = item.attr("data-sku");
                    System.out.println("商品ID：" + id);
                    // 商品名称
                    String name = item.select(".p-name").select("em").text();
                    System.out.println("商品名称：" + name);
                    // 商品价格
                    String price = item.select(".p-price").select("i").text();
                    System.out.println("商品价格：" + price);
                    // 商品网址
                    String goodsUrl = item.select(".p-name").select("a").attr("href");
                    System.out.println("商品网址：" + goodsUrl);
                    // 商品图片网址
                    String imgUrl = item.select(".p-img").select("a").select("img").attr("src");
                    System.out.println("商品图片网址：" + imgUrl);
                    // 商品店铺
                    String goodsShop = item.select(".p-shop").select("span").select("a").attr("title");
                    System.out.println("商品店铺名称：" + goodsShop);
                    System.out.println("------------------------------------");
                }
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            } else {
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }
    }

    public void downTmallProduct() throws IOException {
        String input = "辣条";
        // 需要爬取商品信息的网站地址
        String url = "https://list.tmall.com/search_product.htm?q=" + input;
        // 动态模拟请求数据
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 模拟浏览器浏览（user-agent的值可以通过浏览器浏览，查看发出请求的头文件获取）
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        // 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        try {
            HttpEntity entity = response.getEntity();
            if (statusCode == 200) {
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                Document doc = null;
                doc = Jsoup.parse(html);
                Elements ulList = doc.select("div[class='view grid-nosku']");
                Elements liList = ulList.select("div[class='product']");
                for (Element item : liList) {
                    // 商品ID
                    String id = item.select("div[class='product']").select("p[class='productStatus']").select("span[class='ww-light ww-small m_wangwang J_WangWang']").attr("data-item");
                    System.out.println("商品ID：" + id);
                    // 商品名称
                    String name = item.select("p[class='productTitle']").select("a").attr("title");
                    System.out.println("商品名称：" + name);
                    // 商品价格
                    String price = item.select("p[class='productPrice']").select("em").attr("title");
                    System.out.println("商品价格：" + price);
                    // 商品网址
                    String goodsUrl = item.select("p[class='productTitle']").select("a").attr("href");
                    System.out.println("商品网址：" + goodsUrl);
                    // 商品图片网址
                    String imgUrl = item.select("div[class='productImg-wrap']").select("a").select("img").attr("data-ks-lazyload");
                    System.out.println("商品图片网址：" + imgUrl);
                    System.out.println("------------------------------------");
                }
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            } else {
                // 消耗掉实体
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }
    }
}
