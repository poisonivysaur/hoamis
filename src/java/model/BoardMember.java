
package model;

import java.sql.Date;
import java.io.Serializable;

/**
 * BoardMember Object
 * A <b>board member</b> object contains the different attributes of 
 * a board member and the different functions it can perform on the system.
 * 
 * @author justine
 * @version 1.002
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS:
 * Please seek Justine first before updating this code. 
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-27-17 by J. Sinca 
 * last update: 10-28-17 by I. Lim - added getters & setters
*/
public class BoardMember extends User implements Serializable{
    
    protected BoardPosition position;
    protected Date effectiveDate;
    protected Date endDate;
    protected BoardStatus status;
    protected Election election;
	
	public BoardMember(){}

    public BoardMember(BoardPosition position, Date effectiveDate, Date endDate, BoardStatus status, Election election, String fName, String lName, String mName, String passwd, UserType usertype, Date bDate, Document photo, Occupation occupation, Date movingIn, MovingOutClearance movingOutclear, TransactionReference trx) {
        super();
        this.position = position;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.status = status;
        this.election = election;
    }

    /**
     * returns the board position of the object
     * 
     * @return BoardPosition object
     * @since 10-28-17
     */
    public BoardPosition getPosition() {
        return position;
    }

    /**
     * sets the board position of the object
     * 
     * @param position position of the board member
     * @since 10-28-17
     */
    public void setPosition(BoardPosition position) {
        this.position = position;
    }

    /**
     * returns date of effectivity of the object
     * 
     * @return Date object
     * @since 10-28-17
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * sets the date of effectivity of the object
     * 
     * @param effectiveDate
     * @since 10-28-17
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * returns the end date of effectivity of the object
     * 
     * @return Date object
     * @since 10-28-17
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * sets the end date of effectivity of the object
     * 
     * @param endDate
     * @since 10-28-17
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    

    /**
     * sets the status of effectivity of the object
     * 
     * @param status
     * @since 10-28-17
     */
    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    /**
     * returns the election attribute of the object
     * 
     * @return Election object
     * @since 10-28-17
     */
    public Election getElection() {
        return election;
    }

    /**
     * sets the election attribute of the object
     * 
     * @param election
     * @since 10-28-17
     */
    public void setElection(Election election) {
        this.election = election;
    }
    
}
