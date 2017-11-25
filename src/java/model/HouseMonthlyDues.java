/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author justine
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public MonthlyDues getMdues() {
        return mdues;
    }

    public void setMdues(MonthlyDues mdues) {
        this.mdues = mdues;
    }

    public TransactionReference getTrx() {
        return trx;
    }

    public void setTrx(TransactionReference trx) {
        this.trx = trx;
    }
    
    
}
