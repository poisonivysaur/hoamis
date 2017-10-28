
package model;

import java.sql.Date;

/**
 * BoardMember Object
 * A <b>board member<b> object contains the different attributes of 
 * a board member and the different functions it can perform on the system
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

    /**
     * Method: getPosition
     * returns the board position of the object
     * 
     * @param nothing
     * @return BoardPosition object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public BoardPosition getPosition() {
        return position;
    }

    /**
     * Method: setTrx
     * sets the board position of the object
     * 
     * @param position
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setPosition(BoardPosition position) {
        this.position = position;
    }

    /**
     * Method: getEffectiveDate
     * returns date of effectivity of the object
     * 
     * @param nothing
     * @return Date object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Method: setEffectiveDate
     * sets the date of effectivity of the object
     * 
     * @param effectiveDate
     * @return nothin
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Method: getEndDate
     * returns the end date of effectivity of the object
     * 
     * @param nothing
     * @return Date object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Method: setEndDate
     * sets the end date of effectivity of the object
     * 
     * @param endDate
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Method: getStatus
     * returns the status of the object
     * 
     * @param nothing
     * @return BoardStatus object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public BoardStatus getStatus() {
        return status;
    }

    /**
     * Method: setStatus
     * sets the status of effectivity of the object
     * 
     * @param status
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    /**
     * Method: getElection
     * returns the election attribute of the object
     * 
     * @param nothing
     * @return Election object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Election getElection() {
        return election;
    }

    /**
     * Method: setElection
     * sets the election attribute of the object
     * 
     * @param election
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setElection(Election election) {
        this.election = election;
    }
    
}
