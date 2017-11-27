
package model;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

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
 * last update: 10-28-17 by I. Lim - changed attributes and access specifiers
*/
public class TransactionJournal implements Serializable {
    protected int journalID;
    public Date trxDate;
    public ArrayList<TransactionList> trxList;
    
	public TransactionJournal(){}
    
    public TransactionJournal(int journalID, Date trxDate){
        this.journalID = journalID;
        this.trxDate = trxDate;
        this.trxList = null;
    }

    /**
     * returns the journal ID of the object
     * 
     * @param nothing
     * @return int containing the journal ID of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getJournalID() {
        return journalID;
    }

    /**
     * sets the journal ID of the object
     * 
     * @param journalID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setJournalID(int journalID) {
        this.journalID = journalID;
    }

    /**
     * returns the transaction journal attribute of the object
     * 
     * @param nothing
     * @return TransactionJournal object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Date getTrxDate() {
        return trxDate;
    }
    
    /**
     * sets the transaction date of the object
     * 
     * @param trxDate
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }
    
    
}
