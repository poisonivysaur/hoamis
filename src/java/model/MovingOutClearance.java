
package model;

import java.sql.Date;

/**
 * MovingOutClearance Object
 * A <b>MovingOutClearance<b> object contains the different attributes of 
 * a moving out clearance ...
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
public class MovingOutClearance {
    
    protected int clearanceID;
    protected Date movingoutdate;
    protected BoardMember financeclear;
    protected BoardMember securityclear;
    protected BoardMember violationsclear;
    protected TransactionReference trx;

    public MovingOutClearance(Date movingoutdate, BoardMember financeclear, BoardMember securityclear, BoardMember violationsclear, TransactionReference trx) {
        this.movingoutdate = movingoutdate;
        this.financeclear = financeclear;
        this.securityclear = securityclear;
        this.violationsclear = violationsclear;
        this.trx = trx;
    }

    
    
}
