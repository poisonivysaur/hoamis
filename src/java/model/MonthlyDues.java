package model;

/**
 * MonthlyDues Object
 * A <b>MonthlyDues</b> object contains the monthly dues allocated to all the homeowners per month
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-28
 */
public class MonthlyDues {
    
    protected int mdID;
    protected int month;
    protected int year;
    protected double amount;
    protected Ref_MonthlyDues mdues;

    public MonthlyDues(int mdID, int month, int year, double amount, Ref_MonthlyDues mdues) {
        this.mdID = mdID;
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.mdues = mdues;
    }

    /**
     * returns the monthly due id of the transaction
     * 
     * @param nothing
     * @return int containing the monthly due id of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public int getMdID() {
        return mdID;
    }

    /**
     * returns the month of the transaction
     * 
     * @param nothing
     * @return int containing the month of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public int getMonth() {
        return month;
    }

    /**
     * returns the year of the transaction
     * 
     * @param nothing
     * @return int containing the year of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public int getYear() {
        return year;
    }

    /**
     * sets the monthly due id of the transaction
     * 
     * @param mdID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setMdID(int mdID) {
        this.mdID = mdID;
    }

    /**
     * sets the month of the transaction
     * 
     * @param month
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */       
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * sets the year of the transaction
     * 
     * @param year
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */       
    public void setYear(int year) {
        this.year = year;
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
    public void setAmount(double amount) {
        this.amount = amount;
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
    public void setMdues(Ref_MonthlyDues mdues) {
        this.mdues = mdues;
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
     * returns the monthly due of the transaction
     * 
     * @param nothing
     * @return monthly due of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */      
    public Ref_MonthlyDues getMdues() {
        return mdues;
    }
    
}
