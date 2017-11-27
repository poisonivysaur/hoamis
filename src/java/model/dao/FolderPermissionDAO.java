/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Leebet-PC
 */

public class FolderPermissionDAO {
    /**
     * Checks if the user has a permission to read or download the file from the database
     * @param userGroupID
     * @param userid
     * @param folderid
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasReadPermission(int folderid, String userid, int userGroupID){
        
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;

                
                    PreparedStatement st = con.prepareStatement("select fp.read from hoamis.folderpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.folders d on fp.folderID = d.folderID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and fp.folderID=?;");
                     st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, folderid);
                    rs = st.executeQuery();
                    
                while (rs.next()) {
                    Byte r = new Byte("1");
                    if (rs.getByte("read") == r){
                        return true;
                    }
                }      
            } catch (Exception E) {
                E.printStackTrace();
            }
        return false;
    }
    /**
     * Checks if the user has a permission to update or rename the file from the database
     * @param userGroupID
     * @param userid
     * @param folderid
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasUpdatePermission(int folderid, String userid, int userGroupID){
        
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;
                
                    PreparedStatement st = con.prepareStatement("select fp.update from hoamis.folderpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.folders d on fp.folderID = d.folderID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and fp.folderID=?;");
                    st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, folderid);
                    rs = st.executeQuery();
                    
                while (rs.next()) {
                    Byte r = new Byte("1");
                    if (rs.getByte("update") == r){
                        return true;
                    }
                }      
            } catch (Exception E) {
                E.printStackTrace();
            }
        return false;
    }
    /**
     * Checks if the user has a permission to delete or remove the file from the database
     * @param userGroupID
     * @param userid
     * @param folderid
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasDeletePermission(int folderid, String userid, int userGroupID){
        
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;
                
                    PreparedStatement st = con.prepareStatement("select fp.delete from hoamis.folderpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.folders d on fp.folderID = d.folderID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and fp.folderID=?;");
                    st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, folderid);
                    rs = st.executeQuery();
                    
                while (rs.next()) {
                    Byte r = new Byte("1");
                    if (rs.getByte("delete") == r){
                        return true;
                    }
                }      
            } catch (Exception E) {
                E.printStackTrace();
            }
        return false;
    }
}
