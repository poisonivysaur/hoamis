
package model;

/**
 * A <b>transaction reference</b> is a reference to all the transactions made across 
 * different types of transactions in the system
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please notify justine first before updating major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-28-17
 * last update: 10-28-17 by I. Lim - changed access specifiers
*/
public class TransactionReference {
    
    protected int trxID;
    public double amount;
    public double interest;
    public double totalamount;
    public String description;

    public TransactionReference(double amount, double interest, double totalamount, String desc) {
        this.amount = amount;
        this.interest = interest;
        this.totalamount = totalamount;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the transaction ID of the object
     * 
     * @param nothing
     * @return int containing the transaction ID of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getTrxID() {
        return trxID;
    }

    /**
     * sets the transaction ID of the object
     * 
     * @param trxID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    /**
     * returns the amount of the transaction
     * 
     * @param nothing
     * @return Double containing the amount of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public double getAmount() {
        return amount;
    }

    /**
     * sets the amount of the transaction
     * 
     * @param amount
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * returns the interest of the transaction
     * 
     * @param nothing
     * @return double containing the interest of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public double getInterest() {
        return interest;
    }

    /**
     * sets the interest of the transaction
     * 
     * @param interest
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setInterest(Double interest) {
        this.interest = interest;
    }

    /**
     * returns the total amount of the transaction
     * 
     * @param nothing
     * @return Double containing the total amount of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public double getTotalamount() {
        return totalamount;
    }

    /**
     * sets the total amount of the transaction
     * 
     * @param totalamount
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }
    
    
}
