
package model;

/**
 *  MapPoint Object
 * A <b>map point</b> object contains the attributes of a map point that are 
 * needed in order to display their place on the community map.
 * 
 * @author Jayvee Gabriel
 * @version 1.0
 * 
 * @since 2017-11-25
 * 
 */
public class MapPoint {
    
    /**
     * This is the map point ID of this map point.
     */
    protected int mappointID;
    
    /**
     * This is the x axis or the latitude of the establishment.
     */
    protected String xAxis;
    
    /**
     * This is the y axis or the longitude of the establishment.
     */
    protected String yAxis;
    
    /**
     * This is the title or name of the establishment.
     */
    protected String title;
    
    /**
     * This is a short description of the establishment.
     */
    protected String description;
    
    /**
     * This is the user ID of the user that owns the establishment.
     */
    protected String userID;
    
    /**
     * This is date the establishment or map point is created.
     */
    protected String createDate;
    
    /**
     * This is the category of the establishment. 
     * It can be stores, playgrounds, and other 
     * places in the community.
     */
    protected String mappointcategory;

    /**
     * Returns the map point ID of this MapPoint.
     * 
     * @return String containing the id of the map point.
     * 
     * @since 2017-11-25
     */
    public int getMappointID() {
        return mappointID;
    }

    /**
     * Sets the map point ID of this MapPoint.
     * 
     * @param mappointID.
     * 
     * @since 2017-11-25
     */
    public void setMappointID(int mappointID) {
        this.mappointID = mappointID;
    }

    /**
     * Returns the latitude of the location for this Map Point.
     * @return A string value containing the xAxis or the latitude of this MapPoint.
     * @since 2017-11-25
     */
    public String getxAxis() {
        return xAxis;
    }

    /**
     * Sets the xAxis of this MapPoint.
     * @param xAxis
     * @since 2017-11-25
     */
    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * Returns the longitude of the location for this Map Point.
     * @return A sting value containing the yAxis or the longitude of this MapPoint.
     * @since 2017-11-25
     */
    public String getyAxis() {
        return yAxis;
    }
    
    /**
     * Sets the yAxis of this MapPoint
     * @param yAxis 
     * @since 2017-11-25
     */
    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    
    /**
     * Returns the title or the name of the establishment located in this MapPoint.
     * @return A string value containing the title of the map point.
     * @since 2017-11-25
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the MapPoint. 
     * @param title
     * 
     * @since 2017-11-25
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description about this map point.
     * @return A string value containing a short description about this map point.
     * @since 2017-11-25
     */
    public String getDescription() {
        return description;
    }

    /**
     *Sets the description of this map point.
     * @param description
     * @since 2017-11-25
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the userID of the owner of the establishment.
     * @return A string value containing the userID of the owner of the establishment.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the userID of the owner of this establishment.
     * @param userID
     * @since 2017-11-25
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Returns the date the establishment is added.
     * @return A string value containing the create date of the MapPoint.
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date the map point is created.
     * @param createDate
     * @since 2017-11-25
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Returns the category of the map point. 
     * It can be a store, playground or other establishments 
     * found inside the community.
     * @return A string value containing the category of the map point.
     * @since 2017-11-25
     */
    public String getMappointcategory() {
        return mappointcategory;
    }
    

    /**
     *  Sets the category of this map point.
     * @param mappointcategory
     * @since 2017-11-25
     */
    public void setMappointcategory(String mappointcategory) {
        this.mappointcategory = mappointcategory;
    }
    
    
}
