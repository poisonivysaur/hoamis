
package model;

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
 * last update: 11-25-17 by justine - added documentation
*/
public class PropertyStatus {
    private int statusID;
    protected String status;

    public PropertyStatus(String status) {
        this.status = status;
    }
    
    /**
     * returns the status id of the property
     * 
     * @param nothing
     * @return int containing the status id of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getStatusID() {
        return statusID;
    }
    
    /**
     * sets the status id of the property
     * 
     * @param statusId
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
    
    /**
     * returns the status of the property
     * 
     * @param nothing
     * @return String containing the status of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * sets the status  of the property
     * 
     * @param status
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
