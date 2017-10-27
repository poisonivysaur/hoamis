package model;

import java.sql.Date;
/**
 * 
 * @author justine
 */
public class User {
    // not everyone has a setter, please consider when generating getters and setter
    protected String fName;
    protected String lName;
    protected String mName;
    protected String userID;
    protected String passwd;
    protected UserType usertype;
    protected Date bDate;
    protected Document photo;
    protected Occupation occupation;
    protected Date movingIn;
    protected MovingOutClearance movingOutclear;
    protected TransactionReference trx;

    public User(String fName, String lName, String mName,
            String passwd, UserType usertype, Date bDate, Document photo,
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
    
}
