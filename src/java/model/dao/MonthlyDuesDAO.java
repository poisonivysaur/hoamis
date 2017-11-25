package model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anne Charlene Cipres
 */
public class MonthlyDuesDAO{
    
    protected static MonthlyDues md = null;
    //returns current monthly dues based on current month and year
    public static MonthlyDues getCurrentMonthDues(){
        Connection conn = null;
        conn = DatabaseUtils.retrieveConnection();
        String sql = "SELECT * FROM MONTHLYDUES WHERE MONTH = ? AND YEAR = ?";
        
        try {
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
            Date date = new Date();
            int curyear = Integer.parseInt(dateFormat.format(date).substring(0,4));
            int curmonth = Integer.parseInt(dateFormat.format(date).substring(5));
            pStmt.setInt(1, curmonth);
            pStmt.setInt(2, curyear);
            ResultSet rs = pStmt.executeQuery();
            
            while (rs.next()) {
                int mdID = rs.getInt("mdID");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                double amount = rs.getDouble("amount");
                int mduesID = rs.getInt("mduesID");
                
                sql = "SELECT * FROM REF_MONTHLYDUES WHERE MDUESID = ?";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, mduesID);
                ResultSet rs2 = pStmt.executeQuery();
                Ref_MonthlyDues rmd = null;
                
                if(rs2.next()){
                    int startMonth = rs2.getInt("startMonth");
                    int startYear = rs2.getInt("startYear");
                    int endMonth = rs2.getInt("endMonth");
                    int endYear = rs2.getInt("endYear");
                    double amountApproved = rs2.getDouble("amountapproved");

                    rmd = new Ref_MonthlyDues(mduesID, startMonth, startYear, endMonth, endYear, amountApproved);
                }
                
                md = new MonthlyDues(mdID, month, year, amount, rmd);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        
        return md;
    }
    
    //populates monthlydues and ref_monthlydues tables
    public static boolean insertMonthlyDues(Ref_MonthlyDues rmd, double amountPerMonth){
        Connection conn = null;
        conn = DatabaseUtils.retrieveConnection();
        try{
            String sql = "INSERT INTO REF_MONTHLYDUES (startmonth, startyear, endmonth, endyear, amountapproved) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, rmd.getStartMonth());
            pStmt.setInt(2, rmd.getStartYear());
            pStmt.setInt(3, rmd.getEndMonth());
            pStmt.setInt(4, rmd.getEndYear());
            pStmt.setDouble(5, rmd.getAmountApproved());
            
            int inserted = pStmt.executeUpdate();
            if(inserted != 0){
                System.out.println("Successfully inserted!");
            }
            sql = "SELECT MAX(MDUESID) FROM REF_MONTHLYDUES";
            pStmt = conn.prepareStatement(sql);
            ResultSet rsmDues = pStmt.executeQuery();
            int mduesID = 0;
            if(rsmDues.next()){
                mduesID = rsmDues.getInt(1);
            }
            
            
            int starter = rmd.getNumberOfMonths();
            
            int currentYear = rmd.getStartYear();
            int currentMonth = rmd.getStartMonth();
            
            for(int ctr = 0; ctr < starter; ctr++){
                currentMonth ++;
                if(currentMonth > 12){
                    currentMonth -= 12;
                    currentYear ++;
                }
                sql = "INSERT INTO MONTHLYDUES (month, year, amount, mduesID) VALUES (?, ?, ?, ?)";
                pStmt = conn.prepareStatement(sql);
                pStmt.setInt(1, currentMonth);
                pStmt.setInt(2, currentYear);
                pStmt.setDouble(3, amountPerMonth);
                pStmt.setInt(4, mduesID);
                
                inserted = pStmt.executeUpdate();
                if(inserted != 0){
                    System.out.println("Successfully inserted!");
                }
                
                //get latest monthlydues ID
                sql = "SELECT MAX(MDID) FROM MONTHLYDUES";
                pStmt = conn.prepareStatement(sql);
                ResultSet rsmdID = pStmt.executeQuery();
                int mdID = 0;
                if(rsmdID.next()){
                    mdID = rsmdID.getInt(1);
                }
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                
                //get all the homeowners
                sql = "SELECT RP.BLOCKNUM, RP.LOTNUM FROM REF_PROPERTIES RP JOIN HOMEOWNER HO ON RP.BLOCKNUM = HO.BLOCKNUM AND RP.LOTNUM = HO.LOTNUM JOIN USERS U ON U.USERID = HO.USERID WHERE U.STATUS = 'active'";
                pStmt = conn.prepareStatement(sql);
                ResultSet rsHO = pStmt.executeQuery();
                
                while(rsHO.next()){
                    //insert to trxRef
                    sql = "INSERT INTO TRXREFERENCES (amount, interest, totalamount, description, dateCreated)"
                            + "VALUES (?, 0, ?, 'monthly dues', ?)";
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setDouble(1, amountPerMonth / getNumberOfActiveHomeowner());
                    pStmt.setDouble(2, amountPerMonth / getNumberOfActiveHomeowner());
                    pStmt.setDate(3, sqlDate);
                    inserted = pStmt.executeUpdate();
                    if(inserted != 0){
                        System.out.println("Successfully inserted!");
                    }
                    
                    
                    //get latest trxRef
                    sql = "SELECT MAX(TRXID) FROM TRXREFERENCES";
                    pStmt = conn.prepareStatement(sql);
                    ResultSet rstrxID = pStmt.executeQuery();
                    int trxID = 0;
                    if(rstrxID.next()){
                        trxID = rstrxID.getInt(1);
                    }

                    //insert to houseMonthlyDues
                    sql = "INSERT INTO HOUSEMONTHLYDUES (blocknum, lotnum, mdID, trID)"
                            + "VALUES (?, ?, ?, ?)";
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setInt(1, rsHO.getInt(1));
                    pStmt.setInt(2, rsHO.getInt(2));
                    pStmt.setInt(3, mdID);
                    pStmt.setInt(4, trxID);
                    inserted = pStmt.executeUpdate();
                    if(inserted != 0){
                        System.out.println("Successfully inserted!");
                    }
                }
                
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return true;
    }
    
    public static double getUnpaidFees(String userID){
        double totalAmount = 0;
        
        Connection conn = null;
        conn = DatabaseUtils.retrieveConnection();
        try{
            //get unpaid security violation fees USER2USER
            String sql = "SELECT    SUM(TR.AMOUNT)"
                       + "FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + "JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + "JOIN USER2USER UU              ON UU.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + "JOIN USERS U                   ON UU.ACCUSED_USERID = U.USERID"
                       + "WHERE U.USERID = ?"
                       + "AND   U.STATUS = 'active'"
                       + "AND   TL.AMOUNTPAID = NULL";
            PreparedStatement pStmt = conn.prepareStatement(sql);
        
            pStmt.setString(1, userID);
            double user2userFees = pStmt.executeQuery().getInt(1);

            //get unpaid security violation fees USER2ANYONE
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + "FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + "JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + "JOIN USER2ANYONE UA            ON UA.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + "JOIN USERS U                   ON UA.USERID = U.USERID"
                       + "WHERE U.USERID = ?"
                       + "AND   U.STATUS = 'active'"
                       + "AND   TL.AMOUNTPAID = NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double user2anyoneFees = pStmt.executeQuery().getInt(1);

            //get unpaid vehicle pass fees
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + "FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + "JOIN USER_VEHICLES UV          ON UV.TRXID = TR.TRXID"
                                                  + "JOIN USERS U                   ON UV.USERID = U.USERID"
                       + "WHERE U.USERID = ?"
                       + "AND   U.STATUS = 'active'"
                       + "AND   TL.AMOUNTPAID = NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double vehicleRegistrationFees = pStmt.executeQuery().getInt(1);

            //get unpaid security violation fees VEHICLES2USER
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + "FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + "JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + "JOIN VEHICLE2USER VU           ON VU.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + "JOIN VEHICLES V                ON V.PLATENUM = VU.PLATENUM"
                                                  + "JOIN USER_VEHICLES UV          ON UV.PLATENUM = V.PLATENUM"
                                                  + "JOIN USERS U                   ON UA.USERID = U.USERID"
                       + "WHERE U.USERID = ?"
                       + "AND   U.STATUS = 'active'"
                       + "AND   TL.AMOUNTPAID = NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double vehicleToUserFees = pStmt.executeQuery().getInt(1);

            //get unpaid registration fees USER
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + "FROM      TRXREFERENCES TR u";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double registrationFees = pStmt.executeQuery().getInt(1);

            totalAmount += user2userFees + user2anyoneFees + vehicleRegistrationFees + vehicleToUserFees + registrationFees;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        
        return totalAmount;
    }
    
    public static double getCurrentHomeownerMonthlyDues(){
        
        return getCurrentMonthDues().getAmount() / getNumberOfActiveHomeowner();
        
    }
    
    public static int getNumberOfActiveHomeowner(){
        Connection conn = null;
        conn = DatabaseUtils.retrieveConnection();
        String sql = "SELECT COUNT(USERID) FROM USERS WHERE STATUS = 'active'";
        int numOfHO = 0;
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rsNumOfHO = pStmt.executeQuery();
            numOfHO = 0;
            while(rsNumOfHO.next()){
                numOfHO = rsNumOfHO.getInt(1);
            }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return numOfHO;
    }
}
