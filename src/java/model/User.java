package model;

import java.sql.Date;
/**
 * User Object
 * A <b>user<b> object contains the different attributes of 
 * a user of the system ...
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
 * original code: 10-27-17 by J. Singca
 * last update:
*/
public class User {
    // not everyone has a setter, please consider when generating getters and setter
    protected String fName;
    protected String lName;
    protected String mName;
    protected String userID;
    protected String passwd;
    protected UserType usertype;
    protected Date bDate;
    protected Document photo;
    protected Occupation occupation;
    protected Date movingIn;
    protected MovingOutClearance movingOutclear;
    protected TransactionReference trx;

    public User(String fName, String lName, String mName,
            String passwd, UserType usertype, Date bDate, Document photo,
            Occupation occupation, Date movingIn, MovingOutClearance movingOutclear,
            TransactionReference trx) {
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.passwd = passwd;
        this.usertype = usertype;
        this.bDate = bDate;
        this.photo = photo;
        this.occupation = occupation;
        this.movingIn = movingIn;
        this.movingOutclear = movingOutclear;
        this.trx = trx;
    }
    /**
     * Method: getIDnum
     * returns the current ID number being used by the object
     * 
     * @param nothing
     * @return String containing the ID number of student
     * @throws nothing
     * @excepiton nothing
     * @since 10-27-17
     */
}
