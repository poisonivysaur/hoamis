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
public class Sticker {
    private int year;
    private double cost;
    private String date;
    
    public Sticker(){
        
    }
    /**
     * Constructs a newly allocated Sticker object that represents the int and the double values indicated by the int and the double parameters.
     *
     * @param year sticker year
     * @param cost sticker cost
     
     */
    
    public Sticker(int year, double cost){
        this.year = year;
        this.cost = cost;
        this.date = date;
    }
    /**
     * Returns the sticker year.
     *
     * @return an int containing the sticker year.
     */
    public int getYear(){
      
        return this.year;
    }
    /**
     * Returns the sticker cost.
     *
     * @return a double containing the sticker cost.
     */
    public double getCost(){
        return this.cost;
    }
    
}
