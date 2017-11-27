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
public class Vehicle2User implements Serializable{
    
    private int securityReportID;
    private String complainantUserID;
    private String accusedplatenum;
    
    public Vehicle2User(int securityReportID, String complainantUserID, String accusedplatenum){
        this.securityReportID = securityReportID;
        this.complainantUserID = complainantUserID;
        this.accusedplatenum = accusedplatenum;
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

    public String getAccusedplatenum() {
        return accusedplatenum;
    }

    public void setAccusedplatenum(String accusedplatenum) {
        this.accusedplatenum = accusedplatenum;
    }
    
    
}
