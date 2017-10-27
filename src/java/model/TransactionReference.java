
package model;

/**
 *
 * @author justine
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
