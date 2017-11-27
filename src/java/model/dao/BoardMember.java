package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miguel Merle
 * @version 1.00
 * @since 2017-11-20
 */

import java.sql.*;
import java.util.Calendar;

public class BoardMember {

    protected int userID;
    protected int positionID;
    protected java.sql.Date effectiveDate;
    protected java.sql.Date endDate;
    protected int statusID;
    protected int electionID;
    
    
    /**
     * returns the userID in integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets the userID of the object
     * 
     * @param userID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * returns the positionID in integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getPositionID() {
        return positionID;
    }

    /**
     * sets the positionID of the object
     * 
     * @param positionID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    /**
     * returns the effectiveDate in Date value.
     * 
     * @param nothing
     * @return Date
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * sets the effectiveDate of the object
     * 
     * @param effectiveDate
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * returns the endDate in Date value.
     * 
     * @param nothing
     * @return Date
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * sets the endDate of the object
     * 
     * @param endDate
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * returns the statusID in Integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * sets the statusID of the object
     * 
     * @param statusID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * returns the electionID in Integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getElectionID() {
        return electionID;
    }

    /**
     * sets the electionID of the object
     * 
     * @param electionID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setElectionID(int electionID) {
        this.electionID = electionID;
    }
    
    
    
    
    
    
    
}
