package model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import model.*;


/**
 * A <b>DAO Object</b> for <b>Monthly Dues</b>
 * 
 * @author justine
 * @version 1.001
 * @since 2017-11-25
 */
public class MonthlyDuesDAO{
    /**
     * Gets the current month's monthly dues
     * 
     * @return MonthlyDues object pertaining to the current month's dues
     * @since 11-25-17
     */
    //returns current monthly dues based on current month and year
    public static MonthlyDues getCurrentMonthDues(){
        Connection conn = null;
        MonthlyDues md = null;
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
    
    /**
     * Populates the REF_MONTHLYDUES, MONTHLYDUES, HOUSEMONTHLYDUES, AND TRXREFERENCES tables with the
     * given REF_MONTHLYDUES Object and computed amount per month
     * 
     * @param rmd 
     * @param amountPerMonth
     * @return boolean if the insert was successful or not
     * @since 11-25-17
     */
    
    //populates monthlydues, ref_monthlydues, trxReferences, and HouseMonthlyDues tables
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
    
    /**
     * Computes the total amount of unpaid fees excluding previous unpaid monthly dues
     * 
     * @param userID
     * @return double containing the total amount of unpaid fees (excluding monthly dues)
     * @since 11-25-17
     */
    public static double getUnpaidFees(String userID){
        double totalAmount = 0;
        
        Connection conn = null;
        conn = DatabaseUtils.retrieveConnection();
        try{
            String sql;
            PreparedStatement pStmt;
            ResultSet rs;
            //get unpaid security violation fees USER2USER
             sql = "SELECT    SUM(TR.AMOUNT)"
                       + " FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + " JOIN USER2USER UU              ON UU.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + " JOIN USERS U                   ON UU.ACCUSED_USERID = U.USERID"
                       + " WHERE U.USERID = ?"
                       + " AND   U.STATUS = 'active'"
                       + " AND   TL.AMOUNTPAID IS NULL";
            pStmt = conn.prepareStatement(sql);
        
            pStmt.setString(1, userID);
            double user2userFees = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               user2userFees = rs.getDouble(1); 
            }
            
            //get unpaid security violation fees USER2ANYONE
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + " FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + " JOIN USER2ANYONE UA            ON UA.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + " JOIN USERS U                   ON UA.USERID = U.USERID"
                       + " WHERE U.USERID = ?"
                       + " AND   U.STATUS = 'active'"
                       + " AND   TL.AMOUNTPAID IS NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double user2anyoneFees = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               user2anyoneFees = rs.getDouble(1); 
            }

            //get unpaid vehicle pass fees
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + " FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + " JOIN USER_VEHICLES UV          ON UV.TRXID = TR.TRXID"
                                                  + " JOIN USERS U                   ON UV.USERID = U.USERID"
                       + " WHERE U.USERID = ?"
                       + " AND   U.STATUS = 'active'"
                       + " AND   TL.AMOUNTPAID IS NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double vehicleRegistrationFees = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               vehicleRegistrationFees = rs.getDouble(1); 
            }

            //get unpaid security violation fees VEHICLES2USER
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + " FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + " JOIN SECURITY_VIOLATIONS SV    ON SV.TRXID = TR.TRXID"
                                                  + " JOIN VEHICLE2USER VU           ON VU.SECURITYREPORTID = SV.SECURITYREPORTID"
                                                  + " JOIN VEHICLES V                ON V.PLATENUM = VU.PLATENUM"
                                                  + " JOIN USER_VEHICLES UV          ON UV.PLATENUM = V.PLATENUM"
                                                  + " JOIN USERS U                   ON UV.USERID = U.USERID"
                       + " WHERE U.USERID = ?"
                       + " AND   U.STATUS = 'active'"
                       + " AND   TL.AMOUNTPAID IS NULL";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double vehicleToUserFees = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               vehicleToUserFees = rs.getDouble(1); 
            }

            //get unpaid registration fees USER
            sql =        "SELECT TR.TRXID FROM TRXREFERENCES TR  " +
                            " LEFT JOIN TRXLIST TL ON TR.TRXID = TL.TRXID " +
                            " JOIN USERS U                   ON TR.TRXID = U.TRXID " +
                            " WHERE U.USERID = ? " +
                            " AND   U.STATUS = 'active' " +
                            " AND   TL.AMOUNTPAID = NULL ";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double registrationFees = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               registrationFees = rs.getDouble(1); 
            }
            
            
            
            //get unpaid monthly dues
            sql =        "SELECT    SUM(TR.AMOUNT)"
                       + " FROM      TRXREFERENCES TR LEFT JOIN TRXLIST TL           ON TR.TRXID = TL.TRXID"
                                                  + " JOIN HOUSEMONTHLYDUES HMD      ON HMD.TRXID = TR.TRXID"
                                                  + " JOIN HOMEOWNER HO             ON HO.BLOCKNUM = HMD.BLOCKNUM AND HO.LOTNUM = HMD.LOTNUM"
                                                  + " JOIN USERS U                   ON HO.USERID = U.USERID"
                       + " WHERE U.USERID = ?"
                       + " AND   U.STATUS = 'active'"
                       + " AND   TL.AMOUNTPAID IS NULL"
                        + " AND TR.DESCRIPTION = 'monthly dues'";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userID);
            double monthlyDues = 0;
            rs = pStmt.executeQuery();
            while(rs.next()){
               monthlyDues = rs.getDouble(1); 
            }
            
            totalAmount += monthlyDues + user2userFees + user2anyoneFees + vehicleRegistrationFees + vehicleToUserFees + registrationFees;
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
    
    /**
     * Computes for the standard monthly dues of each homeowner for the current month
     * 
     * @return double containing the monthly dues of a homeowner for the current month
     * @since 11-25-17
     */
    public static double getCurrentHomeownerMonthlyDues(){
        if(getCurrentMonthDues() == null){
            return 0;
        }
        return getCurrentMonthDues().getAmount() / getNumberOfActiveHomeowner();
        
    }
    
    /**
     * Gets the number of active homeowners
     * 
     * @return int with the number of active homeowners
     * @since 11-25-17
     */
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
    
    /**
     * Gets all the entries in the REF_MONTHLYDUES table
     * 
     * @return ArrayList of Ref_MonthlyDues Objects
     * @since 11-25-17
     */
    
    public ArrayList<Ref_MonthlyDues> getAllRangedMonthlyDues(){
        Connection conn = null;
        ArrayList<Ref_MonthlyDues> array = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DatabaseUtils.retrieveConnection();
            String sql = "SELECT * FROM REF_MONTHLYDUES";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rsMD = pStmt.executeQuery();
            while(rsMD.next()){
                array.add(new Ref_MonthlyDues(rsMD.getInt(1), rsMD.getInt(2), rsMD.getInt(3), rsMD.getInt(4), rsMD.getInt(5), rsMD.getDouble(6)));
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return array;
    }
    
    /**
     * Compares a given date range with all the registered date ranges and returns a boolean if the date
     * range overlaps with any of the registered date ranges
     * 
     * @param startMonth
     * @param startYear
     * @param endMonth
     * @param endYear
     * @return boolean indicating if the provided date range overlaps with any of the registered date ranges
     * @since 11-25-17
     */
    public boolean isOverlappingWithStoredDues(int startMonth, int startYear, int endMonth, int endYear){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DatabaseUtils.retrieveConnection();
            String sql = "SELECT * FROM REF_MONTHLYDUES";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rsMD = pStmt.executeQuery();
            
            Calendar startcal = Calendar.getInstance();
            startcal.set(Calendar.YEAR, startYear);
            startcal.set(Calendar.MONTH, startMonth);
            startcal.set(Calendar.DAY_OF_MONTH, 1);
            Date dateStart = startcal.getTime();

            Calendar endcal = Calendar.getInstance();
            endcal.set(Calendar.YEAR, endYear);
            endcal.set(Calendar.MONTH, endMonth);
            endcal.set(Calendar.DAY_OF_MONTH, 1);
            Date dateEnd = endcal.getTime();
            
            while(rsMD.next()){
                Calendar startcal2 = Calendar.getInstance();
                startcal2.set(Calendar.YEAR, rsMD.getInt(3));
                startcal2.set(Calendar.MONTH, rsMD.getInt(2));
                startcal2.set(Calendar.DAY_OF_MONTH, 1);
                Date dateStart2 = startcal2.getTime();

                Calendar endcal2 = Calendar.getInstance();
                endcal2.set(Calendar.YEAR, rsMD.getInt(5));
                endcal2.set(Calendar.MONTH, rsMD.getInt(4));
                endcal2.set(Calendar.DAY_OF_MONTH, 1);
                Date dateEnd2 = endcal2.getTime();
                
                if((startcal.before(endcal2) || startcal.equals(endcal2)) && (endcal.after(startcal2) || endcal.equals(startcal2))){
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return false;
    }
    
    /**
     * Gets a REF_MONTHLYDUES Object from the database based on the passed mDuesID
     * 
     * @param mDuesID
     * @return REF_MONTHLYDUES Object with matching mDuesID
     * @since 11-25-17
     */
    public Ref_MonthlyDues getRefMonthlyDues(int mDuesID){
        Connection conn = null;
        Ref_MonthlyDues rmd = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DatabaseUtils.retrieveConnection();
            String sql = "SELECT * FROM REF_MONTHLYDUES WHERE MDUESID = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, mDuesID);
            ResultSet rsmDues = pStmt.executeQuery();
            if(rsmDues.next()){
                rmd = new Ref_MonthlyDues(mDuesID, rsmDues.getInt(2), rsmDues.getInt(3), rsmDues.getInt(4), rsmDues.getInt(5), rsmDues.getDouble(6));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){}
            }
        }
        return rmd;
    }
    
    public static void main(String[] args) {
        double total = MonthlyDuesDAO.getUnpaidFees("yutainoue");
        System.out.println(total);
        System.out.println("getCurrentMonthDues");
        System.out.println(MonthlyDuesDAO.getCurrentMonthDues());
        System.out.println("getNumberOfActiveHomeowner");
        System.out.println(MonthlyDuesDAO.getNumberOfActiveHomeowner());
        System.out.println("getCurrentHomeownerMonthlyDues()");
        System.out.println(MonthlyDuesDAO.getCurrentHomeownerMonthlyDues());
    }
}
