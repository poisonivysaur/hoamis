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
public class SecurityPersonnel implements Serializable {
    
    private String userID;
    
    public SecurityPersonnel (String userID){
        
           this.userID = userID;
    
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    
}
