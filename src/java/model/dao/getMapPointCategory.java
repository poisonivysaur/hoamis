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
public class getMapPointCategory {
    
    /**
    * Returns a ResultSet of all categories existing in the database.
    * @return rs - ResultSet that contains the category id and name.
    */
    public static ResultSet getCategories(){
        
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);

            Statement ps = con.createStatement();
            rs = ps.executeQuery("SELECT ref_mappointcategory.mappointcategoryID, ref_mappointcategory.mappointcategory FROM hoamis.ref_mappointcategory JOIN mappoint on mappoint.mappointcategoryID = ref_mappointcategory.mappointcategoryID GROUP BY ref_mappointcategory.mappointcategory");

                    
                    }
        catch(Exception e){
        
        }
        
        return rs;
    }
}
