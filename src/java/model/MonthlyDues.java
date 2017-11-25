package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anne Charlene Cipres
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

    public int getMdID() {
        return mdID;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setMdID(int mdID) {
        this.mdID = mdID;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMdues(Ref_MonthlyDues mdues) {
        this.mdues = mdues;
    }

    public double getAmount() {
        return amount;
    }

    public Ref_MonthlyDues getMdues() {
        return mdues;
    }
    
}
