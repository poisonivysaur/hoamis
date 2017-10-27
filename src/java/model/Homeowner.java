
package classes;

import java.sql.Date;

/**
 * 
 * @author justine
 */
public class Homeowner extends User{
    
    protected int blocknum;
    protected int lotnum;
    
    public Homeowner(String fName, String lName, String mName,
            String userID, String passwd, int usertypeID, Date bDate,
            int photoID, int occupationID, Date movingIn,
            int movingOutclearID, int trxID, int blocknum, int lotnum) {
        super(fName, lName, mName, userID, passwd, usertypeID, bDate, photoID, occupationID, movingIn, movingOutclearID, trxID);
        this.blocknum = blocknum;
        this.lotnum = lotnum;
    }

    public int getBlocknum() {
        return blocknum;
    }

    public void setBlocknum(int blocknum) {
        this.blocknum = blocknum;
    }

    public int getLotnum() {
        return lotnum;
    }

    public void setLotnum(int lotnum) {
        this.lotnum = lotnum;
    }
    
}
