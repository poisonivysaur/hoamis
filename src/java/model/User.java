package classes;

import java.sql.Date;
/**
 * 
 * @author justine
 */
public class User {
    protected String fName;
    protected String lName;
    protected String mName;
    protected String userID;
    protected String passwd;
    protected int usertypeID;
    protected Date bDate;
    protected int photoID;
    protected int occupationID;
    protected Date movingIn;
    protected int movingOutclearID;
    protected int trxID;

    public User(String fName, String lName, String mName, String userID, String passwd, int usertypeID, Date bDate, int photoID, int occupationID, Date movingIn, int movingOutclearID, int trxID) {
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.userID = userID;
        this.passwd = passwd;
        this.usertypeID = usertypeID;
        this.bDate = bDate;
        this.photoID = photoID;
        this.occupationID = occupationID;
        this.movingIn = movingIn;
        this.movingOutclearID = movingOutclearID;
        this.trxID = trxID;
    }

    public String getfName() {
        return fName;
    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(int usertypeID) {
        this.usertypeID = usertypeID;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getOccupationID() {
        return occupationID;
    }

    public void setOccupationID(int occupationID) {
        this.occupationID = occupationID;
    }

    public String getUserID() {
        return userID;
    }

    public Date getMovingIn() {
        return movingIn;
    }

    public int getMovingOutclearID() {
        return movingOutclearID;
    }

    public int getTrxID() {
        return trxID;
    }
    
}
