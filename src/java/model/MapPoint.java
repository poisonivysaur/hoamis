/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Jayvee Gabriel
 */
public class MapPoint {
    
    
    protected int mappointID;
    protected double xAxis;
    protected double yAxis;
    protected String title;
    protected String description;
    protected String userID;
    protected String createDate;
    protected String mappointcategory;

    /**
     * returns the appoint id of the transaction
     * 
     * @param nothing
     * @return int containing the appoint id of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public int getMappointID() {
        return mappointID;
    }

    /**
     * sets the appoint id of the transaction
     * 
     * @param mappointID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setMappointID(int mappointID) {
        this.mappointID = mappointID;
    }

    /**
     * returns the x axis of the transaction
     * 
     * @param nothing
     * @return Double containing the x-axis of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */ 
    public double getxAxis() {
        return xAxis;
    }

    /**
     * sets the x axis of the transaction
     * 
     * @param xAxis
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * returns the y axis of the transaction
     * 
     * @param nothing
     * @return Double containing the y-axis of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */ 
    public double getyAxis() {
        return yAxis;
    }

    /**
     * sets the y axis of the transaction
     * 
     * @param yAxis
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * returns the title of the transaction
     * 
     * @param nothing
     * @return String containing the title of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the transaction
     * 
     * @param title
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * returns the description of the transaction
     * 
     * @param nothing
     * @return String containing the description of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the transaction
     * 
     * @param decription
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the user if of the user
     * 
     * @param nothing
     * @return String containing the user id of the user
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public String getUserID() {
        return userID;
    }

    /**
     * sets the userId of the transaction
     * 
     * @param userId
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */       
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * returns the create date of the transaction.
     * 
     * @param nothing
     * @return String containing the date of the transaction.
     * @throws nothing
     * 
     * @since 10-28-17
     */            
    public String getCreateDate() {
        return createDate;
    }

    /**
     * sets the create date of the transaction
     * 
     * @param createDate
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * returns the appoint category of the transaction.
     * 
     * @param nothing
     * @return String containing the appoint category of the transaction.
     * @throws nothing
     * 
     * @since 10-28-17
     */      
    public String getMappointcategory() {
        return mappointcategory;
    }

    /**
     * sets the appoint category of the transaction
     * 
     * @param mappointCategory
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public void setMappointcategory(String mappointcategory) {
        this.mappointcategory = mappointcategory;
    }
    
    
}
