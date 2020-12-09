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
        String input = "����";// ������Ϊ�������Ը����������һ���������������ܽ����û����������ȡ
        // ��Ҫ��ȡ��Ʒ��Ϣ����վ��ַ
        String url = "https://search.jd.com/Search?keyword=" + input + "&enc=utf-8";
        // ��̬ģ����������
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // ģ������������user-agent��ֵ����ͨ�������������鿴���������ͷ�ļ���ȡ��
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
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
                // ѭ��liList�����ݣ������ȡ������ֵ���ÿ�doc��ҳ��Դ��������ȡ���������б䶯��
                for (Element item : liList) {
                    // ��ƷID
                    String id = item.attr("data-sku");
                    System.out.println("��ƷID��" + id);
                    // ��Ʒ����
                    String name = item.select(".p-name").select("em").text();
                    System.out.println("��Ʒ���ƣ�" + name);
                    // ��Ʒ�۸�
                    String price = item.select(".p-price").select("i").text();
                    System.out.println("��Ʒ�۸�" + price);
                    // ��Ʒ��ַ
                    String goodsUrl = item.select(".p-name").select("a").attr("href");
                    System.out.println("��Ʒ��ַ��" + goodsUrl);
                    // ��ƷͼƬ��ַ
                    String imgUrl = item.select(".p-img").select("a").select("img").attr("src");
                    System.out.println("��ƷͼƬ��ַ��" + imgUrl);
                    // ��Ʒ����
                    String goodsShop = item.select(".p-shop").select("span").select("a").attr("title");
                    System.out.println("��Ʒ�������ƣ�" + goodsShop);
                    System.out.println("------------------------------------");
                }
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            } else {
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }
    }

    public void downTmallProduct() throws IOException {
        String input = "����";
        // ��Ҫ��ȡ��Ʒ��Ϣ����վ��ַ
        String url = "https://list.tmall.com/search_product.htm?q=" + input;
        // ��̬ģ����������
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // ģ������������user-agent��ֵ����ͨ�������������鿴���������ͷ�ļ���ȡ��
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        // ��ȡ��Ӧ״̬��
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
                    // ��ƷID
                    String id = item.select("div[class='product']").select("p[class='productStatus']").select("span[class='ww-light ww-small m_wangwang J_WangWang']").attr("data-item");
                    System.out.println("��ƷID��" + id);
                    // ��Ʒ����
                    String name = item.select("p[class='productTitle']").select("a").attr("title");
                    System.out.println("��Ʒ���ƣ�" + name);
                    // ��Ʒ�۸�
                    String price = item.select("p[class='productPrice']").select("em").attr("title");
                    System.out.println("��Ʒ�۸�" + price);
                    // ��Ʒ��ַ
                    String goodsUrl = item.select("p[class='productTitle']").select("a").attr("href");
                    System.out.println("��Ʒ��ַ��" + goodsUrl);
                    // ��ƷͼƬ��ַ
                    String imgUrl = item.select("div[class='productImg-wrap']").select("a").select("img").attr("data-ks-lazyload");
                    System.out.println("��ƷͼƬ��ַ��" + imgUrl);
                    System.out.println("------------------------------------");
                }
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            } else {
                // ���ĵ�ʵ��
                EntityUtils.consume(response.getEntity());
            }
        } finally {
            response.close();
        }
    }
}
