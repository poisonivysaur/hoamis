/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import java.sql.*;
/**
 *
 * A class for validating user registration.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for user accounts.
 * this is <b>NOT</b> a <b>DAO</b> object
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */
public class Validate {
    public static boolean checkUser(String username, String password){
        try{
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","root","root"); 
            PreparedStatement st = con.prepareStatement("select * from hoamis.USERS where userID=? and passwd=?"); 
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            return rs.next();

        }catch(Exception E){
            E.printStackTrace();
        }
        return false;
    }
}
