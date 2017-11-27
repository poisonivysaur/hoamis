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
public class PhotoEvidences implements Serializable {
    
    private int securityReportID;
    private int documentID;
    
    public PhotoEvidences (int securityReportID, int documentID){
    
        this.securityReportID = securityReportID;
        this.documentID = documentID;
        
    }    

    public int getSecurityReportID() {
        return securityReportID;
    }

    public void setSecurityReportID(int securityReportID) {
        this.securityReportID = securityReportID;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }
    
    
    
}
