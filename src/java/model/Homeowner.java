
package model;

import java.sql.Date;

/**
 * 
 * @author justine
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
