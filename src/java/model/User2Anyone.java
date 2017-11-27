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
public class User2Anyone implements Serializable{
    
    private int securityReportID;
    private String complainantID;
    private String otherParty;
   
    
    public User2Anyone(int security, String complainantID, String otherParty){
        this.securityReportID = security;
        this.complainantID = complainantID;
        this.otherParty = otherParty;
    }

    public int getSecurityReportID() {
        return securityReportID;
    }

    public void setSecurityReportID(int securityReportID) {
        this.securityReportID = securityReportID;
    }

    public String getComplainantID() {
        return complainantID;
    }

    public void setComplainantID(String complainantID) {
        this.complainantID = complainantID;
    }

    public String getOtherParty() {
        return otherParty;
    }

    public void setOtherParty(String otherParty) {
        this.otherParty = otherParty;
    }
    
    
}
