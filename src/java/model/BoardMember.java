
package model;

import java.sql.Date;

/**
 * BoardMember Object
 * A <b>board member<b> object contains the different attributes of 
 * a board member ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS:
 * Please seek Justine first before updating this code. @Ivy
 * No methods apart from parent class User so far, just constructor and attributes. -Ivy 10-27-17
 *
 * 
 *
 * original code: 10-27-17 by J. Sinca 
 * last update:
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
