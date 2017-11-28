package Objects;

/**
 * MapPointCategory Object
 * A <b>map point category</b> object contains the id and name of the property.
 * 
 * @author Jayvee Gabriel
 * @since 2017-11-25
 */
public class MapPointCategory {
    
    /**
     * The id of this map point category.
     */
    protected int id;
    
    /**
     * The name of this category.
     */
    protected String name;

    
    /**
     * Returns a string that serves as the id of this map point category.
     * @return A string value containing the id of the category.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of this map point category.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the category.
     * @return A string value containing the name of this map point category.
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Sets the name of the map point category.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
