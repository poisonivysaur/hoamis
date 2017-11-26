
package model;

import java.io.Serializable;

/**
 * ElectType Object
 * A <b>ElectType</b> object contains the different attributes of 
 * a user of the system ...
 * 
 * @author justine
 * @version 1.001
 * @since 2017-10-27
 */

/* Documentation for developer courtesy of Ivy Lim
 * COMMENTS (please comment any concerns and attach your names thanks!):
 * Please seek Justine first before updating any major parts of this code. -Ivy
 * 
 *
 * 
 *
 * original code: 10-27-17
 * last update:
*/
public class ElectType implements Serializable {
    
    protected int electTypeID;
    protected String electType;
	
	public ElectType(){}
    
    public ElectType(String electType) {
        this.electType = electType;
    }
	
	public int getElectTypeID(){return this.electTypeID;}
	public String getElectType(){return this.electType;}
	public void setElectTypeID(int electTypeID){this.electTypeID = electTypeID;}
	public void setElectType(String electType){this.electType = electType;}
}
