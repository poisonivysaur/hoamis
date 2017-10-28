
package model;

/**
 * UserType Object
 * A <b>user type<b> is a reference to the possible values for the usertype field of the User Class
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
    
    protected int usertypeid;
    protected String usertype;

    public UserType(String usertype) {
        this.usertype = usertype;
    }

    public int getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(int usertypeid) {
        this.usertypeid = usertypeid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    
}
