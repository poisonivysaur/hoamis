
package model;

import java.sql.Date;

/**
 * Homeowner Object
 * A <b>Homeowner<b> object contains the different attributes of 
 * a homeowner of the system and is a child class of the User parent class ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy
 *
 * 
 *
 * original code: 10-27-17 by J. Singca
 * last update:
*/
public class Homeowner extends User{
    
    protected int blocknum;
    protected int lotnum;

    // Make this readable by making it fit in the screen lang

    public Homeowner(int blocknum, int lotnum, String fName, String lName, String mName, String passwd, UserType usertype, Date bDate, Document photo, Occupation occupation, Date movingIn, MovingOutClearance movingOutclear, TransactionReference trx) {
        super(fName, lName, mName, passwd, usertype, bDate, photo, occupation, movingIn, movingOutclear, trx);
        this.blocknum = blocknum;
        this.lotnum = lotnum;
    }
    
}
