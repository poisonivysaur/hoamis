/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Ref_MonthlyDues Object
 * A <b>Ref_MonthlyDues</b> object contains the monthly dues allocated for all the homeowners within a
 * specified time period.
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-28
 */
public class Ref_MonthlyDues {
    
    protected int mduesID;
    protected int startMonth;
    protected int startYear;
    protected int endMonth;
    protected int endYear;
    protected double amountApproved;

    public Ref_MonthlyDues(int mduesID, int startMonth, int startYear, int endMonth, int endYear, double amountApproved) {
        this.mduesID = mduesID;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.amountApproved = amountApproved;
    }
    /**
     * returns the monthly due id
     * 
     * @param nothing
     * @return int containing the monthly due id
     * @throws nothing
     * 
     * @since 11-25-17
     */
    public int getMduesID() {
        return mduesID;
    }

    /**
     * returns the start month of the monthly due
     * 
     * @param nothing
     * @return int containing the start month of the monthly due
     * @throws nothing
     * 
     * @since 11-25-17
     */    
    public int getStartMonth() {
        return startMonth;
    }

    /**
     * returns the start year of the monthly due
     * 
     * @param nothing
     * @return int containing the start year of the monthly due
     * @throws nothing
     * 
     * @since 11-25-17
     */      
    public int getStartYear() {
        return startYear;
    }

    /**
     * returns the end month of the monthly due
     * 
     * @param nothing
     * @return int containing the end month of the monthly due
     * @throws nothing
     * 
     * @since 11-25-17
     */      
    public int getEndMonth() {
        return endMonth;
    }

    /**
     * returns the end year of the monthly due
     * 
     * @param nothing
     * @return int containing the end year of the monthly due
     * @throws nothing
     * 
     * @since 11-25-17
     */     
    public int getEndYear() {
        return endYear;
    }

    /**
     * returns the amount approved of the monthly due
     * 
     * @param nothing
     * @return Double containing the amount approved of the monthly due
     * @throws nothing
     * 
     * @since 11-25-17
     */     
    public double getAmountApproved() {
        return amountApproved;
    }
    
    /**
     * Gets the number of months between the startMonth and endMonth. It is considered one (1) month
     * if both are equal.
     * 
     * @return int with number of months between startMonth and endMonth
     * @since 11-25-17
     */
   
    //gets number of months based from start and end values of month and year
    public int getNumberOfMonths(){
        //numOfMonths is initialized as 1 because same month is counted as 1 month
        int numOfMonths = 1;

        if(endYear > startYear){
            //counted as one year if endYear and endMonth is greater than or equal (for month) than start values
            if(endMonth >= startMonth)
                numOfMonths += 12;
        }
        //will still add the difference of the endMonth and startMonth regardless of outcome
        numOfMonths += (endMonth - startMonth);
        
        return numOfMonths;
    }
    
    /**
     * Gets the word form of startMonth (i.e. 1 = January)
     * 
     * @return String with the word equivalent of startMonth
     * @since 11-25-17
     */
    
    public String getStartMonthWord(){
        switch(startMonth){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }
    
    /**
     * Gets the word form of endMonth (i.e. 1 = January)
     * 
     * @return String with the word equivalent of endMonth
     * @since 11-25-17
     */
    
    public String getEndMonthWord(){
        switch(endMonth){
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }
}

    
