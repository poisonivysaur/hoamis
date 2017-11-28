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
public class getEstablishments {
    
    /**
     * Returns a ResultSet containing the location of houses and other establishments 
     * filtered by a category. 
     * @param val A string value that holds the id of the map point category to filter 
     * the rows.
     * @return rs - The rows that contains the location of establishments.
     */
    public static ResultSet getEstablishments(String val){
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);

            Statement ps = con.createStatement();
            rs = ps.executeQuery("SELECT mappointID, xAxis, yAxis, title, description FROM ref_mappointcategory rm JOIN mappoint m ON m.mappointcategoryID = rm.mappointcategoryID WHERE m.mappointcategoryID = "+ val);
        }
        catch(Exception e){
        
        }
        
        return rs;
    }
}
