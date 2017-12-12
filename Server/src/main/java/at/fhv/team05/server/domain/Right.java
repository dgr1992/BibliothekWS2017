package at.fhv.team05.server.domain;

import javax.persistence.*;

@Entity
@Table(name = "UserRight")
public class Right implements IDomainObject {
    private int id;
    private String name;

    @Override
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Right right = (Right) o;

        if (id != right.id) {
            return false;
        }
        if (name != null ? !name.equals(right.name) : right.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
