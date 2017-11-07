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
public class User {
    

    
    private String fName;
    private String lName;
    private String mName;
    private String userID;
    private String passwd;
    private UserType usertype;
    private java.sql.Date bDate;
    private Document photo;
    private Occupation occupation;
    private java.sql.Date movingIn;
    private MovingOutClearance movingOutclear;
    private TransactionReference trx;

    public User(String fName, String lName, String mName, String userID, String passwd, UserType usertype, java.sql.Date bDate, Document photo, Occupation occupation, java.sql.Date movingIn, MovingOutClearance movingOutclear, TransactionReference trx) {
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.userID = userID;
        this.passwd = passwd;
        this.usertype = usertype;
        this.bDate = bDate;
        this.photo = photo;
        this.occupation = occupation;
        this.movingIn = movingIn;
        this.movingOutclear = movingOutclear;
        this.trx = trx;
    }
    public User(String userID){
        
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis", "root", "root");
            PreparedStatement st = con.prepareStatement("select * from hoamis.USERS where userID=?");
            st.setString(1, userID);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.userID = userID;
                this.fName = rs.getString("fname");
                this.lName = rs.getString("lname");
                this.mName = rs.getString("userID");
                this.userID = rs.getString("userID");
                this.passwd = rs.getString("userID");
                //this.usertype = new UserType(rs.getString("userID"));
                //this.bDate = rs.getDate("userID");
                //this.photo = rs.getString("userID");
                //this.occupation = rs.getString("userID");
                //this.movingIn = rs.getString("userID");
                //this.movingOutclear = rs.getString("userID");
                //this.trx = rs.getInt("userID");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public User(){
    }
    public String getfName() {
        return fName;
    }
   
    public String getlName() {
        return lName;
    }
   
    public String getUserID() {
        return userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
        
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    

    /**
     * Returns all the users of the database
     * @return result set of users
     */
    public ResultSet getUsers(){
        ResultSet as= null;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","root"); 
            PreparedStatement st = con.prepareStatement("select * from hoamis.USERS where userID != ?"); 
            st.setString(1,this.getUserID());
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
           Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","root"); 
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
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","root"); 
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
