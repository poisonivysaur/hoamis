/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Patrisha
 */
public class Sticker implements Serializable {
    private int year;
    private double cost;
    private String date;
    
    public Sticker(){
        
    }
    public Sticker(int year, double cost){
        this.year = year;
        this.cost = cost;
        this.date = date;
    }
    public int getYear(){
        return this.year;
    }
    public double getCost(){
        return this.cost;
    }
    
}
