/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Yuta
 */
public class UserManagementDAO {
    
    public static boolean deactivateUser(String userId){
        boolean isUpdated = false;
        String sql = "UPDATE USERS SET STATUS='inactive' WHERE USERID = ?";
        Connection conn = null;
        
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            
            int updated = pStmt.executeUpdate();
            
            if(updated != 0){
                isUpdated = true;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        
        return isUpdated;
    }
    
}
