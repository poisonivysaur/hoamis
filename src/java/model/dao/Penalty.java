package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dao.DatabaseUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel
 */
public class Penalty {
    
    protected int penaltyID;
    protected int penaltyLevel;
    protected String penaltyDescription;
    protected double penaltyFee;
    protected String penaltyAction;
    protected int enablingdocumentID;
    
    /**
     * returns the penaltyID in integer value.
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
     * returns the penaltyLevel in integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getPenaltyLevel() {
        return penaltyLevel;
    }

    /**
     * sets the penaltyLevel of the object
     * 
     * @param penaltyLevel
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPenaltyLevel(int penaltyLevel) {
        this.penaltyLevel = penaltyLevel;
    }

    /**
     * returns the penaltyDescription in String value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getPenaltyDescription() {
        return penaltyDescription;
    }

    /**
     * sets the penaltyDescription of the object
     * 
     * @param penaltyDescription
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPenaltyDescription(String penaltyDescription) {
        this.penaltyDescription = penaltyDescription;
    }

    /**
     * returns the getPenaltyFee in double value.
     * 
     * @param nothing
     * @return double
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public double getPenaltyFee() {
        return penaltyFee;
    }

    /**
     * sets the penaltyFee of the object
     * 
     * @param penaltyFee
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPenaltyFee(double penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    /**
     * returns the penaltyAction in double value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getPenaltyAction() {
        return penaltyAction;
    }

    /**
     * sets the penaltyAction of the object
     * 
     * @param penaltyAction
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPenaltyAction(String penaltyAction) {
        this.penaltyAction = penaltyAction;
    }

    /**
     * returns the enablingdocumentID in double value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getEnablingdocumentID() {
        return enablingdocumentID;
    }

    /**
     * sets the enablingdocumentID of the object
     * 
     * @param enablingdocumentID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setEnablingdocumentID(int enablingdocumentID) {
        this.enablingdocumentID = enablingdocumentID;
    }
    
    /**
     * returns a ResultSet which contains all the penalties from the database.
     * 
     * @param nothing
     * @return ResultSet
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public static ResultSet sql_getAllPenalties(){
        ResultSet fakeRS = null;
        String sqlStatement1 = "SELECT * FROM penalties";
        
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
     * returns the penaltyID from the database in int value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int sql_getPenaltyID(String penaltyDescription){
        
        int penaltyCnt = 0;
        String sqlStatement1 = "SELECT penaltyID FROM penalties WHERE penaltydescription = ?";
        
        try{
            Connection conn = DatabaseUtils.retrieveConnection();
        PreparedStatement ps = conn.prepareStatement(sqlStatement1);
        
        ps.setString(1, penaltyDescription);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return Integer.parseInt(rs.getString("penaltyID"));
        }
        
        }catch(Exception E){
            E.printStackTrace();
        }
        return penaltyCnt;
    }
    
    
    
}
