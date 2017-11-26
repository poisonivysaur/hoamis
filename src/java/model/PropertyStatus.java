
package model;

import java.io.Serializable;

/**
 * PropertyStatus Object
 * A <b>PropertyStatus</b> object contains the ID of the property status type and
 * the property status description
 * 
 * @author ivy
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please notify Ivy first before updating any major parts of this code.
 *
 * 
 *
 * original code: 10-28-17 by I. Lim
 * last update:
*/
public class PropertyStatus implements Serializable {
    private int statusID;
    protected String status;
	
	public PropertyStatus(){}

    public PropertyStatus(String status) {
        this.status = status;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
