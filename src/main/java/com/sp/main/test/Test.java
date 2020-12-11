package com.sp.main.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
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

public class Test {

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.downJDProduct();
    }

    public void downJDProduct() throws IOException {
        String input = "����";// ������Ϊ�������Ը����������һ���������������ܽ����û����������ȡ
        // ��Ҫ��ȡ��Ʒ��Ϣ����վ��ַ
        String url = "https://www.coupang.com/vp/products/337282208?itemId=1075764179&vendorItemId=72052663264";
//        String url = "https://search.jd.com/Search?keyword=" + input + "&enc=utf-8";

        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();


        // ��̬ģ����������
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultConfig);
        // ģ������������user-agent��ֵ����ͨ�������������鿴���������ͷ�ļ���ȡ��
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");

        httpGet.setHeader("Cookie", "PCID=42435659656025452526795; _fbp=fb.1.1607510563367.1166911022; sid=800504d2b6b64afab26d9ce4a41913e02c25582a; gd1=Y; ak_bmsc=E725C6B2C7B55F9B19B6FA1776E4209876D6A7659E4E0000A234D35FF13C3C0D~plaec5QnZlX0tphtdNGxuP3UZZr+LNA7A79GDIVwg4q31shT9v6DghHU4SKkd0cOs+eTOaTTCqkHisWZqLR5yhh/PgKBNWtYlijhofizdGc4rJXTyi/AfneZVmUWT6KgfH47XnYfRCqO+S88r5XXEQML2hOLiuQX/xsOb1aEx37K+KsYO1KRKm7CsfmMEUZKSZ6+YUO7j4Z9S+yzhbzUIQ84IlNGfzHEvsJIxemSkaoPCPeJ25yWsBbwwgevq5Yeqc; bm_mi=F993D54ECA3CEF429C76944993B3F342~AyNirbSC4N2CTZh0hgJwp5wR5+Q4APj07lby2477swsNLCSwEuayD8A5N3j9jJ2eWcqgtQiMsNmwmgrk/pF515mGEMWTDMsBguQhYK/OhwF/TOfKdKW1Y/JZT4AxlfdQezAVIfIHfa+IOwBZrRO5iafgtzY5F5Xvh8Z2E+TW5BCW1Hu2d2UNaW4Xpe8GfZajuMoqFN+DZHF1luKlwrZogbywDwA9OfrF8Mjo+f988bTBAaoboDWGN2jnzZeKlTEKAj1DJpafZfECt0O25AXzt7J28K6ylnpWuh4VM0GJzn4=; overrideAbTestGroup=%5B%5D; rememberme=true; ILOGIN=Y; CSID=JmF2HBn6rdIGjAw4Ju0YNyNxtGpCaj8by6qzeX17qwTj_FVud_DdmMBgUkYhdFoiW_JbggrApLi2xinFsycHwtEPBrimSHX5keDs7Pj9P-v6; CUPT=tjzHkQ5AZAO18wpItmEK_N8juQ7nlxrqBhAr0obcVJKXEG1mrVIXuryZl0xOyGPSDj9XD2wLFwg2JwmCRNWUmKnZvADaEdy5OfUzisv3FzMF4uUJGGQXntpEuYGovO8cJLiq_LTKTTCxUz6drfEUiMLsoQzNN6fh33mSZjC9OPIxR0-ITXIDJlcbJj9HqFX-ksj7WqakZ-mwxG_20sAmSDyEayFxdKTjdHdzV3-gwmJxeM300N3A9KA; member_srl=128318748; baby-isWide=wide; CPUSR_RL=aS%2FzeQJzVHL%2BOeRAabxrG8AZlN1mUfLYIZ64Cb2NUtZDql7wTr1gSMAcph1JyhzDYUt%2FaDBkASKQj4xln4Uc8pIprD4J9wx1r%2Flkszept8kfOtR5ma0mhw6rn28wwwMR64VKt1wHeqP7GoZXs8uJFeCEiQ0pADfhN%2F%2BNB6Dv3rYoL2gaTcFad%2FxfEZ%2FGDJFWqdrlK7nLKl1h0xD0EpPZKQg%3D; bm_sv=EA91E52185E887CE2DE232AFBEAC4AF5~q3c+aLFdQCU9dNup6vOAnT8CemS9Z5SadtrpTKRecAlQk6LUUx3QOOoYVoek1PkhRM0Q5MeI/Njw/ClqEtErhMuhB2eEpZavHQmN4Omv2uo2tx1owEx4JjXwFfEj8VbtuN7bAfzKRprr74T+HEub7UVUsQH0BssG7+PlIPcLJ30=");
        httpGet.setHeader(":authority", "www.coupang.com");
        httpGet.setHeader(":method", "GET");
        httpGet.setHeader(":scheme", "https");
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("accept-encoding", "gzip, deflate, br");
        httpGet.setHeader("origin", "https://store.coupang.com");

        System.out.printf("=============================================");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        // ��ȡ��Ӧ״̬��
        int statusCode = response.getStatusLine().getStatusCode();
        try {
            HttpEntity entity = response.getEntity();
            // ���״̬��Ӧ��Ϊ200�����ȡhtmlʵ�����ݻ���json�ļ�
            if (statusCode == 200) {
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                System.out.println(html);
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
