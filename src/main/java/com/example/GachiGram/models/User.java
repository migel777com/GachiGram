package com.example.GachiGram.models;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void createPost(int author_id, String post_title, String post_content, String post_vis_status, int commendable) throws SQLException {
        Connection connection = getConnection();
        String sqlStatement = "INSERT INTO posts (post_author_id, post_title, post_content,post_vis_status, commendable) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, author_id);
            ps.setString(2, post_title);
            ps.setString(3, post_content);
            ps.setString(4, post_vis_status);
            ps.setInt(5, commendable);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addFollower(int user_id,String follower) throws SQLException {
        //part1
        Connection connection = getConnection();
        String followers = "";
        String sqlStatement = "SELECT followers FROM users WHERE user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                followers = resultSet.getString("folllowers");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //part 2
        Connection connection_new = getConnection();
        followers += follower+",";
        followers = followers.substring(0, followers.length() - 1);
        String sqlStatement_new = "UPDATE users SET followers = ? WHERE user_id = ?;";
        try {
            PreparedStatement ps_new = connection_new.prepareStatement(sqlStatement_new);
            ps_new.setString(1, followers);
            ps_new.setInt(2, user_id);
            ps_new.executeUpdate();
            ps_new.close();
            connection_new.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean suchUserExits(String username) throws SQLException {
        Connection connection = getConnection();
        String sqlStatement = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();
        return false;
    }

    public static void addFriend(int user_id,String friend) throws SQLException {
        //part1
        Connection connection = getConnection();
        String followers = "";
        String sqlStatement = "SELECT followers FROM users WHERE user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                followers = resultSet.getString("folllowers");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //deleting from followers
        String followers_Array[] = followers.split(",");
        List<String> followers_list = new ArrayList<>();
        followers_list = Arrays.asList(followers_Array);
        followers_list.remove(followers_list.size()-1);
        followers_list.remove(friend);
        String followers_string = "";
        for(String s : followers_list){
            followers_string += s;
            followers_string += ",";
        }
        followers_string = followers_string.substring(0, followers_string.length() - 1);



        //appending to friends
        Connection connection_friends = getConnection();
        String friends = "";
        String sqlStatement_friends = "SELECT friends FROM users WHERE user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement_friends);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                friends = resultSet.getString("friends");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String new_friends = friends +","+ friend;

        //part 2
        Connection connection_new = getConnection();
        String sqlStatement_new = "UPDATE users SET followers = ? and friends = ? WHERE user_id = ?;";
        try {
            PreparedStatement ps_new = connection_new.prepareStatement(sqlStatement_new);
            ps_new.setString(1, followers_string);
            ps_new.setString(1, new_friends);
            ps_new.setInt(2, user_id);
            ps_new.executeUpdate();
            ps_new.close();
            connection_new.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
