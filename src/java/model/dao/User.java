/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

import java.util.*;

/**
 *
 * A <b>User</b> object contains the different attributes of a user and its functions on the system.
 * this is a DAO object of a User.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for groups.
 * 
 * @author Leebet-PC
 * @version 1.0 
 * @since 10/30/2017
 */
public class User {

    /**
     *
     */
    protected int user_id;

    /**
     *
     * @return
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     */
    protected String username;
          
    /**
     * Returns user identification number given username
     * @param username 
     * @return
     */
    public static int getUserID(String username){
        int res =-1;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("select * from user where username=?"); 
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
             if(rs.next()){
                 return Integer.parseInt(rs.getString("user_id"));
             }

        }catch(Exception E){
            E.printStackTrace();
        }
        return res;
    }

    /**
     * Returns all the users of the database
     * @return result set of users
     */
    public ResultSet getUsers(){
        ResultSet as= null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("select * from TestForumDB.user where username != ?"); 
            st.setString(1,this.username);
            ResultSet rs = st.executeQuery();
             
            return rs;
             
        }catch(Exception E){
            E.printStackTrace();
        }
        return as;
    }

    /**
     *
     * @param user_id user identification number
     * @param group_id group identification number
     * @return
     */
    public static boolean joinGroup(int user_id, int group_id){
         
         try{
           Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("INSERT INTO TestForumDB.membership(user_user_id, group_group_id,membership_type) VALUES (?,?,1);"); 
            st.setInt(1, user_id);
            st.setInt(2, group_id);
       
            st.executeUpdate();
        }catch(Exception E){
            E.printStackTrace();
        }
         return false;
     }
    
     public static boolean pazucc(String username, String password, int usertype){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("INSERT INTO user(username, password, usertype) VALUES ( ?, ?, ?);"); 
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, usertype);

            
           int i = st.executeUpdate();
            System.out.println(i);
            return true;

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
     public static boolean fbLog(String username){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestForumDB","root","root"); 
            PreparedStatement st = con.prepareStatement("INSERT INTO user(username, password, usertype) VALUES ( ?, '1', 1);"); 
            st.setString(1, username);

           int i = st.executeUpdate();
            System.out.println(i);
            return true;

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    } 
    
    
}
