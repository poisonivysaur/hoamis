
package model;

/**
 *
 * @author justine
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
    
}
