
package model;

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
 * last update: 10-28-17 by I. Lim - added constructor & changed attribute datatypes
*/
public class Billing {
    private int billingID;
    protected Property property;
    protected int precedentBilling;
    protected double totalDue;
    protected double totalPaid;
    
    public Billing(int billingID, Property property, int precedentBilling, double totalDue, double totalPaid){
        this.billingID = billingID;
        this.property = property;
        this.precedentBilling = precedentBilling;
        this.totalDue = totalDue;
        this.totalPaid = totalPaid;
    }

    public int getBillingID(){
        return billingID;
    }

    public int getPrecedentBilling(){
        return precedentBilling;
    }

    public double getTotalDue(){
        return totalDue;
    }

    public double getTotalPaid(){
        return totalPaid;
    }

    public void setBillingID(int billingID){
        this.billingID = billingID;
    }

    public void setPrecedentBilling(int precedentBilling){
        this.precedentBilling = precedentBilling;
    }

    public void setTotalDue(double totalDue){
        this.totalDue = totalDue;
    }

    public void setTotalPaid(double totalPaid){
        this.totalPaid = totalPaid;
    }
}
