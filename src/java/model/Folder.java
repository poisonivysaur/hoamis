
package model;

/**
 * Folder Object
 * A <b>Folder<b> object contains the different attributes of 
 * a folder ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy 10-27-17
 * No methods so far, just constructor and attributes.  -Ivy 10-27-17
 *
 * 
 *
 * original code: 10-27-17
 * last update:
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
