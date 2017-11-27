/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;

/**
 *
 * @author Jennifer
 */
public class Vehicles implements Serializable {
    
    private String platenum;
    private String model;
    private String make;
    private String year;
    private boolean banned;
    
    public Vehicles (String platenum, String model, String make, String year, boolean banned){
        
        this.platenum = platenum;
        this.model = model;
        this.make = make;
        this.year = year;
        this.banned = banned;
        
        
    }

    public void setPlatenum(String platenum) {
        this.platenum = platenum;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getPlatenum() {
        return platenum;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getYear() {
        return year;
    }

    public boolean isBanned() {
        return banned;
    }
    
    
    
}
