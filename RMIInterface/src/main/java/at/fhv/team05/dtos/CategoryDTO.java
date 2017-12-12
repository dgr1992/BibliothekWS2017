package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.ICategory;

import java.io.Serializable;

public class CategoryDTO implements Serializable{

    private int id;
    private String categoryIndex;
    private String categoryName;
    private String room;

    /**
     * Default constructor for converting JSON into an Object
     */
    public CategoryDTO() {}

    public CategoryDTO(ICategory category) {

        id = category.getId();
        categoryIndex = category.getCategoryIndex();
        categoryName = category.getCategoryName();
        room = category.getRoom();

    }

    public int getId() {
        return id;
    }

    public String getCategoryIndex() {
        return categoryIndex;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return categoryIndex + " " + room;
    }
}
