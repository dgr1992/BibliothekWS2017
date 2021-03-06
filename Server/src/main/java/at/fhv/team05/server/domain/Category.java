package at.fhv.team05.server.domain;


import at.fhv.team05.library.ObjectInterfaces.ICategory;
import at.fhv.team05.server.persistence.RepositoryFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 27.10.2017.
 */
@Entity
@Table(name = "Category")
public class Category implements IDomainObject, ICategory, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int _id;

    @Column(name = "categoryIndex")
    private String _categoryIndex;

    @Column(name = "categoryName")
    private String _categoryName;

    @Column(name = "room")
    private String _room;

    @Override
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    @Override
    public String getCategoryIndex() {
        return _categoryIndex;
    }

    public void setCategoryIndex(String categoryIndey) {
        _categoryIndex = categoryIndey;
    }

    @Override
    public String getCategoryName() {
        return _categoryName;
    }

    public void setCategoryName(String categoryName) {
        _categoryName = categoryName;
    }

    @Override
    public String getRoom() {
        return _room;
    }

    public void setRoom(String room) {
        _room = room;
    }

    public static void main(String[] args) {
        List<Category> categories = RepositoryFactory.getRepositoryInstance(Category.class).list();

        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Override
    public String toString() {
        return "Category name: " + _categoryName + " Index: " + _categoryIndex + " Room:" + _room;
    }
}
