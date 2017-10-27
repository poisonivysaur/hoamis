
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
 * Please seek Justine first before updating major parts of this code. -Ivy
 * No methods so far, just constructor and attributes.  -Ivy
 *
 * 
 *
 * original code: 10-27-17
 * last update:
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
    
    
}
