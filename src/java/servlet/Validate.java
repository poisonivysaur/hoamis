package others;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

public class Validate {
    public static boolean checkUser(String userID, String password){
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","password"); 
            PreparedStatement ps = con.prepareStatement("select * from users where userID= ? and passwd= ?"); 
            ps.setString(1, userID);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
   
    
    public static int checkUserType(String userID, String password){
        
        int userTypeCnt = -1;
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","password"); 
            PreparedStatement ps = con.prepareStatement("select usertypeID from users where userID=? and passwd=?"); 
            ps.setString(1, userID);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return Integer.parseInt(rs.getString("usertypeID"));
            }

        }catch(Exception E){
            E.printStackTrace();
        }
        return userTypeCnt;
    }
}
