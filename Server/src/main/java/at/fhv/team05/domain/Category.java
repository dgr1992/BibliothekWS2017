package at.fhv.team05.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import at.fhv.team05.persistence.DBFacade;

/**
 * Created by Daniel on 27.10.2017.
 */
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int _id;

    @Column(name ="categoryIndex")
    private String _categoryIndex;

    @Column(name = "categoryName")
    private String _categoryName;

    @Column(name = "room")
    private String _room;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getCategoryIndex() {
        return _categoryIndex;
    }

    public void setCategoryIndex(String categoryIndey) {
        _categoryIndex = categoryIndey;
    }

    public String getCategoryName() {
        return _categoryName;
    }

    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;
    }

    public String getRoom() {
        return _room;
    }

    public void setRoom(String room) {
        _room = room;
    }

    public static void main(String[] args){
        DBFacade dbFacade = DBFacade.getInstance();
        List<Category> categories = dbFacade.getAllCategories();

        for (Category category: categories) {
            System.out.println(category);
        }
    }

    @Override
    public String toString(){
        return "Category name: " + _categoryName + " Index: " + _categoryIndex + " Room:" +_room;
    }
}
