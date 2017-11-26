
package model;

/**
 * Property Object
 * A <b>Property</b> object contains the different attributes of 
 * a property that a home owner possesses
 * 
 * @author ivy
 * @version 1.001
 * @since 2017-10-28
 */

/* Documentation for developer courtes y of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please notify Ivy first before updating any major parts of this code.
 *
 * 
 *
 * original code: 10-28-17 by I. Lim
 * last update: 11-25-17 by justine - added documentation
*/
public class Property {
    protected int blocknum;
    protected int lotnum;
    protected int endlotnum;
    protected Street street;
    protected PropertyStatus status;
    protected MapPoint mapppoint;
    
    public Property(int blocknum, int lotnum, int endlotnum, Street street, PropertyStatus status, MapPoint mappoint){
        this.blocknum = blocknum;
        this.lotnum = lotnum;
        this.endlotnum = endlotnum;
        this.street = street;
        this.status = status;
        this.mapppoint = mappoint;
    }

    /**
     * returns the block number of the house
     * 
     * @param nothing
     * @return int containing the block number of the house
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public int getBlocknum() {
        return blocknum;
    }

    /**
     * sets the block number of the house
     * 
     * @param blocknum
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setBlocknum(int blocknum) {
        this.blocknum = blocknum;
    }

    /**
     * returns the lot number of the house
     * 
     * @param nothing
     * @return int containing the lot number of the house
     * @throws nothing
     * 
     * @since 10-28-17
     */       
    public int getLotnum() {
        return lotnum;
    }

    /**
     * sets the lot number of the house
     * 
     * @param lotnum
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public void setLotnum(int lotnum) {
        this.lotnum = lotnum;
    }

    /**
     * returns the end lot number of the house
     * 
     * @param nothing
     * @return int containing the end lot number of the house
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public int getEndlotnum() {
        return endlotnum;
    }

    /**
     * sets the end lot number of the house
     * 
     * @param endlotnum
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setEndlotnum(int endlotnum) {
        this.endlotnum = endlotnum;
    }

    /**
     * returns the street number of the house
     * 
     * @param nothing
     * @return the street  of the house
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public Street getStreet() {
        return street;
    }

    /**
     * sets the street of the house
     * 
     * @param street
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */        
    public void setStreet(Street street) {
        this.street = street;
    }

    /**
     * returns the propert status
     * 
     * @param status
     * @return property status of the house
     * @throws nothing
     * 
     * @since 10-28-17
     */       
    public PropertyStatus getStatus() {
        return status;
    }

    /**
     * sets the property status of the house
     * 
     * @param status
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */      
    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    /**
     * returns the appoint number of the transaction
     * 
     * @param nothing
     * @return appoint number of the transaction
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public MapPoint getMapppoint() {
        return mapppoint;
    }

    /**
     * sets the mappoint id of the house
     * 
     * @param mappoint
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */     
    public void setMapppoint(MapPoint mapppoint) {
        this.mapppoint = mapppoint;
    }
    
}
