package model;

import java.io.Serializable;

/**
 * A <b>BillingDetail</b> object keeps the details of a billing such as the ID and transaction
 * 
 * @author ivy
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS:
 * Please seek Justine first before updating this code. 
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-28-17 by I. Lim
 * last update: 
*/
public class BillingDetail implements Serializable {

    protected Billing billing;
    protected TransactionReference trx;
	
	public BillingDetail(){}

    public BillingDetail(Billing billing, TransactionReference trx){
        this.billing = billing;
        this.trx = trx;
    }
    
    
    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public TransactionReference getTrx() {
        return trx;
    }

    public void setTrx(TransactionReference trx) {
        this.trx = trx;
    }

    
}
