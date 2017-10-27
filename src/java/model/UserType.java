
package model;

/**
 * UserType Object
 * A <b>user type<b> is a reference to the possible values for the usertype field of the User Class
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating major parts of this code. -Ivy
 * No methods so far, just constructor and attributes.  -Ivy
 *
 * 
 *
 * original code: 10-27-17
 * last update:
*/
public class UserType {
    
    protected int usertypeid;
    protected String usertype;

    public UserType(String usertype) {
        this.usertype = usertype;
    }
    
}
