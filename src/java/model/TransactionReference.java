
package model;

/**
 * TransactionReference Object
 * A <b>transaction reference<b> is a reference to all the transactions made
 * in the system
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update: 10-28-17 by I. Lim - added getters & setters
*/
public class TransactionReference {
    
    protected int trxID;
    protected Double amount;
    protected Double interest;
    protected Double totalamount;

    public TransactionReference(Double amount, Double interest, Double totalamount) {
        this.amount = amount;
        this.interest = interest;
        this.totalamount = totalamount;
    }

    /**
     * Method: getTrxID
     * returns the transaction ID of the object
     * 
     * @param nothing
     * @return int containing the transaction ID of the object
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public int getTrxID() {
        return trxID;
    }

    /**
     * Method: setTrxID
     * sets the transaction ID of the object
     * 
     * @param trxID
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    /**
     * Method: getAmount
     * returns the amount of the transaction
     * 
     * @param nothing
     * @return Double containing the amount of the transaction
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Method: setAmount
     * sets the amount of the transaction
     * 
     * @param amount
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Method: getInterest
     * returns the interest of the transaction
     * 
     * @param nothing
     * @return double containing the interest of the transaction
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Double getInterest() {
        return interest;
    }

    /**
     * Method: setInterest
     * sets the interest of the transaction
     * 
     * @param interest
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setInterest(Double interest) {
        this.interest = interest;
    }

    /**
     * Method: getTotalamount
     * returns the total amount of the transaction
     * 
     * @param nothing
     * @return Double containing the total amount of the transaction
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public Double getTotalamount() {
        return totalamount;
    }

    /**
     * Method: setTotalamount
     * sets the total amount of the transaction
     * 
     * @param totalamount
     * @return nothing
     * @throws nothing
     * @excepiton nothing
     * @since 10-28-17
     */
    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }
    
    
}
