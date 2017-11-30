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
import java.util.Date;
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
    protected static ArrayList<String> datesPaid = new ArrayList<>();
    protected static boolean isBillGenerated = false;   // not used
    protected static String message = "";
    
    /**
     * returns the list of all Billing records in the database
     *
     * @param nothing
     * @return ArrayList<Billing>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<Billing> getBillings(){
        ArrayList<Billing> billings = new ArrayList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        model.User loginUser = null;
        String sql = "SELECT BILLINGID, BLOCKNUM, LOTNUM, PRECEDENTBILLING, TOTALDUE, TOTALPAID, DATE FROM BILLING"; //WHERE USERID = ? AND PASSWD = ?;";
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
                String date = rs.getString(7);
                Billing sampleBill = new model.Billing(id ,block, lot, prevBill, due, paid, date);
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
    
    /**
     * returns the list of all Billing records in the database given a user ID
     *
     * @param userid
     * @return ArrayList<Billing>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<Billing> getBillings(String userid){
        ArrayList<Billing> billings = new ArrayList();
        Connection conn = null;
        PreparedStatement pStmt = null;
        
        String sql = "SELECT B.BILLINGID, B.PRECEDENTBILLING, B.TOTALDUE, B.TOTALPAID, B.DATE "
                + " FROM BILLING B LEFT JOIN REF_PROPERTIES RP ON B.BLOCKNUM = RP.BLOCKNUM AND B.LOTNUM = RP.LOTNUM "
                + " LEFT JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND HO.LOTNUM = RP.LOTNUM "
                + " LEFT JOIN USERS U ON U.USERID = HO.USERID WHERE HO.USERID = ?"
                + " ORDER BY B.BILLINGID DESC;"; //WHERE USERID = ? AND PASSWD = ?;";
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
                String date = rs.getString(5);
                bill = new Billing();
                bill.setBillingID(id);
                bill.setPrecedentBilling(prev);
                bill.setTotalDue(due);
                bill.setTotalPaid(paid);
                bill.setDateIssued(date);
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
    
    /**
     * returns the list of all transactions included in a given billing statement
     *
     * @param billID
     * @return ArrayList<TransactionReference>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<TransactionReference> getTrxRef(int billID){
        ArrayList<TransactionReference> transactions = new ArrayList();
        datesPaid = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pStmt = null;
        String sql = "SELECT TR.TRXID, TR.AMOUNT, TR.INTEREST, TR.TOTALAMOUNT, TR.DESCRIPTION, TR.DATECREATED,TL.journalID, TJ.trxDate" 
                + " FROM (SELECT trxReferences.TRXID, AMOUNT, INTEREST, TOTALAMOUNT, DESCRIPTION, DATECREATED "
                + " FROM TRXREFERENCES JOIN BILLINGDETAILS BD ON BD.TRXID = trxReferences.TRXID "
                + " WHERE BD.BILLINGID = ?) TR"
                + " LEFT JOIN TRXLIST TL ON TL.TRXID = TR.TRXID"
                + " LEFT JOIN TRANSACTION_JOURNAL TJ ON TJ.JournalID = TL.journalID"
                + " ORDER BY TR.TRXID DESC"; 
        try{
            conn = DatabaseUtils.retrieveConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, billID);
            //pStmt.setString(2, tryLogin.getPasswd());
            
            ResultSet rs = pStmt.executeQuery();
            TransactionReference trx;
            while(rs.next()){
                int id = rs.getInt(1);
                double amount = rs.getDouble(2);
                double interest = rs.getDouble(3);
                double total = rs.getDouble(4);
                String desc = rs.getString(5);
                String date = rs.getString(6);
                String datePaid = rs.getString(8);
                datePaid = (datePaid == null) ? "--" : datePaid;
                datesPaid.add(datePaid); // adds the dates recorded in transaction journal table if any
                //System.out.println("DATE FROM DB: "+date);
                trx = new TransactionReference(id, amount, interest, total, desc, date);
                transactions.add(trx);
               
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
        return transactions;
    }
    
    /**
     * returns the list of dates for all transactions that were already paid for
     *
     * @param nothing
     * @return ArrayList<String>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<String> getDatesPaid(){
        return datesPaid;
    }
    
    /**
     * sets the list of all Homeowners and Users
     *
     * @param nothing
     * @return void
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static void getUserHomeowners(){
        // reset array lists and select from database again
        users = new ArrayList();
        homeowners = new ArrayList();
        if(users.size() ==0 ){
            Connection conn = null;
            PreparedStatement pStmt = null;
            String sql = "SELECT U.USERID, U.FNAME, U.LNAME, U.MNAME, HO.BLOCKNUM, HO.LOTNUM"
                    + " FROM USERS U JOIN HOMEOWNER HO ON HO.USERID = U.USERID"
                    + " WHERE U.STATUS = 'active';";
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
    
    /**
     * returns the list of all Users
     *
     * @param nothing
     * @return ArrayList<User>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<User> getUsers(){
        return users;
    }
    
    /**
     * returns the list of all Homeowners
     *
     * @param billID
     * @return ArrayList<TransactionReference>
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static ArrayList<Homeowner> getHomeowners(){
        return homeowners;
    }
    
    /**
     * generates billing statements for all homeowners
     *
     * @param nothing
     * @return boolean
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static boolean generateBillingForAll(){
        boolean isSuccess = false;
        
        // loop around all active homeowners and get their unpaid fees & dues
        for(int i = 0; i < users.size(); i++){
            double totaldue = MonthlyDuesDAO.getUnpaidFees(users.get(i).getUserID());
            //totaldue += MonthlyDuesDAO.getCurrentHomeownerMonthlyDues();
            double totalpaid = 0;
            int blocknum = homeowners.get(i).getBlocknum();
            int lotnum = homeowners.get(i).getLotnum();
            int precedentBillID = 0;
            
            Connection conn = null;
            PreparedStatement pStmt = null;
            
            try{
                conn = DatabaseUtils.retrieveConnection();
                
                // Check first if there was already a billing issued this month
                String sql = "SELECT B.BILLINGID, B.DATE FROM BILLING B WHERE BLOCKNUM = ? AND LOTNUM = ? AND MONTH(B.DATE) = MONTH(DATE(NOW()));";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, blocknum);
                pStmt.setInt(2, lotnum);
                ResultSet billsGenerated = pStmt.executeQuery();
                if(!billsGenerated.next()){ // if no bill has been generated yet for the month
                    isBillGenerated = true;
                    System.out.println("NO BILLINGS GENERATED YET");
                    // gets the precedent billing of a homeowner
                    sql = "SELECT B.BILLINGID, B.DATE FROM BILLING B WHERE BLOCKNUM = ? AND LOTNUM = ? ORDER BY B.DATE DESC LIMIT 1;"; 

                    pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, blocknum);
                    pStmt.setInt(2, lotnum);
                    //pStmt.setString(2, tryLogin.getPasswd());

                    ResultSet rs = pStmt.executeQuery();
                    while(rs.next()){
                        precedentBillID = rs.getInt(1); // GETS THE LAST BILLING OF THE HOMEOWNER
                        System.out.println("preceding!!!");
                    }



                    // if total due is 0, then no need to insert into Billing table
                    if(totaldue > 0){


                    // inserts a new billing for the homeowner
                    sql = "INSERT INTO BILLING(BLOCKNUM, LOTNUM, PRECEDENTBILLING, TOTALDUE, TOTALPAID, DATE)"
                            + " VALUES(?, ?, ?, ?, ?, DATE(NOW()))";

                    pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, blocknum);
                    pStmt.setInt(2, lotnum);
                    pStmt.setInt(3, precedentBillID);
                    pStmt.setDouble(4, totaldue);
                    pStmt.setDouble(5, totalpaid);

                    int inserted = pStmt.executeUpdate();
                    if(inserted != 0){
                        System.out.println("Successfully inserted into Billings!");
                        String maxBillID = "SELECT BILLINGID FROM BILLING ORDER BY BILLINGID DESC LIMIT 1;";
                        pStmt = conn.prepareStatement(maxBillID);
                        ResultSet rsBillID = pStmt.executeQuery();
                        int BDbillingID = 0;
                        while(rsBillID.next()){
                            BDbillingID = rsBillID.getInt(1);   // gets newly inserted billingID
                        }


                        // GET THE TRANSACTIONS CORRESPONDING TO MONTHLY DUES FOR THE MONTH AND OTHER UNPAID FEES
                        sql = "SELECT MONTHLYDUES.TRXID " +
                                " FROM (SELECT TR.TRXID FROM TRXREFERENCES TR" +
                                " JOIN HOUSEMONTHLYDUES HMD ON TR.TRXID = HMD.TRXID" +
                                " JOIN MONTHLYDUES MD ON MD.MDID = HMD.MDID" +
                                " WHERE HMD.BLOCKNUM = ? AND HMD.LOTNUM = ?" +
                                " AND TR.DESCRIPTION = 'monthly dues'" +
                                " AND MONTH(TR.DATECREATED) = MONTH(NOW()) AND YEAR(TR.DATECREATED) = YEAR(NOW())) MONTHLYDUES" +
                                //get unpaid security violation fees USER2USER*/ " +
                                " LEFT JOIN (SELECT TR.TRXID FROM TRXREFERENCES TR " +
                                " LEFT JOIN TRXLIST TL 		   ON TR.TRXID = TL.TRXID" +
                                " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID" +
                                " JOIN USER2USER UU              ON UU.SECURITYREPORTID = SV.SECURITYREPORTID" +
                                " JOIN USERS U                   ON UU.ACCUSED_USERID = U.USERID" +
                                " WHERE U.USERID = ? " +
                                " AND   U.STATUS = 'active' " +
                                " AND   TL.AMOUNTPAID = NULL) SEC1 ON SEC1.TRXID = MONTHLYDUES.TRXID " +
                                //get unpaid security violation fees USER2ANYONE " +
                                " LEFT JOIN (SELECT TR.TRXID FROM TRXREFERENCES TR  " +
                                " LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID " +
                                " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID " +
                                " JOIN USER2ANYONE UA            ON UA.SECURITYREPORTID = SV.SECURITYREPORTID " +
                                " JOIN USERS U                   ON UA.USERID = U.USERID " +
                                " WHERE U.USERID = ? " +
                                " AND   U.STATUS = 'active' " +
                                " AND   TL.AMOUNTPAID = NULL) SEC2 ON SEC2.TRXID = SEC1.TRXID " +
                                //get unpaid vehicle pass fees " +
                                " LEFT JOIN (SELECT TR.TRXID FROM TRXREFERENCES TR  " +
                                " LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID " +
                                " JOIN USER_VEHICLES UV          ON UV.TRXID = TR.TRXID " +
                                " JOIN USERS U                   ON UV.USERID = U.USERID " +
                                " WHERE U.USERID = ? " +
                                " AND   U.STATUS = 'active' " +
                                " AND   TL.AMOUNTPAID = NULL) VEHICLE ON VEHICLE.TRXID = SEC2.TRXID " +
                                //get unpaid security violation fees VEHICLES2USER " +
                                " LEFT JOIN (SELECT TR.TRXID FROM TRXREFERENCES TR  " +
                                " LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID " +
                                " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID " +
                                " JOIN VEHICLE2USER VU           ON VU.SECURITYREPORTID = SV.SECURITYREPORTID " +
                                " JOIN VEHICLES V                ON V.PLATENUM = VU.PLATENUM " +
                                " JOIN USER_VEHICLES UV          ON UV.PLATENUM = V.PLATENUM " +
                                " JOIN USERS U                   ON UV.USERID = U.USERID " +
                                " WHERE U.USERID = ? " +
                                " AND   U.STATUS = 'active' " +
                                " AND   TL.AMOUNTPAID = NULL) VIOLATION ON VIOLATION.TRXID = VEHICLE.TRXID " +
                                //get unpaid registration fees USER " +
                                " LEFT JOIN (SELECT TR.TRXID FROM TRXREFERENCES TR  " +
                                " LEFT JOIN TRXLIST TL ON TR.TRXID = TL.TRXID " +
                                " JOIN USERS U                   ON TR.TRXID = U.TRXID " +
                                " WHERE U.USERID = ? " +
                                " AND   U.STATUS = 'active' " +
                                " AND   TL.AMOUNTPAID = NULL) REG ON REG.TRXID = VIOLATION.TRXID;";

                        pStmt = conn.prepareStatement(sql);
                        pStmt.setInt(1, blocknum);
                        pStmt.setInt(2, lotnum);
                        pStmt.setString(3, users.get(i).getUserID());
                        pStmt.setString(4, users.get(i).getUserID());
                        pStmt.setString(5, users.get(i).getUserID());
                        pStmt.setString(6, users.get(i).getUserID());
                        pStmt.setString(7, users.get(i).getUserID());
                        rs = pStmt.executeQuery();
                        while(rs.next()){
                            // FOR EVERY TRANSACTION IN trxReferences that has not been paid yet by the user
                            String insertBillingDetail = "INSERT INTO BILLINGDETAILS(BILLINGID, TRXID)"
                                    + " VALUES(?, ?)";

                            pStmt = conn.prepareStatement(insertBillingDetail);
                            pStmt.setInt(1, BDbillingID);
                            pStmt.setInt(2, rs.getInt(1));
                            int insertedBD = pStmt.executeUpdate();
                            if(insertedBD != 0){
                                System.out.println("Successfully inserted to billing details!");
                                isSuccess = true;
                            }
                        }
                    }
                }// END OF IF bill is generated
                else{
                    message = "Billing statements already generated for this month.";
                }
                
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
        
        return isSuccess;
    }
    
    /**
     * returns the message for feedback after generating billings
     *
     * @param nothing
     * @return String
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static String getMessage() {
        return message;
    }
    
    
    /**
     * records payment for a given billing ID
     *
     * @param billID
     * @return boolean
     * @throws nothing
     *
     * @since 10-28-17
     */
    public static boolean payBilling(int billID){
        boolean isPaid = false;
        Connection conn = null;
            PreparedStatement pStmt = null;
            String sql;
            // gets the total due from billing, then inserts that to the total paid of the same billing
            try{
                conn = DatabaseUtils.retrieveConnection();
                // get the amount due from billing
                sql = "SELECT TOTALDUE FROM BILLING WHERE BILLINGID = ?";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, billID);
                ResultSet rs = pStmt.executeQuery();
                double totaldue = 0;
                while(rs.next()){
                     totaldue = rs.getDouble(1);
                }
                
                // update total paid in Billing table with the amount due 
                sql = "UPDATE BILLING SET TOTALPAID = ? WHERE BILLINGID = ?";
                pStmt = conn.prepareStatement(sql);
                pStmt.setDouble(1, totaldue);
                pStmt.setInt(2, billID);
                //pStmt.setString(2, tryLogin.getPasswd());
                
                int rsPay = pStmt.executeUpdate();
                if(rsPay != 0){
                    System.out.println("Successfully updated payments!");
                    isPaid = true;
                }
                
               // insert into journal also
               sql = "INSERT INTO TRANSACTION_JOURNAL(TRXDATE, TRXAMNT, TRXAMNTPAID) VALUES(DATE(NOW()), ?, ?)";
               pStmt = conn.prepareStatement(sql);
                pStmt.setDouble(1, totaldue);
                pStmt.setDouble(2, totaldue);
                
                int rsJournal = pStmt.executeUpdate();
                if(rsJournal != 0){
                    System.out.println("Successfully updated journal!");
                    isPaid = true;
                }
                // then get the journal id of the newly inserted journal 
                String maxJournal = "SELECT JOURNALID FROM TRANSACTION_JOURNAL ORDER BY JOURNALID DESC LIMIT 1;";
                pStmt = conn.prepareStatement(maxJournal);
                ResultSet rsjournalID = pStmt.executeQuery();
                int maxJournalID = 0;
                while(rsjournalID.next()){
                    maxJournalID = rsjournalID.getInt(1);
                }
                        
                // get user id, blocknum, and lotnum from billing needed for inserting into trxList & payment details
                sql = "SELECT B.BLOCKNUM, B.LOTNUM, U.USERID FROM BILLING B"
                        + " JOIN HOMEOWNER HO ON HO.BLOCKNUM = B.BLOCKNUM AND HO.LOTNUM = B.LOTNUM"
                        + " JOIN USERS U ON U.USERID = HO.USERID WHERE B.BILLINGID = ?";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, billID);
                rs = pStmt.executeQuery();
                int blocknum = 0, 
                        lotnum = 0;
                
                String userid = "";
                while(rs.next()){
                     blocknum = rs.getInt(1);
                     lotnum = rs.getInt(2);
                     userid = rs.getString(3);
                }
                
                    sql = " SELECT TR.TRXID, TR.AMOUNT FROM TRXREFERENCES TR" +
                            " JOIN BILLINGDETAILS BD ON BD.TRXID = TR.TRXID" +
                            " WHERE BILLINGID = ?; ";
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, billID);
                    rs = pStmt.executeQuery();
                    while(rs.next()){
                        // INSERT INTO PAYMENT DETAILS
                        String insertPayDetails = "INSERT INTO PAYMENTDETAILS(BILLING_BILLINGID, JOURNALID, TRXID)"
                                + " VALUES(?, ?, ?)";
                
                        pStmt = conn.prepareStatement(insertPayDetails);
                        pStmt.setInt(1, billID);
                        pStmt.setInt(2, maxJournalID);
                        pStmt.setInt(3, rs.getInt(1)); // transaction ID
                        int insertedPD = pStmt.executeUpdate();
                        if(insertedPD != 0){
                            System.out.println("Successfully inserted to payment details!");
                            isPaid = true;
                        }
                        
                        // INSERT INTO TRANSACTION LIST
                        String insertTrxList = "INSERT INTO TRXLIST(JOURNALID, TRXID, AMOUNTPAID)"
                                + " VALUES(?, ?, ?)";
                
                        pStmt = conn.prepareStatement(insertTrxList);
                        pStmt.setInt(1, maxJournalID);
                        pStmt.setInt(2, rs.getInt(1));// transaction ID
                        pStmt.setDouble(3, rs.getDouble(2));// transaction amount
                        
                        int insertedBD = pStmt.executeUpdate();
                        if(insertedBD != 0){
                            System.out.println("Successfully inserted to transaction list!");
                            isPaid = true;
                        }
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
        return isPaid;
    }
    
    /* Documentation for developer courtesy of Ivy Lim
    * COMMENTS:
    * this main method is for testing purposes.
    *
    * original code: 10-28-17 by I. Lim
    * last update: 10-28-17 by I. Lim - added query methods
   */
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
        System.out.println("get TRX REF");
        for(TransactionReference b : BillingDAO.getTrxRef(1)){
            System.out.println(b.getDate());
        }
        System.out.println("End");
        System.out.println("GENERATE BILLING");
        BillingDAO.generateBillingForAll();
        System.out.println("PAY BILLING");
        BillingDAO.payBilling(1);
        
    }
}
