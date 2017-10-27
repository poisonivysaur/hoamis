
package model;

/**
 *
 * @author justine
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
