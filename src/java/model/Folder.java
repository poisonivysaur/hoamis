
package model;

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
public class Folder {
    
    protected int folderID;
    protected String folderName;
    protected String folderdesc;
    protected Folder parentFolder;
    protected User createUser;

    public Folder(String folderName, String folderdesc, Folder parentFolder, User createUser) {
        this.folderName = folderName;
        this.folderdesc = folderdesc;
        this.parentFolder = parentFolder;
        this.createUser = createUser;
    }
    /**
     * mark did this two constructors below
     * @return 
     */
    public Folder(String folderName, String folderdesc,  User createUser) {
        this.folderName = folderName;
        this.folderdesc = folderdesc;
        this.parentFolder = null;
        this.createUser = createUser;
    }
    public Folder() {
        
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
