
package model;

/**
 * BoardStatus Object
 * A <b>BoardStatus<b> object contains the all the possible types of
 * boart statuses of the system ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy
 * No methods so far, just constructor and attributes.  -Ivy
 *
 * 
 *
 * original code: 10-27-17
 * last update:
*/
public class BoardStatus {
    
    protected int statusID;
    protected String status;

    public BoardStatus(String status) {
        this.status = status;
    }
}
