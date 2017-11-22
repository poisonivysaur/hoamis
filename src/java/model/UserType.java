
package model;

/**
 * UserType Object
 * A <b>user type</b> is a reference to the possible values for the usertype field of the User Class
 * 
 * @author justine
 * @version 1.002
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update: 10-28-17 by I. Lim - added getters & setters
*/
public class UserType {
    
    private int usertypeid;
    protected String usertype;

    public UserType(String usertype) {
        this.usertype = usertype;
    }

    /**
     * returns the user type ID of the object
     * 
     * @param nothing
     * @return int containing the user type ID of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getUsertypeID() {
        return usertypeid;
    }

    /**
     * sets the user type ID of the object
     * 
     * @param usertypeid
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setUsertypeID(int usertypeid) {
        this.usertypeid = usertypeid;
    }

    /**
     * returns the user type of the object
     * 
     * @param nothing
     * @return String containing the user type of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getUsertype() {
        return usertype;
    }

    /**
     * sets the user type of the object
     * 
     * @param usertype
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    
}
