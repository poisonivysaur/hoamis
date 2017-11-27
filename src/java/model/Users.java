/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;

/**
 *
 * @author Jennifer
 */
public class Users implements Serializable {
    
    private String userID;
    private String password;
    private int usertypeID;
    private String lname;
    private String mname;
    private String fname;
    private String bDate;
    private int photoID;
    private int occupationID;
    private String movingIn;
    private int movingOutclearID;
    private int trxID;

    public Users (){}
    
    public Users (String userID, String password){
    
        this.userID = userID;
        this.password = password;
        
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertypeID(int usertypeID) {
        this.usertypeID = usertypeID;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public void setOccupationID(int occupationID) {
        this.occupationID = occupationID;
    }

    public void setMovingIn(String movingIn) {
        this.movingIn = movingIn;
    }

    public void setMovingOutclearID(int movingOutclearID) {
        this.movingOutclearID = movingOutclearID;
    }

    public void setTrxID(int trxID) {
        this.trxID = trxID;
    }
    
    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public int getUsertypeID() {
        return usertypeID;
    }

    public String getLname() {
        return lname;
    }

    public String getMname() {
        return mname;
    }

    public String getFname() {
        return fname;
    }

    public String getbDate() {
        return bDate;
    }

    public int getPhotoID() {
        return photoID;
    }

    public int getOccupationID() {
        return occupationID;
    }

    public String getMovingIn() {
        return movingIn;
    }

    public int getMovingOutclearID() {
        return movingOutclearID;
    }

    public int getTrxID() {
        return trxID;
    }
    
}
