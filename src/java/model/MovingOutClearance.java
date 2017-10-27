
package model;

import java.sql.Date;

/**
 *
 * @author justine
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
