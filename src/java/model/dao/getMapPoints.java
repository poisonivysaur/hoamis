/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);

            Statement ps = con.createStatement();
            rs = ps.executeQuery("SELECT mappointID, xAxis, yAxis, title, description, mappointcategoryID FROM mappoint");
                             
        }
        catch(Exception e){
        
        }
        
        return rs;
    }
}
