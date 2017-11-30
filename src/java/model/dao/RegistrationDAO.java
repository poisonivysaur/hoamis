/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import model.Homeowner;
import model.MapPoint;
import model.Occupation;
import model.Property;
import model.User;

/**
 *
 * @author Yuta
 */
public class RegistrationDAO {
    
    public static ArrayList<Occupation> getAllOccupations(){
        Connection conn = null;
        ArrayList<Occupation> occupations = new ArrayList<>();
        String sql = "SELECT OCCUPATIONID, OCCUPATION FROM REF_OCCUPATION WHERE OCCUPATIONID != 9 ;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                Occupation o = new Occupation();
                o.setOccupationID(rs.getInt(1));
                o.setOccupation(rs.getString(2));
                occupations.add(o);
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
        
        return occupations;
    }
    
    public static ArrayList<User> getAllHomeowners(){
        Connection conn = null;
        ArrayList<User> homeowners = new ArrayList<>();
        String sql = "SELECT USERID, FNAME, LNAME FROM USERS U WHERE U.USERID IN (SELECT USERID FROM HOMEOWNER) AND STATUS = 'active';";
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                User ho = new User();
                ho.setUserID(rs.getString(1));
                ho.setfName(rs.getString(2));
                ho.setlName(rs.getString(3));
                ho.setUsertype(1);
                homeowners.add(ho);
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
        
        return homeowners;
    }
    
    public static ArrayList<Homeowner> getHomeowners(){
        ArrayList<Homeowner> homeowners = new ArrayList<>();
        String sql = "SELECT H.BLOCKNUM, H.LOTNUM, H.USERID FROM HOMEOWNER H WHERE (SELECT STATUS FROM USERS WHERE USERID = H.USERID) = 'active';";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                Homeowner h = new Homeowner();
                h.setBlocknum(rs.getInt(1));
                h.setLotnum(rs.getInt(2));
                h.setUserID(rs.getString(3));
                homeowners.add(h);
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
        return homeowners;
    }
    
    public static int insertNewOccupation(String occupation){
        Connection conn = null;
        int occupationId = -1;
        String sql = "INSERT INTO REF_OCCUPATION VALUES(0, ?)";
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, occupation);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            if(rs.next()){
                occupationId = rs.getInt(1);
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
        return occupationId;
    }
    
    public static ArrayList<Property> getAvailableProperty(){
        ArrayList<Property> availProperties = new ArrayList<>();
        String sql = "SELECT BLOCKNUM, LOTNUM, MAPPOINTID FROM REF_PROPERTIES WHERE PROPERTYSTATUSID = 1";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                Property p = new Property();
                p.setBlocknum(rs.getInt(1));
                p.setLotnum(rs.getInt(2));
                p.setMapppoint(getMapPointById(conn, rs.getInt(3)));
                availProperties.add(p);
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
        return availProperties;
    }
    
    public static ArrayList<Property> getRentedProperty(){
        ArrayList<Property> availProperties = new ArrayList<>();
        String sql = "SELECT BLOCKNUM, LOTNUM, MAPPOINTID FROM REF_PROPERTIES WHERE PROPERTYSTATUSID = 4";
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            
            while(rs.next()){
                Property p = new Property();
                p.setBlocknum(rs.getInt(1));
                p.setLotnum(rs.getInt(2));
                p.setMapppoint(getMapPointById(conn, rs.getInt(3)));
                availProperties.add(p);
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
        return availProperties;
    }
    
    private static MapPoint getMapPointById(Connection conn, int map) throws Exception{
        MapPoint mapObj = new MapPoint();
        PreparedStatement pStmt = conn.prepareStatement("SELECT MAPPOINTID, XAXIS, YAXIS, TITLE FROM MAPPOINT WHERE MAPPOINTID = ? AND REMOVED IS NULL;");
        pStmt.setInt(1, map);
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            mapObj.setMappointID(rs.getInt(1));
            mapObj.setxAxis(rs.getString(2));
            mapObj.setyAxis(rs.getString(3));
            mapObj.setTitle(rs.getString(4));
        }
        return mapObj;
    }
    
    public static boolean insertNewHomeowner(User newHomeOwner, String birthday, int occupation, int blocknum, int lotnum){
        boolean isSuccess = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, TRXID, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?, ?);";
        MonthlyDuesDAO d = new MonthlyDuesDAO();
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newHomeOwner.getUserID());
            pStmt.setString(2, newHomeOwner.getPasswd());
            pStmt.setInt(3, newHomeOwner.getUsertype());
            pStmt.setString(4, newHomeOwner.getlName());
            pStmt.setString(5, newHomeOwner.getfName());
            pStmt.setString(6, newHomeOwner.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            pStmt.setInt(9, insertRegistrationTransaction(conn));
            pStmt.setString(10, "active");
            
            int added1 = pStmt.executeUpdate();
            
            if(added1 == 1){
                pStmt = conn.prepareStatement("INSERT INTO HOMEOWNER VALUES (?, ?, ?);");
                pStmt.setInt(1, blocknum);
                pStmt.setInt(2, lotnum);
                pStmt.setString(3, newHomeOwner.getUserID());
                pStmt.executeUpdate();
                pStmt = conn.prepareStatement("UPDATE REF_PROPERTIES SET PROPERTYSTATUSID = 4 WHERE BLOCKNUM = ? AND LOTNUM = ?");
                pStmt.setInt(1, blocknum);
                pStmt.setInt(2, lotnum);
                pStmt.executeUpdate();
                System.out.println("Justin Method: " + d.assignNewUserMonthlyDues(blocknum, lotnum));
                isSuccess = true;
            }
            
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isSuccess = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        return isSuccess;
    }
    
    public static boolean insertNewSystemAdmin(User newSysAdmin, String birthday, int occupation){
        boolean isSuccess = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?);";
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newSysAdmin.getUserID());
            pStmt.setString(2, newSysAdmin.getPasswd());
            pStmt.setInt(3, newSysAdmin.getUsertype());
            pStmt.setString(4, newSysAdmin.getlName());
            pStmt.setString(5, newSysAdmin.getfName());
            pStmt.setString(6, newSysAdmin.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            pStmt.setString(9, "active");
            
            int added1 = pStmt.executeUpdate();
            if(added1 == 1){
                isSuccess = true;
            }
            
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isSuccess = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        return isSuccess;
    }
    
    public static boolean insertNewSecurity(User newSecurity, String birthday, int occupation){
        boolean isSuccess = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?);";
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newSecurity.getUserID());
            pStmt.setString(2, newSecurity.getPasswd());
            pStmt.setInt(3, newSecurity.getUsertype());
            pStmt.setString(4, newSecurity.getlName());
            pStmt.setString(5, newSecurity.getfName());
            pStmt.setString(6, newSecurity.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            pStmt.setString(9, "active");
            
            int added1 = pStmt.executeUpdate();
            if(added1 == 1){
                pStmt = conn.prepareStatement("INSERT INTO SECURITYPERSONNEL VALUES (?)");
                pStmt.setString(1, newSecurity.getUserID());
                int added2 = pStmt.executeUpdate();
                if(added2 == 1){
                    isSuccess = true;
                }
            }
            
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isSuccess = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        return isSuccess;
    }
    
    public static boolean insertHomeMember(User newMember, String birthday, int occupation, int blocknum, int lotnum){
        boolean isSuccess = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?);";
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newMember.getUserID());
            pStmt.setString(2, newMember.getPasswd());
            pStmt.setInt(3, newMember.getUsertype());
            pStmt.setString(4, newMember.getlName());
            pStmt.setString(5, newMember.getfName());
            pStmt.setString(6, newMember.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            pStmt.setString(9, "active");
            
            int added1 = pStmt.executeUpdate();
            if(added1 == 1){
                pStmt = conn.prepareStatement("INSERT INTO HOMEMEMBER VALUES(?, true, ?, ?)");
                pStmt.setString(1, newMember.getUserID());
                pStmt.setInt(2, blocknum);
                pStmt.setInt(3, lotnum);
                int added2 = pStmt.executeUpdate();
                if(added2 == 1){
                    isSuccess = true;
                }
            }
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isSuccess = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        return isSuccess;
    }
    
    public static boolean insertKasambahay(User newKasambahay, String birthday, int occupation, int blocknum, int lotnum){
        boolean isSuccess = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?);";
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newKasambahay.getUserID());
            pStmt.setString(2, newKasambahay.getPasswd());
            pStmt.setInt(3, newKasambahay.getUsertype());
            pStmt.setString(4, newKasambahay.getlName());
            pStmt.setString(5, newKasambahay.getfName());
            pStmt.setString(6, newKasambahay.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            pStmt.setString(9, "active");
            
            int added1 = pStmt.executeUpdate();
            if(added1 == 1){
                pStmt = conn.prepareStatement("INSERT INTO KASAMBAHAY(USERID, STARTDATE, BLOCKNUM, LOTNUM) VALUES(?, NOW(), ?, ?)");
                pStmt.setString(1, newKasambahay.getUserID());
                pStmt.setInt(2, blocknum);
                pStmt.setInt(3, lotnum);
                int added2 = pStmt.executeUpdate();
                if(added2 == 1){
                    isSuccess = true;
                }
            }
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isSuccess = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        return isSuccess;
    }
    
    
    public static boolean insertNewSystemUser(User user, String birthday, int occupation, int blocknum, int lotnum){
        boolean isInserted = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, PASSWD, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, TRXID, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, NOW(), ?, ?)";
        //INSERT INTO `hoamis`.`USERS` (1`userID`, 2`passwd`, 3`usertypeID`, 4`lname`, 5`fname`, 6`mname`, 7`bDate`, 8`photoID`, 9`occupationID`, 0`movingIn`, 11`trxID`, 12`status`) VALUES 
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
            java.util.Date dateStr = formatter.parse(birthday);
            java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, user.getUserID());
            pStmt.setString(2, user.getPasswd());
            pStmt.setInt(3, user.getUsertype());
            pStmt.setString(4, user.getlName());
            pStmt.setString(5, user.getfName());
            pStmt.setString(6, user.getmName());
            pStmt.setString(7, birthday);
            pStmt.setInt(8, occupation);
            if(user.getUsertype() == 1){
                int trxId = insertRegistrationTransaction(conn);
                int journalId = insertRegistrationJournal(conn);
                insertTrxList(conn, journalId, trxId);
                pStmt.setInt(9, trxId);
            }else{
                pStmt.setNull(9, Types.INTEGER);
            }
            pStmt.setString(10, "active");
            
            //System.out.println("Birthday DB: " + dateDB);
            
            int added = pStmt.executeUpdate();
            
            if(added != 0){
                isInserted = true;
                if(user.getUsertype() == 1){
                    pStmt = conn.prepareStatement("INSERT INTO HOMEOWNER VALUES(?, ?, ?);");
                    pStmt.setInt(1, blocknum);
                    pStmt.setInt(2, lotnum);
                    pStmt.setString(3, user.getUserID());
                    int added2 = pStmt.executeUpdate();
                    if(added2 == 0){
                        isInserted = false;
                    }else{
                        isInserted = true;
                    }
                }
            }
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                isInserted = false;
                conn.rollback();
            }catch(Exception e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        
        return isInserted;
    }
        
    public static boolean insertNormalUser(User user, String birthday, int occupation, int blocknum, int lotnum){
        boolean isInserted = false;
        Connection conn = null;
        String sql = "INSERT INTO USERS(USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID, MOVINGIN, STATUS) VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), 'active');";
        //INSERT INTO `hoamis`.`USERS` (1`userID`, 2`usertypeID`, 3`lname`, 4`fname`, 5`mname`, 6`bDate`, 7`photoID`, 8`occupationID`, 9`movingIn`, 10`status`) VALUES
        try{
            conn = DatabaseUtils.retrieveConnection();
            conn.setAutoCommit(false);
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, user.getUserID());
            pStmt.setInt(2, user.getUsertype());
            pStmt.setString(3, user.getlName());
            pStmt.setString(4, user.getfName());
            pStmt.setString(5, user.getmName());
            pStmt.setString(6, birthday);
            pStmt.setInt(7, 1);
            pStmt.setInt(8, occupation);
            
            int added1 = pStmt.executeUpdate();
            
            if(added1 != 0){
                int added2 = 0;
                if(user.getUsertype() == 5){
                    pStmt = conn.prepareStatement("INSERT INTO HOMEMEMBER VALUES(?, ?, ?, ?);");
                    pStmt.setString(1, user.getUserID());
                    pStmt.setBoolean(2, true);
                    pStmt.setInt(3, blocknum);
                    pStmt.setInt(4, lotnum);
                    added2 = pStmt.executeUpdate();
                    if(added2 != 0){
                        isInserted = true;
                    }
                }else{
                    pStmt = conn.prepareStatement("INSERT INTO KASAMBAHAY VALUES(?, NOW(), ?, ?, ?);");
                    pStmt.setString(1, user.getUserID());
                    pStmt.setNull(2, Types.DATE);
                    pStmt.setInt(3, blocknum);
                    pStmt.setInt(4, lotnum);
                    added2 = pStmt.executeUpdate();
                    if(added2 != 0){
                        isInserted = true;
                    }
                }
            }else{
                isInserted = false;
            }
            
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
            try{
                conn.rollback();
                isInserted = false;
            }catch(SQLException e2){}
        }finally{
            if(conn != null){
                try{
                    conn.close();
                    conn.setAutoCommit(true);
                }catch(Exception e){}
            }
        }
        System.out.println(isInserted);
        return isInserted;
    }
        
    public static int insertRegistrationTransaction(Connection conn) throws Exception{
        String sql = "INSERT INTO trxreferences (`trxID`, `amount`, `interest`, `totalamount`, `description`, `dateCreated`) VALUES (0, 250, 0, 250, 'registration', NOW());";
        PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int newTrx = -1;
        pStmt.executeUpdate();
        ResultSet rs = pStmt.getGeneratedKeys();
        while(rs.next()){
            newTrx = rs.getInt(1);
        }
        return newTrx;
    }
    
    public static int insertRegistrationJournal(Connection conn) throws Exception{
        String sql = "INSERT INTO transaction_journal VALUES(0, NOW(), 250, 250);";
        PreparedStatement pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int newJournal = -1;
        pStmt.executeUpdate();
        ResultSet rs = pStmt.getGeneratedKeys();
        while(rs.next()){
            newJournal = rs.getInt(1);
        }
        return newJournal;
    }
    
    public static void insertTrxList(Connection conn, int journalId, int trxId) throws Exception{
        String sql = "INSERT INTO TRXLIST VALUES(?, ?, 250);";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setInt(1, journalId);
        pStmt.setInt(2, trxId);
        pStmt.executeUpdate();
        
    }
    
    public static void main(String[] args) {
        Connection conn = DatabaseUtils.retrieveConnection();
        try{
            System.out.println(insertRegistrationJournal(conn));
            
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
