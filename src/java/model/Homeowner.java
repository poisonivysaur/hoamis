
package model;

import java.sql.Date;

/**
 * Homeowner Object
 * A <b>Homeowner</b> object contains the different attributes of 
 * a homeowner and the different functions it can perform on the system.
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code.
 *
 * 
 *
 * original code: 10-27-17 by J. Singca
 * last update:
*/
public class Homeowner extends User{
    
    protected int blocknum;
    protected int lotnum;
    protected int userid;

    // Make this readable by making it fit in the screen lang
    public Homeowner(){
        super();
    }
    public Homeowner(int blocknum, int lotnum, int userid) {
        super();
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.userid = userid;
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
