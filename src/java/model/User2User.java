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
public class User2User implements Serializable{
    
    private int securityReportID;
    private String complainantUserID;
    private String accusedUserID;
    
    public User2User(int securityReport, String complainantuserID, String accusedUserID){
        
        this.securityReportID = securityReport;
        this.complainantUserID = complainantuserID;
        this.accusedUserID = accusedUserID;
     
    }

    public int getSecurityReportID() {
        return securityReportID;
    }

    public void setSecurityReportID(int securityReportID) {
        this.securityReportID = securityReportID;
    }

    public String getComplainantUserID() {
        return complainantUserID;
    }

    public void setComplainantUserID(String complainantUserID) {
        this.complainantUserID = complainantUserID;
    }

    public String getAccusedUserID() {
        return accusedUserID;
    }

    public void setAccusedUserID(String accusedUserID) {
        this.accusedUserID = accusedUserID;
    }
    
}
