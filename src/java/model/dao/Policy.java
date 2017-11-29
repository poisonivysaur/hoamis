package model.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel
 */
import java.sql.*;
import model.dao.DatabaseUtils;

public class Policy {
    
    protected int policyID;
    protected String policydesc;
    protected int penaltyID;
    protected int supportingdocumentID;
    protected java.sql.Date enactmentDate;
    protected java.sql.Date stopimplementDate;
    protected String enablingBoard;

    /**
     * returns the policyID in integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getPolicyID() {
        return policyID;
    }

    /**
     * sets the policyID of the object
     * 
     * @param policyID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPolicyID(int policyID) {
        this.policyID = policyID;
    }

    /**
     * returns the policydesc in String value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getPolicydesc() {
        return policydesc;
    }

    /**
     * sets the policydesc of the object
     * 
     * @param policydesc
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPolicydesc(String policydesc) {
        this.policydesc = policydesc;
    }

    /**
     * returns the penaltyID in int value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getPenaltyID() {
        return penaltyID;
    }

    /**
     * sets the penaltyID of the object
     * 
     * @param penaltyID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPenaltyID(int penaltyID) {
        this.penaltyID = penaltyID;
    }

    /**
     * returns the supportingdocumentID in int value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getSupportingdocumentID() {
        return supportingdocumentID;
    }

    /**
     * sets the supportingdocumentID of the object
     * 
     * @param supportingdocumentID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setSupportingdocumentID(int supportingdocumentID) {
        this.supportingdocumentID = supportingdocumentID;
    }

    /**
     * returns the enactmentDate in Date value.
     * 
     * @param nothing
     * @return Date
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public Date getEnactmentDate() {
        return enactmentDate;
    }

    /**
     * sets the enactmentDate of the object
     * 
     * @param enactmentDate
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setEnactmentDate(Date enactmentDate) {
        this.enactmentDate = enactmentDate;
    }

    /**
     * returns the stopimplementDate in Date value.
     * 
     * @param nothing
     * @return Date
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public Date getStopimplementDate() {
        return stopimplementDate;
    }

    /**
     * sets the stopimplementDate of the object
     * 
     * @param stopimplementDate
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setStopimplementDate(Date stopimplementDate) {
        this.stopimplementDate = stopimplementDate;
    }

    /**
     * returns the enablingBoard in String value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getEnablingBoard() {
        return enablingBoard;
    }

    /**
     * sets the enablingBoard of the object
     * 
     * @param enablingBoard
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setEnablingBoard(String enablingBoard) {
        this.enablingBoard = enablingBoard;
    }
    
    /**
     * returns a ResultSet that contains all the Policies in the database.
     * 
     * @param nothing
     * @return ResultSet
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public static ResultSet sql_getAllPolicies(){
        ResultSet fakeRS = null;
        String sqlStatement1 = "SELECT * FROM POLICIES WHERE stopimplementDate is NULL";
        
        try{
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement(sqlStatement1); 

            ResultSet rs = st.executeQuery();

            return rs;
        }catch(Exception E){
            E.printStackTrace();
        }
        return fakeRS;
    }
    
    /**
     * returns a ResultSet that contains all the Retired Policies in the database.
     * 
     * @param nothing
     * @return ResultSet
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public static ResultSet sql_getAllRetiredPolicies(){
        ResultSet fakeRS = null;
        String sqlStatement1 = "SELECT * FROM POLICIES WHERE stopimplementDate IS NOT NULL";
        
        try{        
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement(sqlStatement1); 

            ResultSet rs = st.executeQuery();

            return rs;
        }catch(Exception E){
            E.printStackTrace();
        }
        return fakeRS;
    }
    
    /**
     * returns a ResultSet that contains a specific Policy in the database.
     * 
     * @param policyID
     * @return ResultSet
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public static ResultSet sql_getPolicy(int policyID){
        ResultSet fakeRS = null;
        String sqlStatement1 = "SELECT po.policyID, po.policydesc, pe.penaltyLevel, pe.penaltyDescription, pe.penaltyfee, pe.penaltyaction FROM POLICIES po JOIN PENALTIES pe ON po.penaltyID = pe.penaltyID WHERE policyID = ?";
        
        try{        
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement st = con.prepareStatement(sqlStatement1); 
            
            st.setInt(1, policyID);
                
            ResultSet rs = st.executeQuery();

            return rs;
            
        }catch(Exception E){
            E.printStackTrace();
        }
        return fakeRS;
    }
    
}
