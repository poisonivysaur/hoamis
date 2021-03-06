
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dao.DatabaseUtils;

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
 * last update:
*/
public class Folder implements Serializable {
    
    protected int folderID;
    protected String folderName;
    protected String folderdesc;
    protected Folder parentFolder;
    protected User createUser;
	
	public Folder(){}

    public Folder(String folderName, String folderdesc, Folder parentFolder, User createUser) {
        this.folderName = folderName;
        this.folderdesc = folderdesc;
        this.parentFolder = parentFolder;
        this.createUser = createUser;
    }
    
    public Folder(int folderID){
        this.folderID = folderID;
        try {
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement("select * from hoamis.FOLDERS where folderID=?");
            st.setInt(1, folderID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                this.folderName = rs.getString("folderName");
                this.folderdesc = rs.getString("folderdesc");
                int parentID = rs.getInt("parentID");
                if(rs.wasNull()){
                    this.parentFolder = null;
                }
                else{
                    this.parentFolder = new Folder(parentID);
                }
                this.createUser = new User(rs.getString("create_userID"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public int getFolderID() {
        return folderID;
    }

    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderdesc() {
        return folderdesc;
    }

    public void setFolderdesc(String folderdesc) {
        this.folderdesc = folderdesc;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
    
}
