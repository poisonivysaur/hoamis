package model;

import java.io.Serializable;

/**
 * A <b>Billing</b> object contains the different information of 
 * a billing to a home owner 
 * 
 * @author jana
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
 * original code: 10-28-17 by J. Doctolero 
 * last update: 10-28-17 by I. Lim - changed access specifiers
*/
public class PaymentDetail implements Serializable {
    protected int billingID;
    protected TransactionList trxList;
	
	public PaymentDetail(){}

    public PaymentDetail(Billing billing, TransactionList trxList){
        this.billingID = billing.getBillingID();
        this.trxList = trxList;
    }
    
    public int getBillingID() {
        return billingID;
    }

    public void setBillingID(Billing billingID) {
        this.billingID = billingID.getBillingID();
    }

    public TransactionList getTrxList() {
        return trxList;
    }

    public void setTrxList(TransactionList trxList) {
        this.trxList = trxList;
    }
    
    
}
