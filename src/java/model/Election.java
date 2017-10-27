
package model;

import java.sql.Date;

/**
 *
 * @author justine
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
