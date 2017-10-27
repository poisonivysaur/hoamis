
package model;

import java.sql.Date;

/**
 *
 * @author justine
 */
public class BoardMember extends User{
    
    protected BoardPosition position;
    protected Date effectiveDate;
    protected Date endDate;
    protected BoardStatus status;
    protected Election election;

    public BoardMember(BoardPosition position, Date effectiveDate, Date endDate, BoardStatus status, Election election, String fName, String lName, String mName, String passwd, UserType usertype, Date bDate, Document photo, Occupation occupation, Date movingIn, MovingOutClearance movingOutclear, TransactionReference trx) {
        super(fName, lName, mName, passwd, usertype, bDate, photo, occupation, movingIn, movingOutclear, trx);
        this.position = position;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.status = status;
        this.election = election;
    }

    
    
    
}
