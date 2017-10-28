
package model;

/**
 * Property Object
 * A <b>Property<b> object contains the different attributes of 
 * a property that a home owner possesses
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
 * last update:
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

    public int getBlocknum() {
        return blocknum;
    }

    public void setBlocknum(int blocknum) {
        this.blocknum = blocknum;
    }

    public int getLotnum() {
        return lotnum;
    }

    public void setLotnum(int lotnum) {
        this.lotnum = lotnum;
    }

    public int getEndlotnum() {
        return endlotnum;
    }

    public void setEndlotnum(int endlotnum) {
        this.endlotnum = endlotnum;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public MapPoint getMapppoint() {
        return mapppoint;
    }

    public void setMapppoint(MapPoint mapppoint) {
        this.mapppoint = mapppoint;
    }
    
}
