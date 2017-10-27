
package model;

/**
 * Document Object
 * A <b>Document<b> object contains the different attributes of 
 * a document of the system ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy
 * No methods so far, just constructor and attributes.  -Ivy
 *
 * 
 *
 * original code: 10-27-17
 * last update:
*/
public class Document {
    
    /**
     * Primary key of the document
     */
    protected int documentID;
    protected String description;
    /**
     * The absolute path of the document with respect to the root directory
     */
    protected String documentLocation;
    protected Folder folder;
    protected User createUser;

    public Document(String description, String documentLocation, Folder folder, User createUser) {
        this.description = description;
        this.documentLocation = documentLocation;
        this.folder = folder;
        this.createUser = createUser;
    }
}
