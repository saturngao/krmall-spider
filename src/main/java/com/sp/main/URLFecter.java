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
 *  �Ϸʹ�ҵ��ѧ ����ѧԺ qianyang 1563178220@qq.com
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
        //�������ս���������
        List<JdModel> JingdongData = new ArrayList<JdModel>();
        //��ȡ��վ��Ӧ��html�����������HTTPUtils��
        HttpResponse response = HTTPUtils.getRawHtml(client, url, header);
        //��ȡ��Ӧ״̬��
        int StatusCode = response.getStatusLine().getStatusCode();
        //���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("entity ------- " + entity);
            JingdongData = JdParse.getData(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //�������ĵ�ʵ��
            EntityUtils.consume(response.getEntity());
        }
        return JingdongData;
    }

    /**
     * get�ӿ�
     * @param url
     * @param header
     * @return
     * @throws Exception
     */
    public static JSONObject getMethod(String url, Map<String, String> header) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
        JSONObject object = new JSONObject();
        //��ȡ��Ӧ״̬��
        int StatusCode = response.getStatusLine().getStatusCode();
        //���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
        if(StatusCode == 200){
            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("entity ------- " + entity);
            object = JSONObject.parseObject(entity);
            EntityUtils.consume(response.getEntity());
        }else {
            //�������ĵ�ʵ��
            EntityUtils.consume(response.getEntity());
        }
        return object;
    }

    public static void URLParser (Map<String, String> header, String url) throws Exception {

//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
//        JSONObject object = new JSONObject();
//        //��ȡ��Ӧ״̬��
//        int StatusCode = response.getStatusLine().getStatusCode();
//        //���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
//        if(StatusCode == 200){
//            String entity = EntityUtils.toString (response.getEntity(),"utf-8");
//            System.out.println("entity ------- " + entity);
//            object = JSONObject.parseObject(entity);
//            EntityUtils.consume(response.getEntity());
//        }else {
//            //�������ĵ�ʵ��
//            EntityUtils.consume(response.getEntity());
//        }


        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpGet.setConfig(defaultConfig);
        // ģ������������user-agent��ֵ����ͨ�������������鿴���������ͷ�ļ���ȡ��
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        HttpResponse response = HTTPUtils.getRawHtml(httpclient, url, header);
//        CloseableHttpResponse response = httpclient.execute(httpGet);
        // ��ȡ��Ӧ״̬��
        int statusCode = response.getStatusLine().getStatusCode();
        try {
            HttpEntity entity = response.getEntity();
            // ���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
            if (statusCode == 200) {
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                // ��ȡHTML�õ���Ʒ��Ϣ���
                Document doc = Jsoup.parse(html);
                // ͨ��������鿴��Ʒҳ���Դ���룬�ҵ���Ϣ���ڵ�div��ǩ���ٶ������һ��һ���ؽ���,�ⶼ��Ҫ��html������з�����
                Elements ulList = doc.select("#J_goodsList");
                Elements liList = ulList.select(".gl-item");
                System.out.println(doc.body().toString());
                // ѭ��liList�����ݣ������ȡ������ֵ���ÿ�doc��ҳ��Դ��������ȡ���������б䶯��
//                for (Element item : liList) {
//                    // ��ƷID
//                    String id = item.attr("data-sku");
//                    System.out.println("��ƷID��" + id);
//                    // ��Ʒ����
//                    String name = item.select(".p-name").select("em").text();
//                    System.out.println("��Ʒ���ƣ�" + name);
//                    // ��Ʒ�۸�
//                    String price = item.select(".p-price").select("i").text();
//                    System.out.println("��Ʒ�۸�" + price);
//                    // ��Ʒ��ַ
//                    String goodsUrl = item.select(".p-name").select("a").attr("href");
//                    System.out.println("��Ʒ��ַ��" + goodsUrl);
//                    // ��ƷͼƬ��ַ
//                    String imgUrl = item.select(".p-img").select("a").select("img").attr("src");
//                    System.out.println("��ƷͼƬ��ַ��" + imgUrl);
//                    // ��Ʒ����
//                    String goodsShop = item.select(".p-shop").select("span").select("a").attr("title");
//                    System.out.println("��Ʒ�������ƣ�" + goodsShop);
//                    System.out.println("------------------------------------");
//                }
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            } else {
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            }
        } finally {
//            response.close();
        }
    }
}