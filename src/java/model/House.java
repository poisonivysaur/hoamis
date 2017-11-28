
package Objects;

/**
 * House Object
 * A <b>house</b> object contains the data needed to locate the house 
 * owned by a homeowner using the community map.
 * 
 * @author Jayvee Gabriel
 * @version 1.0
 * @since 2017-11-25
 */
public class House {
    
    /** 
     * The map point ID of this house. 
     */
    protected String mappointID;
    
    /**
     * The x axis or the latitude for this house.
     */
    protected String xAxis;
    
    /**
     * The y axis or the longitude for this house.
     */
    protected String yAxis;
    
    /**
     * The first name of the owner of this house.
     */
    protected String fName;
    
    /**
     * The last name of the owner of this house.
     */
    protected String lName;

    /**
     * Returns the map point ID of this house.
     * @return String containing the map point ID of the object.
     */
    public String getMappointID() {
        return mappointID;
    }

    /**
     * Sets the map point ID of this house.
     * @param mappointID 
     * 
     * @since 2017-11-25
     */
    public void setMappointID(String mappointID) {
        this.mappointID = mappointID;
    }

    
    /**
     * Returns the x axis or the latitude of this house.
     * @return A string value containing the x axis of the house.
     * 
     * @since 2017-11-25
     */
    public String getxAxis() {
        return xAxis;
    }

    /**
     * Sets the x axis of this house.
     * @param xAxis 
     * 
     * @since 2017-11-25
     */
    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * Returns the y axis or the longitude of this house.
     * @return A string value containing the y axis of the house. 
     * 
     * @since 2017-11-25
     */
    public String getyAxis() {
        return yAxis;
    }

    
    /**
     * Sets the y axis or the longitude of this house.
     * @param yAxis 
     * 
     * @since 2017-11-25
     */
    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * Returns the first name of the homeowner.
     * @return A string value containing the first name of the homeowner. 
     * 
     * @since 2017-11-25
     */
    public String getfName() {
        return fName;
    }

    /**
     * Sets the first name of the homeowner.
     * @param fName
     * 
     * @since 2017-11-25
     */
    public void setfName(String fName) {
        this.fName = fName;
    }
    
    /**
     * Returns the last name of the homeowner.
     * @return A string value containing the last name of the homeowner.
     * 
     * @since 2017-11-25
     */
    public String getlName() {
        return lName;
    }
    
    /**
     * Sets the last name of the homeowner.
     * @param lName
     * 
     * @since 2017-11-25
     */
    public void setlName(String lName) {
        this.lName = lName;
    }
    
    
}
