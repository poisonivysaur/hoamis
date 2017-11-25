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
    public Vehicle(String owner, String plate, String model, String make, String year, int banned){
        this.owner = owner;
        this.plate = plate;
        this.model = model;
        this.make = make;
        this.year = year + "-0" + "-0";
        this.banned = banned;
    }
    public String getOwner(){
        return this.owner;
    }
    public String getPlate(){
        return this.plate;
    }
    public String getModel(){
        return this.model;
    }
    public String getMake(){
        return this.make;
    }
    public String getYear(){
        return this.year;
    }
    public int getBanned(){
        return this.banned;
    }
    public void setBanned(int banned){
        this.banned = banned;
    }
}
