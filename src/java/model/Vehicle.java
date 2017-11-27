/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Patrisha
 */
public class Vehicle {
    private String owner;
    private String plate;
    private String model;
    private String make;
    private String year;
    private int banned;
    
    public Vehicle(){
        
    }
    /**
     * Constructs a newly allocated Vehicle object that represents the String and int values indicated by the String and the int parameters.
     *
     * @param owner vehicle owner
     * @param plate vehicle plate
     * @param model vehicle model
     * @param make vehicle make
     * @param year vehicle year
     * @param banned indicator if vehicle is banned or not
     */
    public Vehicle(String owner, String plate, String model, String make, String year, int banned){
        this.owner = owner;
        this.plate = plate;
        this.model = model;
        this.make = make;
        this.year = year + "-0" + "-0";
        this.banned = banned;
    }
    /**
     * Returns the vehicle owner.
     *
     * @return a String containing the owner's name.
     */
    public String getOwner(){
        return this.owner;
    }
    /**
     * Returns the vehicle plate number.
     *
     * @return a String containing the vehicle's plate number.
     */
    public String getPlate(){
        return this.plate;
    }
    /**
     * Returns the model.
     *
     * @return an String containing the vehicle model.
     */
    public String getModel(){
        return this.model;
    }
    /**
     * Returns the vehicle make.
     *
     * @return an String containing the vehicle make.
     */
    public String getMake(){
        return this.make;
    }
    /**
     * Returns the vehicle year.
     *
     * @return an String containing the vehicle year.
     */
    public String getYear(){
        return this.year;
    }
    /**
     * Returns 1 if vehicle is banned and 0 if vehicle is not.
     *
     * @return an int representing if the vehicle is banned or not.
     */
    public int getBanned(){
        return this.banned;
    }
    /**
     * Sets the banned parameter to 1 if banned and 0 if not. 
     *
     * */
    public void setBanned(int banned){
        this.banned = banned;
    }
}
