/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Jayvee Gabriel
 */
public class getMapPoints {
    
    /**
     * Returns a ResultSet that contains all the mappoints existing in the database.
     * @return rs - Contains the latitude and longitude, the title and description, 
     * and the categoryID of the map point.
     */
    public static ResultSet getMapPoints(){
        ResultSet rs = null;
        try{
            Connection con = DatabaseUtils.retrieveConnection();

            Statement ps = con.createStatement();
            rs = ps.executeQuery("SELECT mappointID, xAxis, yAxis, title, description, mappointcategoryID FROM mappoint");
                             
        }
        catch(Exception e){
        
        }
        
        return rs;
    }
}
