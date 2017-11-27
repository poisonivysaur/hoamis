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
public class trxList implements Serializable {
    
    
    private int journalID;
    private int trxID;
    private int amountPaid;
    
    public trxList(int journalID, int trxID, int amountPaid){
    
        this.journalID = journalID;
        this.trxID = trxID;
        this.amountPaid = amountPaid;
        
    }

    public int getJournalID() {
        return journalID;
    }

    public void setJournalID(int journalID) {
        this.journalID = journalID;
    }

    public int getTrxID() {
        return trxID;
    }

    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    
    
}
