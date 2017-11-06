package model;

import java.sql.Date;
/**
 * User Object
 * A <b>user</b> object contains the different attributes of 
 * a user of the system and the different functions it can perform on the system.
 * User is the parent class of HomeOwner, HomeMember, Kasambahay, BoardMember.
 * 
 * @author justine
 * @version 1.002
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. 
 * generated getters & setters, note that adding of triggers/checkers for the setters/ delete unnecessary setters, etc. are still needed
 *
 * 
 *
 * original code: 10-27-17 by J. Singca
 * last update: 
 *   10-28-17 by I. Lim - added getters & setters
 *   11-06-17 by Y. Inoue - Changed userType's Data type to int. Added fields for type of users.
*/
public class User {
    // not everyone has a setter, please consider when generating getters and setter
    
    public static final String USERTYPE1 = "Homeowner";
    public static final String USERTYPE2 = "Board Member";
    public static final String USERTYPE3 = "Security Administrator";
    public static final String USERTYPE4 = "System Administrator";
    
    private String fName;
    private String lName;
    private String mName;
    private String userID;
    private String passwd;
    private int usertype;
    private Date bDate;
    private Document photo;
    private Occupation occupation;
    private Date movingIn;
    private MovingOutClearance movingOutclear;
    private TransactionReference trx;
    
    public User(){}

    public User(String fName, String lName, String mName,
            String passwd, int usertype, Date bDate, Document photo,
            Occupation occupation, Date movingIn, MovingOutClearance movingOutclear,
            TransactionReference trx) {
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.passwd = passwd;
        this.usertype = usertype;
        this.bDate = bDate;
        this.photo = photo;
        this.occupation = occupation;
        this.movingIn = movingIn;
        this.movingOutclear = movingOutclear;
        this.trx = trx;
    }
    // Overloaded constructor to support null values
    public User(String fName, String lName, String mName, String passwd, int usertype, Date bDate, TransactionReference trx){
        this(fName, lName, mName, passwd, usertype, bDate, null, null, null, null, trx);
    }
    
    /**
     * returns the transaction reference of the User object when it first registers into the system
     * 
     * @param nothing
     * @return TransactioniReference object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public TransactionReference getTrx() {
        return this.trx;
    }
    
    /**
     * sets the transaction reference of the User object when it first registers into the system
     * 
     * @param trx
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setTrx(TransactionReference trx) {
        this.trx = trx;
        /*  @ To whom it may concern:
        
            just a theory: if it is association, we can only accept objects in the method parameters,
            then set the fields to the field of the object being accepted, for example:
        
            // User attributes
            ...
            protected int trxID;    --> no object in the attributes, so no composition/aggretation, just association in methods
            
            // User methods
            ...
            public void setTrx(TransactionReference trx){   --> accepts an object as param, hence association
                this.trxID = trx.trxID;
            ]
        */
    }

    /**
     * returns the first name of the User object
     * 
     * @param nothing
     * @return String containing the first name of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getfName() {
        return fName;
    }

    /**
     * sets the first name of the User object
     * 
     * @param fName
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * returns the last name of the User object
     * 
     * @param nothing
     * @return String containing the last name of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getlName() {
        return lName;
    }

    /**
     * sets the last name of the User object
     * 
     * @param lName
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * returns the middle name of the User object
     * 
     * @param nothing
     * @return String containing the middle name of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getmName() {
        return mName;
    }

    /**
     * sets the middle name of the User object
     * 
     * @param mName
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * returns the user ID of the object
     * 
     * @param nothing
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getUserID() {
        return userID;
    }

    /**
     * sets the user ID of the object
     * 
     * @param userID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * returns the password of the object
     * 
     * @param nothing
     * @return String containing the password of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * sets the password of the object
     * 
     * @param passwd
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * returns the user type of the object
     * 
     * @param nothing
     * @return UserType
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getUsertype() {
        return usertype;
    }

    /**
     * sets the user type of the object
     * 
     * @param usertype
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    /**
     * returns the birth date of the object
     * 
     * @param nothing
     * @return Date containing the birth date of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Date getbDate() {
        return bDate;
    }

    /**
     * sets birthday of the object
     * 
     * @param bDate
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    /**
     * returns the photo document of the object
     * 
     * @param nothing
     * @return Document object containing the photo of the user object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Document getPhoto() {
        return photo;
    }

    /**
     * sets the photo document object of the User object
     * 
     * @param photo
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setPhoto(Document photo) {
        this.photo = photo;
    }

    /**
     * returns the occupation of the object
     * 
     * @param nothing
     * @return Occupation object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Occupation getOccupation() {
        return occupation;
    }

    /**
     * sets the occupation of the User object
     * 
     * @param occupation
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    /**
     * returns the date of when the User object moved in
     * 
     * @param nothing
     * @return Date object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public Date getMovingIn() {
        return movingIn;
    }

    /**
     * sets the date of when the User object moved in
     * 
     * @param movingIn
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setMovingIn(Date movingIn) {
        this.movingIn = movingIn;
    }

    /**
     * returns the moving out clearance of the User object
     * 
     * @param nothing
     * @return MovingOutClearance object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public MovingOutClearance getMovingOutclear() {
        return movingOutclear;
    }

    /**
     * sets the moving out clearance attribute of the User object
     * 
     * @param movinOutclear
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setMovingOutclear(MovingOutClearance movingOutclear) {
        this.movingOutclear = movingOutclear;
    }
    
    public String getUserTypeString(){
        String userType = "";
        switch (this.usertype){
            case 1:
                userType = USERTYPE1;
                break;
            case 2:
                userType = USERTYPE2;
                break;
            case 3:
                userType = USERTYPE3;
                break;
            case 4:
                userType = USERTYPE4;
                break;
        }
        return userType;
    }
}
