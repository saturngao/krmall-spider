package com.sp.main;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 * author qianyang 1563178220@qq.com
 * Mysql������QueryRunner����
 * һ�����ݿ�����࣬��ĳ���ֱ�ӵ��ü���
 */
public class MYSQLControl {

    /**
     * �����Լ������ݿ��ַ�޸�
     */
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/moviedata");
    static QueryRunner qr = new QueryRunner(ds);
    //��һ�෽��
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //�ڶ������ݿ��������
    public static void executeInsert(List<JdModel> jingdongdata) throws SQLException {
        /*
         * ����һ��Object���飬����
         * 3��ʾ�����������Լ������ݶ��������������
         * params[i][0]���Ƕ����鸳ֵ�������õ����ϵ�get����
         * 
         */
        Object[][] params = new Object[jingdongdata.size()][3];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = jingdongdata.get(i).getBookID();
            params[i][1] = jingdongdata.get(i).getBookName();
            params[i][2] = jingdongdata.get(i).getBookPrice();
        }
        qr.batch("insert into jingdongbook (bookID, bookName, bookPrice)"
                + "values (?,?,?)", params);
        System.out.println("ִ�����ݿ���ϣ�"+"�ɹ��������ݣ�"+jingdongdata.size()+"��");

    }
}