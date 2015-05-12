package com.zh.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlServerJDBC {
    public static void main(String args[]) {

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ����JDBC����

        //SQL SERVER 2008��ʹ��localhost,127.0.0.1�ͱ���ʵ��ip192.168.0.101����������SQL SERVER 2008
        //String dbURL = "jdbc:sqlserver://localhost:5419;DatabaseName=InsideTSQL2008";
        //String dbURL = "jdbc:sqlserver://127.0.0.1:5419;DatabaseName=InsideTSQL2008"; 
        //String dbURL = "jdbc:sqlserver://192.168.0.101:5419;DatabaseName=InsideTSQL2008"; 
        
        //SQL SERVER 2012���˿ںŴ�ԭ����5419�����5413�����ݿ����Ʊ����TSQL2012
        //������SQL SERVER 2008һ��������ʹ��localhost��127.0.0.1��192.168.0.101
        //String dbURL = "jdbc:sqlserver://localhost:5413;DatabaseName=TSQL2012"; 
        //String dbURL = "jdbc:sqlserver://127.0.0.1:5413;DatabaseName=TSQL2012"; 
        String dbURL = "jdbc:sqlserver://192.168.0.101:5413;DatabaseName=TSQL2012"; 
    
        String userName = "sa"; // �û���
        String userPwd = "sa12345"; // ����

        try {
            // ����SQLSERVER JDBC��������
            Class.forName(driverName);
            //�������ݿ�����
            Connection connect = DriverManager.getConnection(dbURL, userName,userPwd);
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from HR.Employees;");
            while (rs.next()) {
                System.out.println(rs.getString("lastname"));
            }
        } catch (ClassNotFoundException e) {
            System.out.print("Error loading SQLServer Driver!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }
}