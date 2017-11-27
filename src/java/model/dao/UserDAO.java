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
import model.Document;
import model.MovingOutClearance;
import model.Occupation;
import model.TransactionReference;
import model.UserType;

/**
 *
 * A <b>User</b> object contains the different attributes of a user and its functions on the system.
 * this is a DAO object of a User.
 * 
 * PATCH NOTES:
 * - 10/30/2017: ADDED FB USER
 * - 11/07/2017: Used USERS from hoamis DB
 * 
 * @author Leebet-PC
 * @version 1.02 
 * @since 10/30/2017
 * @update 11/07/2017
 */
public class UserDAO {
    

    /**
     * Returns all the users of the database
     * @return result set of users
     */
    public static ResultSet getUsers(String user_id){
        ResultSet as= null;
        try{
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.USERS where userID != ?"); 
            st.setString(1,user_id);
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
    public static boolean joinGroup(String user_id, int group_id){
         
         try{
             Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.USERGROUPS(userID, userGroupID) VALUES (?,?);"); 
            st.setString(1, user_id);
            st.setInt(2, group_id);
       
            st.executeUpdate();
        }catch(Exception E){
            E.printStackTrace();
        }
         return false;
     }
    /*
    **THIS IS A DUMMY REGISTER ACCOUNT**
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
    */
    /**
     * Creating a new account for Facebook enabled login
     * @param fName
     * @param lName
     * @return nothing
     * 
     */
     public static boolean fbLog(String fName, String lName){
        try{
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO user(fName, lName, userID,passwd,usertype,bDate,photo,occupation,movingIn,movingOutclearID,trxID)"
                    + " VALUES ( ?, '1', 1);"); 
            st.setString(1, fName);

           int i = st.executeUpdate();
            System.out.println(i);
            return true;

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    } 
    
}
