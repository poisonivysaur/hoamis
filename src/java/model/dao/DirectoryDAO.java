/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Document;
import model.Homeowner;
import model.Occupation;
import model.Pet;
import model.User;

/**
 * All Process and methods Related to Directory Modules resides within this class.<br />
 * 
 * @author Yuta
 */
public class DirectoryDAO {
    
    /**
     * Returns the existing Home Owners whose accounts are active.
     * 
     * @return ArrayList of User
     */
    public static ArrayList<User> getHomeowners(){
        ArrayList<User> homeowners = new ArrayList<>();
        Connection conn = null;
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID FROM USERS WHERE STATUS = 'active' AND USERTYPEID = 1 ORDER BY 4,3 DESC;";
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            User u = null;
            while(rs.next()){
                u = new User();
                Document docu = new Document();
                Occupation occu = new Occupation();
                occu.setOccupationID(rs.getInt("OCCUPATIONID"));
                u.setUserID(rs.getString(1));
                u.setUsertype(rs.getInt(2));
                u.setlName(rs.getString(3));
                u.setfName(rs.getString(4));
                u.setmName(rs.getString(5));
                u.setbDate(rs.getDate("BDATE"));
                docu.setDocumentID(rs.getInt("PHOTOID"));
                docu = getDocumentById(conn, docu);
                u.setPhoto(docu);
                u.setOccupation(occu);
                homeowners.add(u);
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
    
    /**
     * Returns a specific Homeowner with complete fields.
     * @param userId
     * @return Homeowner
     */
    public static Homeowner getHomeownerById(String userId){
        Homeowner h = null;
        String sql = "SELECT BLOCKNUM, LOTNUM, USERID FROM HOMEOWNER WHERE USERID = ?";
        Connection conn = null;
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            User temp = getUserById(conn, userId);
            if(temp != null){
                h = new Homeowner();
                h.setUserID(temp.getUserID());
                h.setUsertype(temp.getUsertype());
                h.setlName(temp.getlName());
                h.setfName(temp.getfName());
                h.setmName(temp.getmName());
                h.setbDate(temp.getbDate());
                h.setOccupation(temp.getOccupation());
                h.setPhoto(temp.getPhoto());
                h.setMovingIn(temp.getMovingIn());
                ResultSet rs = pStmt.executeQuery();
                while(rs.next()){
                    h.setBlocknum(rs.getInt(1));
                    h.setLotnum(rs.getInt(2));
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
        return h;
    }
    
    /**
     * Returns an Arraylist of Users where the user's status is active.
     * 
     * @return ArrayList of User
     */
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<>();
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, OCCUPATIONID FROM USERS WHERE STATUS = 'active' ORDER BY 4,3 DESC;";
        Connection conn = null;
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();
            User temp = null;
            while(rs.next()){
                temp = new User();
                Document docu = new Document();
                Occupation occu = new Occupation();
                occu.setOccupationID(rs.getInt("OCCUPATIONID"));
                temp.setUserID(rs.getString(1));
                temp.setUsertype(rs.getInt(2));
                temp.setlName(rs.getString(3));
                temp.setfName(rs.getString(4));
                temp.setmName(rs.getString(5));
                temp.setbDate(rs.getDate("BDATE"));
                docu.setDocumentID(rs.getInt("PHOTOID"));
                docu = getDocumentById(conn, docu);
                temp.setPhoto(docu);
                temp.setOccupation(occu);
                allUsers.add(temp);
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
        return allUsers;
    }
    
    /**
     * While the connection is still running, this method returns a Document object.
     * 
     * @param conn
     * @param docu with documentId is set on the object
     * @return Document with complete information
     * @throws Exception 
     */
    public static Document getDocumentById(Connection conn, Document docu) throws Exception{
        PreparedStatement pStmt = conn.prepareStatement("SELECT DOCUMENTID, DOCUMENTLOCATION FROM DOCUMENTS WHERE DOCUMENTID = ?");
        pStmt.setInt(1, docu.getDocumentID());
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            docu.setDocumentLocation(rs.getString(2));
        }
        return docu;
    }
    
    /**
     * This method helps completing the information to instantiate a Homeowner Object or simply for searching a User by its userId.<br />
     * This method assumes my Database Connection is still running
     * @param conn
     * @param userid
     * @return User
     * @throws Exception 
     */
    public static User getUserById(Connection conn, String userid) throws Exception {
        User findUser = null;
        String sql = "SELECT USERID, USERTYPEID, LNAME, FNAME, MNAME, BDATE, PHOTOID, MOVINGIN, OCCUPATIONID FROM USERS WHERE STATUS = 'active' AND USERID = ?;";

        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, userid);
        ResultSet rs = pStmt.executeQuery();

        while (rs.next()) {
            findUser = new User();
            Document docu = new Document();
            Occupation occu = new Occupation();
            occu.setOccupationID(rs.getInt("OCCUPATIONID"));
            occu = getOccupationById(conn, occu);
            findUser.setUserID(rs.getString(1));
            findUser.setUsertype(rs.getInt(2));
            findUser.setlName(rs.getString(3));
            findUser.setfName(rs.getString(4));
            findUser.setmName(rs.getString(5));
            findUser.setbDate(rs.getDate("BDATE"));
            docu.setDocumentID(rs.getInt("PHOTOID"));
            docu = getDocumentById(conn, docu);
            findUser.setPhoto(docu);
            findUser.setOccupation(occu);
            findUser.setMovingIn(rs.getDate("MOVINGIN"));
        }

        return findUser;
    }
    
    /**
     * This method returns a complete Occupation object information.<br />
     * The occupation object inside the parameter must have its occupationId set.
     * @param conn
     * @param occupation
     * @return Occupation
     * @throws Exception 
     */
    public static Occupation getOccupationById(Connection conn, Occupation occupation) throws Exception{
        PreparedStatement pStmt = conn.prepareStatement("SELECT OCCUPATIONID, OCCUPATION FROM REF_OCCUPATION WHERE OCCUPATIONID = ?;");
        pStmt.setInt(1, occupation.getOccupationID());
        ResultSet rs = pStmt.executeQuery();
        while(rs.next()){
            occupation.setOccupation(rs.getString(2));
        }
        return occupation;
    }
    
    /**
     * Returns the Home Members of the Selected Home Owner.
     * @param homeowner
     * @return ArrayList of User
     */
    public static ArrayList<User> getHomeMember(Homeowner homeowner){
        ArrayList<User> homemembers = new ArrayList<>();
        Connection conn = DatabaseUtils.retrieveConnection();
        String sql = "SELECT USERID FROM HOMEMEMBER WHERE RENTING = TRUE AND BLOCKNUM = ? AND LOTNUM = ?";
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, homeowner.getBlocknum());
            pStmt.setInt(2, homeowner.getLotnum());
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                homemembers.add(getUserById(conn, rs.getString(1)));
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
        return homemembers;
    }
    
    public static ArrayList<User> getKasambahay(Homeowner homeowner){
        ArrayList<User> kasambahay = new ArrayList<>();
        Connection conn = DatabaseUtils.retrieveConnection();
        String sql = "SELECT USERID FROM KASAMBAHAY WHERE ENDDATE IS NULL AND BLOCKNUM = ? AND LOTNUM = ?";
        try{
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, homeowner.getBlocknum());
            pStmt.setInt(2, homeowner.getLotnum());
            ResultSet rs = pStmt.executeQuery();
            while(rs.next()){
                kasambahay.add(getUserById(conn, rs.getString(1)));
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
        return kasambahay;
    }
    
    /**
     * This method adds a new Pet to the User.<br />
     * Method will either return a true or false. <br />
     * @param p
     * @param owner
     * @return True is insertion is a succes, False if insertion was not a success
     */
    public static boolean addNewPet(Pet p, User owner){
        Connection conn = null;
        String sql = "INSERT INTO USER_PETS VALUES(?, ?, ?, ?, 1)";
        boolean isSuccess = false;
        
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, owner.getUserID());
            pStmt.setInt(2, p.getAnimalType());
            pStmt.setString(3, p.getPetName());
            pStmt.setBoolean(4, p.isVaccinated());
            int added = pStmt.executeUpdate();
            if(added == 1){
                isSuccess = true;
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
        
        return isSuccess;
    }
    
    public static ArrayList<Pet> getPetsByUserId(String userId){
        ArrayList<Pet> pets = new ArrayList<>();
        String sql = "SELECT PETNAME, ANIMALTYPE, VACCINATED, PHOTOID FROM USER_PETS WHERE USERID = ?";
        Connection conn = null;
        
        try{
            conn = DatabaseUtils.retrieveConnection();
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, userId);
            ResultSet rs = pStmt.executeQuery();
            
            Pet temp = null;
            while(rs.next()){
                Document tempDocu = new Document();
                tempDocu.setDocumentID(1);
                temp = new Pet(rs.getString(1), rs.getInt(2), rs.getBoolean(3), getDocumentById(conn, tempDocu));
                pets.add(temp);
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
        
        return pets;
    }
    
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseUtils.retrieveConnection();
        Homeowner h = getHomeownerById("yutainoue2");
        for(User a : getKasambahay(h)){
            System.out.println(a.getUserID());
        }
        
        conn.close();
    }
}