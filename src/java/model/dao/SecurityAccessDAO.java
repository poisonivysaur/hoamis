/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author Yuta
 */
public class SecurityAccessDAO {
    
    
    public static User loginUserProcess(User tryLogin){
        Connection conn = null;
        PreparedStatement pStmt = null;
        User loginUser = null;
        String sql = "SELECT USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME FROM USERS WHERE USERID = ? AND PASSWD = ?;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, tryLogin.getUserID());
            pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                loginUser = new User();
                loginUser.setUserID(rs.getString(1));
                loginUser.setPasswd(rs.getString(2));
                loginUser.setUsertype(rs.getInt(3));
                loginUser.setlName(rs.getString(4));
                loginUser.setfName(rs.getString(5));
                loginUser.setmName(rs.getString(6));
            }
            
        }catch(Exception e){
            e.printStackTrace();
            loginUser = null;
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return loginUser;
    }
    
}
