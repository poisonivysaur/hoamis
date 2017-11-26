/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * HouseMonthlyDues Object
 * A <b>HouseMonthlyDues</b> object contains the property and monthly dues assigned to it.
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-28
 */
public class HouseMonthlyDues {
    
    protected Property property;
    protected MonthlyDues mdues;
    protected TransactionReference trx;

    public HouseMonthlyDues(Property property, MonthlyDues mdues, TransactionReference trx) {
        this.property = property;
        this.mdues = mdues;
        this.trx = trx;
    }

    /**
     * returns the property of the transaction
     * 
     * @param nothing
     * @return property
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public Property getProperty() {
        return property;
    }
    /**
     * sets the property of the object
     * 
     * @param property
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * returns the monthly due of the transaction
     * 
     * @param nothing
     * @return monthly due
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public MonthlyDues getMdues() {
        return mdues;
    }

    /**
     * sets the monthly due of the transaction
     * 
     * @param mdues
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setMdues(MonthlyDues mdues) {
        this.mdues = mdues;
    }

    /**
     * returns the transaction reference of the transaction
     * 
     * @param nothing
     * @return monthly due
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public TransactionReference getTrx() {
        return trx;
    }

    /**
     * sets the transaction reference of the transaction
     * 
     * @param trx
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public void setTrx(TransactionReference trx) {
        this.trx = trx;
    }
    
    
}
