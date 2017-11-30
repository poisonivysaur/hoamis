/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Document;
import model.Occupation;
import model.User;

/**
 * All Process and methods Related to Directory Modules resides within this class.<br />
 * 
 * @author Yuta
 */
public class DirectoryDAO {
    
    /**
     * Returns the existing Home Owners whose accounts are active.
     * 
     * @return ArrayList of User
     */
    public static ArrayList<User> getHomeowners(){
        ArrayList<User> homeowners = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID FROM USERS WHERE STATUS = 'active' AND USERTYPEID = 1 ORDER BY 4,3 DESC;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            User u = null;
            while(rs.next()){
                u = new User();
                Document docu = new Document();
                Occupation occu = new Occupation();
                occu.setOccupationID(rs.getInt("OCCUPATIONID"));
                u.setUserID(rs.getString(1));
                u.setUsertype(rs.getInt(2));
                u.setlName(rs.getString(3));
                u.setfName(rs.getString(4));
                u.setmName(rs.getString(5));
                u.setbDate(rs.getDate("BDATE"));
                docu.setDocumentID(rs.getInt("PHOTOID"));
                docu = getDocumentById(conn, docu);
                u.setPhoto(docu);
                u.setOccupation(occu);
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
        return homeowners;
    }
    
    
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<>();
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID FROM USERS WHERE STATUS = 'active' ORDER BY 4,3 DESC;";
        Connection conn = null;
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            User temp = null;
            while(rs.next()){
                temp = new User();
                Document docu = new Document();
                Occupation occu = new Occupation();
                occu.setOccupationID(rs.getInt("OCCUPATIONID"));
                temp.setUserID(rs.getString(1));
                temp.setUsertype(rs.getInt(2));
                temp.setlName(rs.getString(3));
                temp.setfName(rs.getString(4));
                temp.setmName(rs.getString(5));
                temp.setbDate(rs.getDate("BDATE"));
                docu.setDocumentID(rs.getInt("PHOTOID"));
                docu = getDocumentById(conn, docu);
                temp.setPhoto(docu);
                temp.setOccupation(occu);
                allUsers.add(temp);
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
        return allUsers;
    }
    
    public static Document getDocumentById(Connection conn, Document docu) throws Exception{
        PreparedStatement pStmt = conn.prepareStatement("SELECT DOCUMENTID, DOCUMENTLOCATION FROM DOCUMENTS WHERE DOCUMENTID = ?");
        pStmt.setInt(1, docu.getDocumentID());
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            docu.setDocumentLocation(rs.getString(2));
        }
        return docu;
    }
    
    public static User getUserById(String userid){
        User findUser = null;
        Connection conn = null;
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID FROM USERS WHERE STATUS = 'active' AND USERID = ?;";
        
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userid);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                findUser = new User();
                Document docu = new Document();
                Occupation occu = new Occupation();
                occu.setOccupationID(rs.getInt("OCCUPATIONID"));
                occu = getOccupationById(conn, occu);
                findUser.setUserID(rs.getString(1));
                findUser.setUsertype(rs.getInt(2));
                findUser.setlName(rs.getString(3));
                findUser.setfName(rs.getString(4));
                findUser.setmName(rs.getString(5));
                findUser.setbDate(rs.getDate("BDATE"));
                docu.setDocumentID(rs.getInt("PHOTOID"));
                docu = getDocumentById(conn, docu);
                findUser.setPhoto(docu);
                findUser.setOccupation(occu);
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
        
        return findUser;
    }
    
    public static Occupation getOccupationById(Connection conn, Occupation occupation) throws Exception{
        PreparedStatement pStmt = conn.prepareStatement("SELECT OCCUPATIONID, OCCUPATION FROM REF_OCCUPATION WHERE OCCUPATIONID = ?;");
        pStmt.setInt(1, occupation.getOccupationID());
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            occupation.setOccupation(rs.getString(2));
        }
        return occupation;
    }

}