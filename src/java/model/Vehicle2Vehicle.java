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
public class Vehicle2Vehicle {
    private int securityReportID;
    private String complainantplatenum;
    private String accusedplatenum;
    
    public Vehicle2Vehicle(int securityReportID, String complainantplatenum, String accusedplatenum){
    
        this.accusedplatenum = accusedplatenum;
        this.complainantplatenum = complainantplatenum;
        this.securityReportID = securityReportID;
    
    }

    public int getSecurityReportID() {
        return securityReportID;
    }

    public void setSecurityReportID(int securityReportID) {
        this.securityReportID = securityReportID;
    }

    public String getComplainantplatenum() {
        return complainantplatenum;
    }

    public void setComplainantplatenum(String complainantplatenum) {
        this.complainantplatenum = complainantplatenum;
    }

    public String getAccusedplatenum() {
        return accusedplatenum;
    }

    public void setAccusedplatenum(String accusedplatenum) {
        this.accusedplatenum = accusedplatenum;
    }
    
    
    
}
