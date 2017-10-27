
package classes;

/**
 *
 * @author justine
 */
public class Occupation {
    protected int occupationID;
    protected String occupation;

    public Occupation(int occupationID, String occupation) {
        this.occupationID = occupationID;
        this.occupation = occupation;
    }
    
    

    public int getOccupationID() {
        return occupationID;
    }

    public void setOccupationID(int occupationID) {
        this.occupationID = occupationID;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
