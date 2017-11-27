
package model;

import java.io.Serializable;

/**
 * A <b>Billing</b> object contains the different information of 
 * a billing to a home owner 
 * 
 * @author ivy
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS:
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-28-17 by J. Doctolero 
 * last update: 10-28-17 by I. Lim - added constructor & changed attribute datatypes
*/
public class Billing implements Serializable {
    private int billingID;
    protected int blocknum;
    protected int lotnum;
    protected int precedentBilling;
    protected double totalDue;
    protected double totalPaid;
    
    public Billing(){}

    
    public Billing(int billingID, int blocknum, int lotnum, int precedentBilling, double totalDue, double totalPaid){
        this.billingID = billingID;
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.precedentBilling = precedentBilling;
        this.totalDue = totalDue;
        this.totalPaid = totalPaid;
    }
    
    /**
     * returns the billing ID of the Billing object
     *
     * @param nothing
     * @return int
     * @throws nothing
     *
     * @since 10-28-17
     */
    public int getBillingID(){
        return billingID;
    }
    
    /**
     * returns the block number of the Billing object to which the billing is generated for the homeowner
     *
     * @param nothing
     * @return int
     * @throws nothing
     *
     * @since 10-28-17
     */
    public int getBlocknum() {
        return blocknum;
    }
    /**
     * sets the blocknum ID of the billing object
     *
     * @param blocknum
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setBlocknum(int blocknum) {
        this.blocknum = blocknum;
    }

    /**
     * returns the lot number of the Billing object to which the billing is generated for the homeowner
     *
     * @param nothing
     * @return int
     * @throws nothing
     *
     * @since 10-28-17
     */
    public int getLotnum() {
        return lotnum;
    }
    /**
     * sets the lotnum ID of the billing object
     *
     * @param lotnum
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setLotnum(int lotnum) {
        this.lotnum = lotnum;
    }
    
    /**
     * returns the lot number of the Billing object to which the billing is generated for the homeowner
     *
     * @param nothing
     * @return int
     * @throws nothing
     *
     * @since 10-28-17
     */
    public int getPrecedentBilling(){
        return precedentBilling;
    }
    
    /**
     * returns the total due amount of the Billing object to which the billing is generated for the homeowner
     *
     * @param nothing
     * @return double
     * @throws nothing
     *
     * @since 10-28-17
     */
    public double getTotalDue(){
        return totalDue;
    }
    
    /**
     * returns the total paid amount of the Billing object to which the billing is generated for the homeowner
     *
     * @param nothing
     * @return double
     * @throws nothing
     *
     * @since 10-28-17
     */
    public double getTotalPaid(){
        return totalPaid;
    }
    
    /**
     * sets the billing ID of the billing object
     *
     * @param billingID
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setBillingID(int billingID){
        this.billingID = billingID;
    }
    
    /**
     * sets the precedent billing ID of the billing object
     *
     * @param precedentBilling
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setPrecedentBilling(int precedentBilling){
        this.precedentBilling = precedentBilling;
    }
    
    /**
     * sets the total due field of the billing object
     *
     * @param totalDue
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setTotalDue(double totalDue){
        this.totalDue = totalDue;
    }
    
    /**
     * sets the total paid field of the billing object
     *
     * @param totalPaid
     * @return nothing
     * @throws nothing
     *
     * @since 10-28-17
     */
    public void setTotalPaid(double totalPaid){
        this.totalPaid = totalPaid;
    }
    
    
}
