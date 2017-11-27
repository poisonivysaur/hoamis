
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dao.*;
import model.Document;
import model.User;
import model.Folder;


/**
 *
 * A <b>Document Object</b> object that contains the different attributes of a group and
 * its functions on the system. this is a DAO object of a User Group.
 *
 *
 * @author Leebet Barraquias
 * @version 1.0
 * @since 10/29/2017
 * 
 * original code: 11/22/17 by Leebet Barraquias
 * last update: 11/07/17 by L. Barraquias - added getters & setters
 */
public class DocumentDAO {
   
    
    public static ResultSet getAllDocuments() {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.documents");
            

            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }
    /**
     * Gets the extension name of the given file
     * @param file
     * @return String that corresponds to the extension of the file
     */
    public static String getFileExtension(String file) {
        
        if(file.lastIndexOf(".") != -1 && file.lastIndexOf(".") != 0)
        return file.substring(file.lastIndexOf(".")+1);
        else return "file";
    }
    /**
     * Adds a document object to the database
     * @param document
     * @return a boolean to notify query success or failure
     */
    public static boolean addDocument(Document document){  
       try{
           int last_inserted_id = 0;
           System.out.println(document.getDocumentLocation());
           Connection con = DatabaseUtils.retrieveConnection();
           PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.DOCUMENTS(documentID,description, documentLocation, folderID,create_userID) VALUES (NULL,?,?,?,?);"); 
           st.setString(1, document.getDescription());
           st.setString(2, document.getDocumentLocation());
           st.setInt(3, document.getFolder().getFolderID());
           st.setString(4, document.getCreateUser().getUserID());
       
           st.executeUpdate();
           return true;
            
        }catch(Exception E){
            E.printStackTrace();
        } 
            
        return false;
    }
    /**
     * Gets the documents from a specified <b> folder  </b>
     * @param user_id
     * @param folderID
     * @return a result set of document objects 
     */
    public static ResultSet getFolderDocuments(String user_id, int folderID) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.documents where create_userID=? and folderID=?");
            st.setString(1, user_id);
            st.setInt(2, folderID);
            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }
    /**
     * Gets the documents shared by a user in a <b> group </b>
     * @param groupID
     * @param user_id
     * @return a result set of document objects
     */
     public static ResultSet getSharedDocuments(int groupID, String user_id){
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            
            PreparedStatement st = con.prepareStatement("select d.* from hoamis.documentpermissions dp \n" +
                                                        "join hoamis.usergroupmembers ug on dp.userGroupID = ug.userGroupID \n" +
                                                        "join hoamis.users u on ug.userID = u.userID join hoamis.documents d on dp.documentID = d.documentID\n" +
                                                        "where ug.userGroupID=? and ug.userID=?;");
            st.setInt(1, groupID);
            st.setString(2, user_id);
            ResultSet rs = st.executeQuery();
            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }  
   /**
    * Deletes a document from the documents objects
    * @param document
    * @return a boolean to notify query success or failure
    */
    public static boolean deleteDocument(Document document) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("delete from hoamis.documents where documentID=? and folderID=? ");
            st.setInt(1, document.getDocumentID());
            st.setInt(2, document.getFolder().getFolderID());
            int i = st.executeUpdate();

            if(i > 0) {
                System.out.println("Document successfully removed...");
            }
                return true;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }
    /**
     * Renames the document object
     * @param document
     * @param desc
     * @return a boolean to notify query success or failure
     */
    public static boolean updateDocument(Document document, String desc) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("update hoamis.documents set description=? where documentID=? and folderID=? ");
            st.setString(1, desc);
            st.setInt(2, document.getDocumentID());
            st.setInt(3, document.getFolder().getFolderID());
            int i = st.executeUpdate();

            if(i > 0) {
                System.out.println("Document successfully updated...");
            }
                return true;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }
    /**
     * Checks if there is an existing document with similar name and description.
     * 
     * @param document
     * @return a boolean to notify query success or failure
     */
    public static boolean checkDuplicateDoc(Document document) {
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.documents where description=? and folderID=?");
            st.setString(1, document.getDescription());
            st.setInt(2, document.getFolder().getFolderID());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return false;
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
        return true;
    }
    /**
     * Shares a document to a usergroup and grants the specified permissions
     * @param document
     * @param userGroupID
     * @param r - read
     * @param u - update
     * @param d - delete
     * @return a boolean to notify query success or failure
     */
    public static boolean shareDocument(Document document,int userGroupID,byte r, byte u, byte d){
       try{
           int last_inserted_id = 0;
           System.out.println(document.getDocumentLocation());
           Connection con = DatabaseUtils.retrieveConnection();
           PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.DOCUMENTPERMISSIONS (documentID,userGroupID,DOCUMENTPERMISSIONS.read,DOCUMENTPERMISSIONS.update,DOCUMENTPERMISSIONS.delete) VALUES (?,?,?,?,?);"); 
           st.setInt(1, document.getDocumentID());
           st.setInt(2, userGroupID);
           st.setByte(3, r);
           st.setByte(4, u);
           st.setByte(5, d);
       
           st.executeUpdate();
           return true;
            
        }catch(Exception E){
            E.printStackTrace();
        } 
        return false;
    }
    
    
    public static void main(String[] args) throws Exception{
        System.out.println("TESTING");
        
        ResultSet rs = getFolderDocuments("ivylim", 0);
        
        while(rs.next()){
            System.out.println(rs.getString(2));
        }
        
        System.out.println("End TESTING");
    }
}
