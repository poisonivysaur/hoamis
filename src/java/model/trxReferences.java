/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
/**
 *
 * @author Jennifer
 */
public class trxReferences implements Serializable{
    
    private int trxID;
    private int interest;
    private int amount;
    private int totalamount;
    
    public trxReferences (int trxID, int interest, int amount, int totalamount){
    
        this.trxID = trxID;
        this.interest = interest;
        this.amount = amount;
        this.totalamount = totalamount;
    
    }

    public int getTrxID() {
        return trxID;
    }

    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }
    
    
    
}
