
package model;

/**
 * Street Object
 * A <b>Street</b> object contains the street name of the streets in Saint Mary's Homes community
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
 * last update: 11-25-17 by justine - added documentation
*/
public class Street {
    public String street;
    
    public Street(String st){
        this.street = st;
    }

    /**
     * returns the street of the property
     * 
     * @param nothing
     * @return String containing the street of the property
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public String getStreet() {
        return street;
    }

    /**
     * sets the street of the property
     * 
     * @param street
     * @return nothing
     * @throws nothing
     * 
     * @since 10-28-17
     */    
    public void setStreet(String street) {
        this.street = street;
    }
    
}
