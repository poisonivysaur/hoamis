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
public class Document {
    
    protected int documentID;
    protected String description;
    protected String documentLocation;
    protected int folderID;
    protected int create_userID;

    /**
     * returns the documentID in integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getDocumentID() {
        return documentID;
    }

    /**
     * sets the documentID of the object
     * 
     * @param documentID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    /**
     * returns the description in String value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the object
     * 
     * @param description
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the documentLocation in String value.
     * 
     * @param nothing
     * @return String
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public String getDocumentLocation() {
        return documentLocation;
    }

    /**
     * sets the description of the object
     * 
     * @param documentLocation
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setDocumentLocation(String documentLocation) {
        this.documentLocation = documentLocation;
    }

    /**
     * returns the folderID in Integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getFolderID() {
        return folderID;
    }

    /**
     * sets the folderID of the object
     * 
     * @param folderID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setFolderID(int folderID) {
        this.folderID = folderID;
    }

    /**
     * returns the create_userID in Integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int getCreate_userID() {
        return create_userID;
    }

    /**
     * sets the create_userID of the object
     * 
     * @param create_userID
     * @return nothing
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public void setCreate_userID(int create_userID) {
        this.create_userID = create_userID;
    }
    
    /**
     * returns a ResultSet which contains All the Documents from the database.
     * 
     * @param nothing
     * @return ResultSet
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public static ResultSet sql_getAllDocuments(){
        ResultSet fakeRS = null;
        String sqlStatement1 = "SELECT * FROM documents";
        
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
     * returns the documentID from the database in Integer value.
     * 
     * @param nothing
     * @return int
     * @throws nothing
     * 
     * @since 11-07-17
     */
    public int sql_getDocumentID(String docuDescription){
        
        int docuCnt = 0;
        String sqlStatement1 = "SELECT documentID FROM documents WHERE description = ?";
        
        try{
            Connection conn = DatabaseUtils.retrieveConnection();
        PreparedStatement ps = conn.prepareStatement(sqlStatement1);
        
        ps.setString(1, docuDescription);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return Integer.parseInt(rs.getString("documentID"));
        }
        
        }catch(Exception E){
            E.printStackTrace();
        }
        return docuCnt;
    }
    
    
    
}
