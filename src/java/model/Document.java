
package model;

import java.io.Serializable;

/**
 * Document Object
 * A <b>Document</b> object contains the different attributes of 
 * a document of the system such as the folder containing the document,
 * its directory path.
 * 
 * @author justine
 * @version 1.002
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code.
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-27-17 by J. Singca
 * last update: 10-28-17 by I. Lim - added getters & setters
*/
public class Document implements Serializable {
    
    /**
     * Primary key of the document
     */
    private int documentID;
    protected String description;
    /**
     * The absolute path of the document with respect to the root directory
     */
    protected String documentLocation;
    protected Folder folder;
    protected User createUser;
    
    public Document(){}

    public Document(String description, String documentLocation, Folder folder, User createUser) {
        this.description = description;
        this.documentLocation = documentLocation;
        this.folder = folder;
        this.createUser = createUser;
    }
    
    /**
     * returns the document ID of the object
     * 
     * @param nothing
     * @return int containing the ID number of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getDocumentID() {
        return documentID;
    }

    /**
     * sets the document ID of the object
     * 
     * @param documentID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    /**
     * returns the description of the object
     * 
     * @param nothing
     * @return String containing the description of the document
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the object
     * 
     * @param description
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the directory path of the object
     * 
     * @param nothing
     * @return String containing the directory path of the document 
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getDocumentLocation() {
        return documentLocation;
    }

    /**
     * sets the document location attribute of the object
     * 
     * @param documentLocation
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setDocumentLocation(String documentLocation) {
        this.documentLocation = documentLocation;
    }

    /**
     * returns the folder containing the object
     * 
     * @param nothing
     * @return Folder object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Folder getFolder() {
        return folder;
    }

    /**
     * sets the folder attribute of the object
     * 
     * @param folder
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    /**
     * returns the user who created the document
     * 
     * @param nothing
     * @return User object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public User getCreateUser() {
        return createUser;
    }

    /**
     * sets the user who created the document
     * 
     * @param createUser
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }
    
}
