package com.sp.main;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;


/*   
 *  �Ϸʹ�ҵ��ѧ ����ѧԺ qianyang 1563178220@qq.com
 */
public class JdongMain {
    // log4j����ʹ�ã�������뿴֮ǰд������
    static final Log logger = LogFactory.getLog(JdongMain.class);
    public static void main(String[] args) throws Exception {
        //��ʼ��һ��httpclient
        HttpClient client = new DefaultHttpClient();
        //����Ҫ��ȡ��һ����ַ��������Դ����ݿ��г�ȡ���ݣ�Ȼ������ѭ����������ȡһ��URL����
        String url="https://store.coupang.com/vp/vendors/C00257985/products?vendorName=Weihai+Hongyuan+Electronics+Co.%2C+Ltd.&productId=1082572752&outboundShippingPlaceId=";
//        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //ץȡ������
        List<JdModel> bookdatas=URLFecter.URLParser(client, url);
        //ѭ�����ץȡ������
        for (JdModel jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //��ץȡ�����ݲ������ݿ�
        MYSQLControl.executeInsert(bookdatas);
    }
}