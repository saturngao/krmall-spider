package com.sp.main;


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
        System.out.printf(object.toJSONString());
    }
}