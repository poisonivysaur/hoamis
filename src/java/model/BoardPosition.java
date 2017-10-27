
package model;

/**
 * BoardPosition Object
 * A <b>BoardPosition<b> object contains the different attributes of 
 * a board position ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy 10-27-17
 * No methods so far, just constructor and attributes.  -Ivy 10-27-17
 *
 * 
 *
 * original code: 10-27-17
 * last update:
*/
public class BoardPosition {
    
    protected int positionID;
    protected String position;

    public BoardPosition(int positionID, String position) {
        this.positionID = positionID;
        this.position = position;
    }
}
