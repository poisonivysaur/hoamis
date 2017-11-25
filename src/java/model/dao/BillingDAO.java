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
import model.User;
import model.Homeowner;
import java.util.ArrayList;
import model.TransactionReference;

/**
 * A <b>BillingDAO</b> data access object contains the different 
 * queries related to the Billing and Collection module to access
 * the tables in the database.
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
    protected static ArrayList<User> users = new ArrayList();
    protected static ArrayList<Homeowner> homeowners = new ArrayList();
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
        
        String sql = "SELECT B.BILLINGID, B.PRECEDENTBILLING, B.TOTALDUE, B.TOTALPAID, B.DATE "
                + " FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM "
                + " JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM "
                + " JOIN USERS U ON U.USERID = HO.USERID WHERE HO.USERID = ?;"; //WHERE USERID = ? AND PASSWD = ?;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userid);
            //pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            Billing bill;
            while(rs.next()){
                int id = rs.getInt(1);
                int prev = rs.getInt(2);
                double due = rs.getDouble(3);
                double paid = rs.getDouble(4);
                bill = new Billing();
                bill.setBillingID(id);
                bill.setPrecedentBilling(prev);
                bill.setTotalDue(due);
                bill.setTotalPaid(paid);
                //int three = rs.getInt(3);
                //int four = rs.getInt(4);
                //int five = rs.getInt(5);
                
                //Billing sampleBill = new model.Billing(one ,two, three, four, five);
                billings.add(bill);
               
            }
            
        }catch(Exception e){
            e.printStackTrace();
            
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return billings;
    }
    
    public static ArrayList<Billing> getTrxRef(int billID){
        ArrayList<TransactionReference> trx = new ArrayList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        
        String sql = "SELECT TR.TRXID, TR.AMOUNT, TR.INTEREST, TR.TOTALAMOUNT, TR.DESCRIPTION, TR.DATE "
                + " FROM BILLING B JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM "
                + " JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM "
                + " JOIN USERS U ON U.USERID = HO.USERID WHERE HO.USERID = ?;"; //WHERE USERID = ? AND PASSWD = ?;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userid);
            //pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            Billing bill;
            while(rs.next()){
                int id = rs.getInt(1);
                int prev = rs.getInt(2);
                double due = rs.getDouble(3);
                double paid = rs.getDouble(4);
                bill = new Billing();
                bill.setBillingID(id);
                bill.setPrecedentBilling(prev);
                bill.setTotalDue(due);
                bill.setTotalPaid(paid);
                //int three = rs.getInt(3);
                //int four = rs.getInt(4);
                //int five = rs.getInt(5);
                
                //Billing sampleBill = new model.Billing(one ,two, three, four, five);
                billings.add(bill);
               
            }
            
        }catch(Exception e){
            e.printStackTrace();
            
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return billings;
    }
    
    
    
    public static void getUserHomeowners(){
        // reset array lists and select from database again
        users = new ArrayList();
        homeowners = new ArrayList();
        if(users.size() ==0 ){
            Connection conn = null;
            PreparedStatement pStmt = null;
            String sql = "SELECT U.USERID, U.FNAME, U.LNAME, U.MNAME, HO.BLOCKNUM, HO.LOTNUM"
                    + " FROM USERS U JOIN HOMEOWNER HO ON HO.USERID = U.USERID";
            try{
                conn = DatabaseUtils.retrieveConnection();
                pStmt = conn.prepareStatement(sql);
                //pStmt.setString(1, userid);
                //pStmt.setString(2, tryLogin.getPasswd());

                ResultSet rs = pStmt.executeQuery();
                
                while(rs.next()){
                    String id = rs.getString(1);
                    String fname = rs.getString(2);
                    String lname = rs.getString(3);
                    String mname = rs.getString(4);
                    int block = rs.getInt(5);
                    int lot = rs.getInt(6);
                    
                    User user = new User();
                    Homeowner ho = new Homeowner();
                    System.out.println(id+"after querying");
                    
                    ho.setBlocknum(block);
                    ho.setLotnum(lot);
                    ho.setUserID(id);
                    homeowners.add(ho);
                    
                    user.setUserID(id);
                    user.setfName(fname);
                    user.setlName(lname);
                    user.setmName(mname);
                    users.add(user);

                }

            }catch(Exception e){
                e.printStackTrace();

            }finally{
                if(conn != null){
                    try{
                        conn.close();
                    }catch(Exception e){}
                }
            }  
        }
    }
    
    public static ArrayList<User> getUsers(){
        return users;
    }
    
    public static ArrayList<Homeowner> getHomeowners(){
        return homeowners;
    }
    
    public static void main(String[] args) {
        System.out.println("Start");
        for(Billing b : BillingDAO.getBillings("yutainoue")){
            System.out.println(b.getBillingID());
        }
        BillingDAO.getUserHomeowners();
        
        for(User b : BillingDAO.getUsers()){
            System.out.println(b.getUserID());
        }
        
        for(Homeowner b : BillingDAO.getHomeowners()){
            System.out.println(b.getUserID());
        }
        System.out.println("End");
    }
}
