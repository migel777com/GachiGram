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

    public static void addFollower(String follower, String addresser) throws SQLException {
        //part1
        Connection connection = getConnection();
        String followers = "";
        String sqlStatement = "SELECT followers FROM users WHERE username=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, addresser);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                followers = resultSet.getString("followers");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //part 2
        Connection connection_new = getConnection();
        if (!followers.equals("")) {
            followers += ",";
        }
        followers += follower;
        String sqlStatement_new = "UPDATE users SET followers = ? WHERE username = ?;";
        try {
            PreparedStatement ps_new = connection_new.prepareStatement(sqlStatement_new);
            ps_new.setString(1, followers);
            ps_new.setString(2, addresser);
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

    public static void addFriend(int user_id, String me, String friend) throws SQLException {
        //part1
        Connection connection = getConnection();
        String followers = "";
        String sqlStatement = "SELECT followers FROM users WHERE user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                followers = resultSet.getString("followers");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //deleting from followers
        String[] followers_Array = followers.split(",");
        List<String> followers_list;
        followers_list = new ArrayList<>(Arrays.asList(followers_Array));
        //followers_list.remove(followers_list.size()-1);
        followers_list.remove(friend);
        StringBuilder followers_string = new StringBuilder();
        for(String s : followers_list){
            followers_string.append(s);
            followers_string.append(",");
        }
        if (!followers_string.toString().equals("")) {
            followers_string = new StringBuilder(followers_string.substring(0, followers_string.length() - 1));
        }


        //appending to friends
        Connection connection_friends = getConnection();
        String friends = "";
        String sqlStatement_friends = "SELECT friends FROM users WHERE user_id=?";
        try {
            PreparedStatement ps = connection_friends.prepareStatement(sqlStatement_friends);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                friends = resultSet.getString("friends");
            }
            connection_friends.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (!friends.equals("")) {
            friends += ",";
        }
        friends += friend;

        //part 2
        Connection connection_new = getConnection();
        String sqlStatement_new = "UPDATE users SET followers = ?, friends = ? WHERE user_id = ?;";
        try {
            PreparedStatement ps_new = connection_new.prepareStatement(sqlStatement_new);
            ps_new.setString(1, followers_string.toString().toString());
            ps_new.setString(2, friends);
            ps_new.setInt(3, user_id);
            ps_new.executeUpdate();
            ps_new.close();
            connection_new.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //part 3 add friend to another friend
        User.responseFriendAddition(me, friend);

    }

    public static void responseFriendAddition(String friend, String addresser) throws SQLException {
        Connection connection = getConnection();
        String friendlist = "";
        String sqlStatement = "SELECT friends FROM users WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, addresser);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                friendlist = resultSet.getString("friends");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.close();

        if (!friendlist.equals("")) {
            friendlist += ",";
        }
        friendlist += friend;

        Connection connection_new = getConnection();
        String sqlStatement_new = "UPDATE users SET  friends = ? WHERE username = ?;";
        try {
            PreparedStatement ps_new = connection_new.prepareStatement(sqlStatement_new);
            ps_new.setString(1, friendlist);
            ps_new.setString(2, addresser);
            ps_new.executeUpdate();
            ps_new.close();
            connection_new.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getFollowers(int user_id) throws SQLException {
        Connection connection = getConnection();
        String followers = "";
        String sqlStatement = "SELECT followers FROM users WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                followers = resultSet.getString("followers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] followerList = followers.split(",");
        for(int i = 0; i< followerList.length; i++){
            System.out.println(followerList[i]);
        }
        connection.close();
        return followerList;

    }

    public static String[] getFriends(int user_id) throws SQLException {
        Connection connection = getConnection();
        String friends = "";
        String sqlStatement = "SELECT friends FROM users WHERE user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                friends = resultSet.getString("friends");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] friendList = friends.split(",");
        connection.close();
        return friendList;

    }

    public static void saveComment(int comment_post_id, int comment_author_id, String comment_content) {
        Connection connection = getConnection();
        String sqlStatement = "INSERT INTO comments (comment_post_id, comment_author_id, comment_content) VALUES (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setInt(1, comment_post_id);
            ps.setInt(2, comment_author_id);
            ps.setString(3, comment_content);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String isFriends(String username, int user_id) throws SQLException {
        String[] friends = getFriends(user_id);
        for (int i = 0; i < friends.length; i++) {
            if (friends[i].equals(username)) {
                return "yes";
            }
        }
        return "no";
    }


    public static int getUser(String name) {
        Connection connection = getConnection();
        String sqlStatement = "SELECT user_id FROM users WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
