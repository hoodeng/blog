/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuteng.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wu
 */
public class DBTest {
    //驱动程序名
    static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
    static String url = "jdbc:mysql://localhost:3306/blog?useSSL=false";
    //MySQL配置时的用户名
    static String user = "root";
    //MySQL配置时的密码
    static String password = "xxxxx";

    public static void main(String[] args) {
        System.err.println("Hello");
        testInsert();
        testQuery();
    }

    static void testQuery() {
        //声明Connection对象
        Connection con = null;
        ResultSet rs = null;
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            String sql = "select * from blog";
            //2.创建statement类对象，用来执行SQL语句！！
            PreparedStatement statement = con.prepareStatement(sql);
            //要执行的SQL语句

            //3.ResultSet类，用来存放获取的结果集！！
            rs = statement.executeQuery();
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String id = null;
            String title = null;
            String content = null;
            while (rs.next()) {
                //获取stuid这列数据
                id = rs.getString(1);
                title = rs.getString(2);
                content = rs.getString(3);
                //输出结果
                System.out.println(id + "\n" + title + "\n" + content);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }

    static void testInsert() {
        String sql = "Insert into blog (title,content) VALUES (?,?)";
        //声明Connection对象
        Connection con = null;
        ResultSet rs = null;
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(sql);
            for (Blog blog : blogs) {
                ps.setString(1, blog.title);
                ps.setString(2, blog.content);
                ps.executeLargeUpdate();
            }
        } catch (Exception e) {

            System.err.println("e --> " + e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(con != null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    static class Blog {

        String title;
        String content;

        public Blog(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    static List<Blog> blogs;

    static {
        blogs = new ArrayList<>();
        blogs.add(new Blog("javaScript", "js是个好东西"));
       
    }
}
