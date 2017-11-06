
package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * A Dummy servlet for adding accounts.
 * 
 * <b>IMPORTANT NOTE:</b> this program still does not have the final database object for adding accounts.
 * 
 * @author Leebet Barraquias
 * @version 1.0 
 * @since 10/30/2017
 */

public class AddAccount {
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
}
