/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * This class Represents the Pet Object. Typically the Homeowners owns them
 * @author Yuta
 */
public class Pet implements Serializable {
    
    public static String ANIMALTYPE1 = "Dog";
    public static String ANIMALTYPE2 = "Cat";
    
    private String petName;
    private int animalType;
    private boolean isVaccinated;
    private Document photo;
    
    /**
     * Empty constructor for easy set of Attribute
     */
    public Pet(){}
    
    /**
     * Complete Constructor.<br />
     * This constructor will complete the attributes of Pet object.
     * @param petName
     * @param animalType
     * @param isVaccinated
     * @param photo 
     */
    public Pet(String petName, int animalType, boolean isVaccinated, Document photo){
        this.petName = petName;
        this.animalType = animalType;
        this.isVaccinated = isVaccinated;
        this.photo = photo;
    }
    
    /**
     * Pet model Constructor without photo document.
     * @param petName
     * @param animalType
     * @param isVaccinated 
     */
    public Pet(String petName, int animalType, boolean isVaccinated){
        this.petName = petName;
        this.animalType = animalType;
        this.isVaccinated = isVaccinated;
    }

    /**
     * Returns the pet name.
     * 
     * @return Pet Name
     */
    public String getPetName() {
        return petName;
    }

    /**
     * Sets the Pet Name.
     * @param petName 
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * Gets the animal type.
     * @return animal type int
     */
    public int getAnimalType() {
        return animalType;
    }

    /**
     * Gets the animal type.<br />
     * Use getAnimalTypeString to get the String value.
     * @param animalType 
     */
    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    /**
     * Checks if the pet is vaccinated.
     * @return boolean
     */
    public boolean isVaccinated() {
        return isVaccinated;
    }

    /**
     * Sets the pet's vaccination
     * @param isVaccinated 
     */
    public void setIsVaccinated(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    /**
     * Gets the pet's picture
     * @return Document Object that is a picture
     */
    public Document getPhoto() {
        return photo;
    }

    /**
     * Sets the Pet's pciture.
     * @param photo 
     */
    public void setPhoto(Document photo) {
        this.photo = photo;
    }
    
    /**
     * Returns the String equivalent of the animal type.
     * @return 
     */
    public String getAnimalTypeString(){
        String s = "";
        switch(this.animalType){
            case 1:
                s = ANIMALTYPE1;
                break;
            case 2:
                s = ANIMALTYPE2;
                break;
        }
        return s;
    }
    
}
