/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.*;

/**
 *
 * @author Jayvee Gabriel
 */
public class getHouses {
    
    /**
     * Returns a ResultSet that contains the locations of houses and the name of
     * its owner.
     * @return rs - Contains the latitude and longitude of houses and the name 
     * of its owner.
     */
    public static ResultSet getHouses(){
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);

            Statement ps = con.createStatement();
            rs = ps.executeQuery("SELECT m.mappointID, m.xAxis, m.yAxis, u.fname, u.lname FROM mappoint m join ref_mappointcategory rm on m.mappointcategoryID = rm.mappointcategoryID JOIN ref_properties rp on rp.mappointID = m.mappointID join homeowner h on h.blocknum = rp.blocknum AND h.lotnum = rp.lotnum JOIN users u ON u.userID = h.userid WHERE rm.mappointcategoryID = 2");
        }
        catch(Exception e){
        
        }
        
        return rs;
    }
}
