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
public class Policies implements Serializable {
    
    private int policyID;
    private String policydesc;
    private int penaltyID;
    private int supportingdocumentID;
    private String enactmentDate;
    private String stopimplementDate;
    private String enablingBoard;
    
    public Policies(int policyID, String policydesc, int penaltyID, int supportingdocumentID, String enactmentDate, String stopimplementDate, String enablingBoard){
    
        this.policyID = policyID;
        this.policydesc = policydesc;
        this.penaltyID = penaltyID;
        this.supportingdocumentID = supportingdocumentID;
        this.enactmentDate = enactmentDate;
        this.stopimplementDate = stopimplementDate;
        this.enablingBoard = enablingBoard;
        
    }

    public int getPolicyID() {
        return policyID;
    }

    public String getPolicydesc() {
        return policydesc;
    }

    public int getPenaltyID() {
        return penaltyID;
    }

    public int getSupportingdocumentID() {
        return supportingdocumentID;
    }

    public String getEnactmentDate() {
        return enactmentDate;
    }

    public String getStopimplementDate() {
        return stopimplementDate;
    }

    public String getEnablingBoard() {
        return enablingBoard;
    }

    public void setPolicyID(int policyID) {
        this.policyID = policyID;
    }

    public void setPolicydesc(String policydesc) {
        this.policydesc = policydesc;
    }

    public void setPenaltyID(int penaltyID) {
        this.penaltyID = penaltyID;
    }

    public void setSupportingdocumentID(int supportingdocumentID) {
        this.supportingdocumentID = supportingdocumentID;
    }

    public void setEnactmentDate(String enactmentDate) {
        this.enactmentDate = enactmentDate;
    }

    public void setStopimplementDate(String stopimplementDate) {
        this.stopimplementDate = stopimplementDate;
    }

    public void setEnablingBoard(String enablingBoard) {
        this.enablingBoard = enablingBoard;
    }
    
    
    
    
}
