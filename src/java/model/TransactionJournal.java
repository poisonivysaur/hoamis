
package model;

import java.util.Date;

/**
 * A <b>TransactionJournal</b> object records the paid fees and dues of transactions
 * 
 * @author ivy
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please notify Ivy first before updating any major parts of this code.
 *
 * 
 *
 * original code: 10-28-17 by I. Lim
 * last update:
*/
public class TransactionJournal {
    protected int journalID;
    protected Date trxDate;
    protected double trxAmount;
    protected double trxAmountPaid;
    
    public TransactionJournal(int journalID, Date trxDate, double trxAmount, double trxAmountPaid){
        this.journalID = journalID;
        this.trxDate = trxDate;
        this.trxAmount = trxAmount;
        this.trxAmountPaid = trxAmountPaid;
    }

    /**
     * Method: getJournalID
     * returns the journal ID of the object
     * 
     * @param nothing
     * @return int containing the journal ID of the object
     * @throws nothing
     * @exception nothing
     * @since 10-28-17
     */
    public int getJournalID() {
        return journalID;
    }

    /**
     * Method: setJournalID
     * sets the journal ID of the object
     * 
     * @param journalID
     * @return nothing
     * @throws nothing
     * @exception nothing
     * @since 10-28-17
     */
    public void setJournalID(int journalID) {
        this.journalID = journalID;
    }

    /**
     * Method: getJournal
     * returns the transaction journal attribute of the object
     * 
     * @param nothing
     * @return TransactionJournal object
     * @throws nothing
     * @exception nothing
     * @since 10-28-17
     */
    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public double getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(double trxAmount) {
        this.trxAmount = trxAmount;
    }

    public double getTrxAmountPaid() {
        return trxAmountPaid;
    }

    public void setTrxAmountPaid(double trxAmountPaid) {
        this.trxAmountPaid = trxAmountPaid;
    }
    
    
}
