package com.example.GachiGram.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    protected String username;
    protected String password;
    protected String[] friendList;

    private static Connection getConnection() {
        Context initialContext;
        Connection connection = null;
        try {
            initialContext = new InitialContext();
            Context envCtx = (Context) initialContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/db");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void registerUser(String username, String password) throws SQLException {
        Connection connection = getConnection();
        String sqlStatement = "INSERT INTO users (username, password, friends) VALUES (?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, "");
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int logInUser(String username, String password) throws SQLException {
        int id = -1;
        Connection connection = getConnection();
        String sqlStatement = "SELECT user_id FROM users WHERE username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
        return id;
    }

    public static void createPost(int author_id, String post_title, String post_content, String post_vis_status) throws SQLException {
        Connection connection = getConnection();
        String sqlStatement = "INSERT INTO posts (post_author_id, post_title, post_content,post_vis_status) VALUES (?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, author_id);
            ps.setString(2, post_title);
            ps.setString(3, post_content);
            ps.setString(4, post_vis_status);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
