package com.sp.main;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class JdongMain {
    // log4j的是使用，不会的请看之前写的文章
    static final Log logger = LogFactory.getLog(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //初始化一个httpclient
//        HttpClient client = new DefaultHttpClient();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="https://store.coupang.com/vp/vendors/C00257985/product/lists?outboundShippingPlaceId=&attributeFilters=&brand=&componentId=&keyword=&maxPrice=&minPrice=&pageNum=1&rating=0&sortTypeValue=&scpLanding=true";
//        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //抓取的数据
//        List<JdModel> bookdatas=URLFecter.URLParser(httpclient, url);
//        //循环输出抓取的数据
//        for (JdModel jd:bookdatas) {
//            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
//        }
        //将抓取的数据插入数据库
//        MYSQLControl.executeInsert(bookdatas);
        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "PCID=42435659656025452526795; ak_bmsc=A5CD95D1C75B46B1485570BF2E2A705276D6A7643225000021AAD05FC48BC077~plANIys5SqGqHxa7vh87f+KmDDSFfblWq6EyUg9kmNiwkY28r30htorHWtteKeOUcB2ozWBNdW0+AQCX9IdBn7gLsNvafCxsDebTQqrBfq8qsTi/SSyyldxxoKQ8IW+GS3iCxtlfkkegafcaNYiZyzF11FU7/bJ0TRKcfEjCWrmVbpVqVeKZUuFXUg8bJpb8xp9vJxt7COllI8yUq+avkY4rk3DEmj3dudwJFJ3rb8i44EGf+KX/9UhkqLmBYEqvaa; _fbp=fb.1.1607510563367.1166911022; baby-isWide=wide; sid=800504d2b6b64afab26d9ce4a41913e02c25582a; overrideAbTestGroup=%5B%5D; bm_sv=3FE9C9AC2C44149A50312B934A194D16~dXxBTzjfKxzEu+6jbWqD2v4OMGxD70I+uhsYTIyJUuCnFONC7QhE5NQH5fYj2G2QNEzpuzXZoqOmKzvbMYhq/rVCZ4PUN07M90X/hcZZRDJy9FxRl8S1PIFcQaKsxDmeCN0kdsjFa4QQcXu2lA+hVJyPipHH/X5m0Urid02pr+E=");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        header.put(":authority", "www.coupang.com");
        header.put(":method", "GET");
        header.put(":scheme", "https");
        header.put("accept", "*/*");
        header.put("accept-encoding", "gzip, deflate, br");
        header.put("origin", "https://store.coupang.com");
        header.put("referer", "https://store.coupang.com");
        JSONObject object = URLFecter.getMethod(url,header);
        JSONArray products = object.getJSONObject("data").getJSONArray("products");
        JSONObject product = null;
        Map<String, String> pheader = new HashMap<>();
        int i =1;
        for (Object ob : products) {
            product = JSONObject.parseObject(ob.toString());
            System.out.println(product.getString("link"));
            pheader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
            pheader.put("Cookie", "PCID=42435659656025452526795; _fbp=fb.1.1607510563367.1166911022; sid=800504d2b6b64afab26d9ce4a41913e02c25582a; gd1=Y; ak_bmsc=E725C6B2C7B55F9B19B6FA1776E4209876D6A7659E4E0000A234D35FF13C3C0D~plaec5QnZlX0tphtdNGxuP3UZZr+LNA7A79GDIVwg4q31shT9v6DghHU4SKkd0cOs+eTOaTTCqkHisWZqLR5yhh/PgKBNWtYlijhofizdGc4rJXTyi/AfneZVmUWT6KgfH47XnYfRCqO+S88r5XXEQML2hOLiuQX/xsOb1aEx37K+KsYO1KRKm7CsfmMEUZKSZ6+YUO7j4Z9S+yzhbzUIQ84IlNGfzHEvsJIxemSkaoPCPeJ25yWsBbwwgevq5Yeqc; bm_mi=F993D54ECA3CEF429C76944993B3F342~AyNirbSC4N2CTZh0hgJwp5wR5+Q4APj07lby2477swsNLCSwEuayD8A5N3j9jJ2eWcqgtQiMsNmwmgrk/pF515mGEMWTDMsBguQhYK/OhwF/TOfKdKW1Y/JZT4AxlfdQezAVIfIHfa+IOwBZrRO5iafgtzY5F5Xvh8Z2E+TW5BCW1Hu2d2UNaW4Xpe8GfZajuMoqFN+DZHF1luKlwrZogbywDwA9OfrF8Mjo+f988bTBAaoboDWGN2jnzZeKlTEKAj1DJpafZfECt0O25AXzt7J28K6ylnpWuh4VM0GJzn4=; overrideAbTestGroup=%5B%5D; rememberme=true; ILOGIN=Y; CSID=JmF2HBn6rdIGjAw4Ju0YNyNxtGpCaj8by6qzeX17qwTj_FVud_DdmMBgUkYhdFoiW_JbggrApLi2xinFsycHwtEPBrimSHX5keDs7Pj9P-v6; CUPT=tjzHkQ5AZAO18wpItmEK_N8juQ7nlxrqBhAr0obcVJKXEG1mrVIXuryZl0xOyGPSDj9XD2wLFwg2JwmCRNWUmKnZvADaEdy5OfUzisv3FzMF4uUJGGQXntpEuYGovO8cJLiq_LTKTTCxUz6drfEUiMLsoQzNN6fh33mSZjC9OPIxR0-ITXIDJlcbJj9HqFX-ksj7WqakZ-mwxG_20sAmSDyEayFxdKTjdHdzV3-gwmJxeM300N3A9KA; member_srl=128318748; baby-isWide=wide; CPUSR_RL=aS%2FzeQJzVHL%2BOeRAabxrG8AZlN1mUfLYIZ64Cb2NUtZDql7wTr1gSMAcph1JyhzDYUt%2FaDBkASKQj4xln4Uc8pIprD4J9wx1r%2Flkszept8kfOtR5ma0mhw6rn28wwwMR64VKt1wHeqP7GoZXs8uJFeCEiQ0pADfhN%2F%2BNB6Dv3rYoL2gaTcFad%2FxfEZ%2FGDJFWqdrlK7nLKl1h0xD0EpPZKQg%3D; bm_sv=EA91E52185E887CE2DE232AFBEAC4AF5~q3c+aLFdQCU9dNup6vOAnT8CemS9Z5SadtrpTKRecAlQk6LUUx3QOOoYVoek1PkhRM0Q5MeI/Njw/ClqEtErhMuhB2eEpZavHQmN4Omv2uo2tx1owEx4JjXwFfEj8VbtuN7bAfzKRprr74T+HEub7UVUsQH0BssG7+PlIPcLJ30=");
            pheader.put(":authority", "www.coupang.com");
            pheader.put(":method", "GET");
            pheader.put(":scheme", "https");
            pheader.put("accept", "*/*");
            pheader.put("accept-encoding", "gzip, deflate, br");
            pheader.put("origin", "https://store.coupang.com");
//            pheader.put("referer", "https://store.coupang.com");
            URLFecter.URLParser(pheader, product.getString("link"));
            Thread.sleep(1000);
            i ++;
        }

        System.out.println("========================" + i);
//        System.out.printf(object.toJSONString());
    }
}