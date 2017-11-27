/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Jennifer
 */
public class SecurityViolations implements Serializable {
    /**public static int count = 1;**/
    private int securityReportID;
    private Date reportDate;
    private int incidentTypeID;
    private String complaint;
    private int status;
    private String resolution;
    private int trxID;
    private String boardmemberID;
    private String securityID;
    private int violatedpolicyID;
    private int mappointID;
    
    
    public SecurityViolations (int securityReportID, Date reportDate, int incidentTypeID, String complaint, int status, String resolution, String securityID, int mappointID){
       /**this.securityReportID = (++count);**/
       this.securityReportID = securityReportID;
       this.reportDate = reportDate;
       this.incidentTypeID = incidentTypeID;
       this.complaint = complaint;
       this.status = 1;
       this.securityID = securityID;
       this.trxID = 1;
       this.mappointID = mappointID;
    }
    
    
    public SecurityViolations (int securityReportID, Date reportDate, int incidentTypeID, String complaint, int status, String resolution, int trxID, String boardmemberID, String securityID, int violatedpolicyID, int mappointID){
     
       this.securityReportID = securityReportID;
       this.reportDate = reportDate;
       this.incidentTypeID = incidentTypeID;
       this.complaint = complaint;
       this.status = status;
       this.resolution = resolution;
       this.trxID = trxID;
       this.securityID = securityID;
       this.boardmemberID =  boardmemberID;
       this.violatedpolicyID = violatedpolicyID;
       this.mappointID = mappointID;
    
    }

    public SecurityViolations(int i, Date date, int incidentTypeID, String complaint, int a, String resolution, int o, String securityID, int violatedpolicyID, int mappointID) {
       this.securityReportID = i;
       this.reportDate = date;
       this.incidentTypeID = incidentTypeID;
       this.complaint = complaint;
       this.status = a;
       this.resolution = resolution;
       this.trxID = o;
       this.securityID = securityID;
       this.violatedpolicyID = violatedpolicyID;
       this.mappointID = mappointID;
    }

    public int getSecurityReportID() {
        return securityReportID;
    }

    public String getReportDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return ft.format(reportDate);
    }

    public int getIncidentTypeID() {
        return incidentTypeID;
    }

    public String getComplaint() {
        return complaint;
    }

    public int getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public int getTrxID() {
        return trxID;
    }

    public String getBoardmemberID() {
        return boardmemberID;
    }

    public String getSecurityID() {
        return securityID;
    }

    public int getViolatedpolicyID() {
        return violatedpolicyID;
    }

    public int getMappointID() {
        return mappointID;
    }
    
    
}
