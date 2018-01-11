/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuteng.blog.db;

import com.wuteng.blog.constants.DBConstant;
import static com.wuteng.blog.db.DBTest.driver;
import static com.wuteng.blog.db.DBTest.url;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deng.wu
 */
public class BlogDatabaseHandler {

    private boolean mSucessLoadedDriver;

    public BlogDatabaseHandler() {
        init();
    }

    public void insert(Blog blog) {
        assert (checkDriverLoaded());
        assert (blog.title == null || blog.content == null);
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connect();
            String sql = "Insert into blog (title,content) VALUES (?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, blog.title);
            ps.setString(2, blog.content);
            ps.executeLargeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BlogDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!= null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BlogDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(con != null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BlogDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Connection connect() throws SQLException {
        Connection con = DriverManager.getConnection(DBConstant.url, DBConstant.user, DBConstant.password);
        return con;
    }

    private boolean checkDriverLoaded() {
        return mSucessLoadedDriver;
    }

    private void init() {
        try {
            Class.forName(DBConstant.driver);
            mSucessLoadedDriver = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
    }
}
