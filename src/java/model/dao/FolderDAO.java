
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Document;

import model.*;

/**
 * Folder Object
 * A <b>Folder</b> object contains the different attributes of 
 * a folder ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update: 10/07/17 by L. Barraquias - Added a overloaded constructor
*/
public class FolderDAO {
   /**
    * Inserts a folder object to the database.
    * @param folder - the folder object to be inserted
    * @return a boolean to notify query success or failure
    */
   public static boolean addFolder(Folder folder){
        
       try{
           int last_inserted_id = 0;
           
           Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.FOLDERS(folderID,folderName, folderdesc, parentID,create_userID) VALUES (NULL,?,?,?,?);"); 
            st.setString(1, folder.getFolderName());
            st.setString(2, folder.getFolderdesc());
            st.setInt(3, folder.getParentFolder().getFolderID());
            st.setString(4, folder.getCreateUser().getUserID());
       
            st.executeUpdate();

            
            return true;
            
        }catch(Exception E){
            E.printStackTrace();
        } 
            
        return false;
    }
   
   /**
     * Checks if there is an existing document with similar name and description.
     * 
     * @param folder
     * @return a boolean to notify query success or failure. returns true if there is duplicate, returns false if there is none.
     */
    public static boolean checkDuplicateFolder(Folder folder) {
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.folders where folderName=? and parentID=?");
            st.setString(1, folder.getFolderName());
            st.setInt(2, folder.getParentFolder().getFolderID());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            }
            
        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }
    
   /**
    * Gets the folders of a user in the root folder, folderid = 1; 
    * @param user_id
    * @return a result set of folders object
    */
   public static ResultSet getRootFolders(String user_id) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.folders where create_userID=? and parentID=0");
            st.setString(1, user_id);
            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return as;
    }
   /**
    * Deletes a folder from the database and also all the parent files and folders by cascade delete
    * @param folderid
    * @return a boolean to notify query success or failure
    */
   public static boolean deleteFolder(int folderid) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("delete from hoamis.folders where folderID=? ");
            st.setInt(1, folderid);
            int i = st.executeUpdate();

            if(i > 0) {
                System.out.println("Folder successfully removed...");
            }
                return true;

        } catch (Exception E) {
            E.printStackTrace();
        }
        return false;
    }
   /**
    * Gets all the child folders of the current user.
    * @param folderID - current folder
    * @param userID
    * @return result set of folder objects from the database
    */
    public static ResultSet getChildFolders(int folderID, String userID) {
        ResultSet as = null;
        Connection con = null;
        try {
            con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.folders where parentID=? and create_userID=?");
            st.setInt(1, folderID);
            st.setString(2, userID);
            ResultSet rs = st.executeQuery();

            return rs;

        } catch (Exception E) {
            E.printStackTrace();
        }
        
        return as;
    }
    /**
     * shares the folders to the usergroups and specifies the boolean
     * @param folderid - folder to be shared
     * @param userGroupID
     * @param r - read
     * @param u - update
     * @param d - delete
     * @return a boolean to notify query success or failure
     */
    public static boolean shareFolder(int folderid,int userGroupID,byte r, byte u, byte d){
       try{
           System.out.println("folder-id: "+folderid);
           Connection con = DatabaseUtils.retrieveConnection();
           PreparedStatement st = con.prepareStatement("INSERT INTO hoamis.FOLDERPERMISSIONS(folderID,userGroupID,FOLDERPERMISSIONS.read,FOLDERPERMISSIONS.update,FOLDERPERMISSIONS.delete) VALUES (?,?,?,?,?);"); 
           st.setInt(1, folderid);
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
    /**
     * Gets the folders shared by a group.
     * @param groupID 
     * @param user_id
     * @return result set of folder objects from the database
     */
    public static ResultSet getSharedFolders(int groupID, String user_id) {
        ResultSet as = null;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select d.*,fp.* from hoamis.folderpermissions fp \n" +
                                                        "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                        "join hoamis.users u on ug.userID = u.userID join hoamis.folders d on fp.folderID = d.folderID\n" +
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
}
