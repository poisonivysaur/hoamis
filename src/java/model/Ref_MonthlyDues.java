/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author justine
 */
public class Ref_MonthlyDues implements Serializable {
    
    protected int mduesID;
    protected int startMonth;
    protected int startYear;
    protected int endMonth;
    protected int endYear;
    protected double amountApproved;
	
	public Ref_MonthlyDues(){}

    public Ref_MonthlyDues(int mduesID, int startMonth, int startYear, int endMonth, int endYear, double amountApproved) {
        this.mduesID = mduesID;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.amountApproved = amountApproved;
    }

    public int getMduesID() {
        return mduesID;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public double getAmountApproved() {
        return amountApproved;
    }
    
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
    
}
