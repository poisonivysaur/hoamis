/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Billing;
import java.util.ArrayList;

/**
 * A <b>BillingDAO</b> data access object contains the different queries to access
 * the billing table in the database
 * 
 * @author ivy lim
 * @version 1.001
 * @since 2017-10-28
 */

/* 
 * COMMENTS:
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-28-17 by J. Doctolero 
 * last update: 11-24-17 by I. Lim - added queries
*/
public class BillingDAO {
    public static ArrayList<Billing> getBillings(){
        ArrayList<Billing> billings = new ArrayList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        model.User loginUser = null;
        String sql = "SELECT BILLINGID, BLOCKNUM, LOTNUM, PRECEDENTBILLING, TOTALDUE, TOTALPAID FROM BILLING"; //WHERE USERID = ? AND PASSWD = ?;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            //pStmt.setString(1, tryLogin.getUserID());
            //pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int block = rs.getInt(2);
                int lot = rs.getInt(3);
                int prevBill = rs.getInt(4);
                int due = rs.getInt(5);
                int paid = rs.getInt(6);
                Billing sampleBill = new model.Billing(id ,block, lot, prevBill, due, paid);
                billings.add(sampleBill);
                /*
                loginUser = new model.User();
                loginUser.setUserID(rs.getString(1));
                loginUser.setPasswd(rs.getString(2));
                loginUser.setUsertype(rs.getInt(3));
                loginUser.setlName(rs.getString(4));
                loginUser.setfName(rs.getString(5));
                loginUser.setmName(rs.getString(6));
*/
            }
            
        }catch(Exception e){
            e.printStackTrace();
            loginUser = null;
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return billings;
    }
    
    public static ArrayList<Billing> getBillings(String userid){
        ArrayList<Billing> billings = new ArrayList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        model.User loginUser = null;
        String sql = "SELECT B.BILLINGID, B.PRECEDENTBILLING "
                + " FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM "
                + " JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM "
                + " JOIN USERS U ON U.USERID = HO.USERID WHERE HO.USERID = ?;"; //WHERE USERID = ? AND PASSWD = ?;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userid);
            //pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            Billing sample;
            while(rs.next()){
                int one = rs.getInt(1);
                int two = rs.getInt(2);
                sample = new Billing();
                sample.setBillingID(one);
                sample.setPrecedentBilling(two);
                //int three = rs.getInt(3);
                //int four = rs.getInt(4);
                //int five = rs.getInt(5);
                
                //Billing sampleBill = new model.Billing(one ,two, three, four, five);
                billings.add(sample);
                /*
                loginUser = new model.User();
                loginUser.setUserID(rs.getString(1));
                loginUser.setPasswd(rs.getString(2));
                loginUser.setUsertype(rs.getInt(3));
                loginUser.setlName(rs.getString(4));
                loginUser.setfName(rs.getString(5));
                loginUser.setmName(rs.getString(6));
*/
            }
            
        }catch(Exception e){
            e.printStackTrace();
            loginUser = null;
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return billings;
    }
    
    public static void main(String[] args) {
        System.out.println("Start");
        for(Billing b : BillingDAO.getBillings("yutainoue")){
            System.out.println(b.getBillingID());
        }
        System.out.println("End");
    }
}
