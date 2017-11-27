
package model.dao;
import java.sql.*;
import model.*;

/**
 *
 * @author Leebet-PC
 */
public class DocumentPermissionDAO {
    /**
     * Checks if the user has a permission to read or download the file from the database
     * @param userGroupID
     * @param userid
     * @param documentid
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasReadPermission(int userGroupID, String userid, int documentid){     
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;

                
                    PreparedStatement st = con.prepareStatement("select fp.read from hoamis.documentpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.documents d on fp.documentID = d.documentID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and d.documentID =?;");
                    st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, documentid);
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
     * @param documentid 
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasUpdatePermission(int userGroupID, String userid, int documentid){
        
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;
                
                    PreparedStatement st = con.prepareStatement("select fp.update from hoamis.documentpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.documents d on fp.documentID = d.documentID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and d.documentID =?;");
                    st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, documentid);
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
     * @param documentid 
     * @param userid
     * @return a boolean that represents the byte result returned by the database
     */
    public static boolean hasDeletePermission(int userGroupID, String userid, int documentid){
        
            try {
                Connection con = DatabaseUtils.retrieveConnection();
                ResultSet rs=null;

                
                    PreparedStatement st = con.prepareStatement("select fp.delete from hoamis.documentpermissions fp \n" +
                                                                "join hoamis.usergroupmembers ug on fp.userGroupID = ug.userGroupID \n" +
                                                                "join hoamis.users u on ug.userID = u.userID join hoamis.documents d on fp.documentID = d.documentID\n" +
                                                                "where ug.userGroupID=? and ug.userID=? and d.documentID =?;");
                    st.setInt(1, userGroupID);
                    st.setString(2, userid);
                    st.setInt(3, documentid);
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
