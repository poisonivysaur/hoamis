
package model;

import java.sql.Date;

/**
 * Election Object
 * A <b>Election<b> object contains the different attributes of 
 * an election ...
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
public class Election {
    
    protected int electionID;
    protected Date electDate;
    protected ElectType electType;
    protected Document electDoc;
    protected BoardMember enablingBoardMember;
    protected String[] choices; //not sure if array or need indiv properties

    public Election(Date electDate, ElectType electType, Document electDoc, BoardMember enablingBoardMember) {
        this.electDate = electDate;
        this.electType = electType;
        this.electDoc = electDoc;
        this.enablingBoardMember = enablingBoardMember;
    }
    
    // add choices as a method na lang
}
