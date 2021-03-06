
package model;

import java.io.Serializable;

/**
 * BoardPosition Object
 * A <b>BoardPosition</b> object contains the ID of the board position type and
 * the board position description
 * 
 * @author justine
 * @version 1.002
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code.
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update: 10-28-17 by I. Lim - added getters & setters
*/
public class BoardPosition implements Serializable {
    
    private int positionID;
    protected String position;
	
	public BoardPosition(){}

    public BoardPosition(int positionID, String position) {
        this.positionID = positionID;
        this.position = position;
    }

    /**
     * returns the position ID of the object
     * 
     * @param nothing
     * @return int containing the position ID of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public int getPositionID() {
        return positionID;
    }

    /**
     * sets the position ID of the object
     * 
     * @param positionID
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    /**
     * returns the position of the object
     * 
     * @param nothing
     * @return String containing the position description of the object
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public String getPosition() {
        return position;
    }

    /**
     * sets the position of the object
     * 
     * @param position
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */
    public void setPosition(String position) {
        this.position = position;
    }
    
}
