package at.fhv.team05.server.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UserRole")
public class UserRole implements IDomainObject {
    private int id;
    private String role;
    private Set<Right> rights;

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
    @Column(name = "role", nullable = false, length = 20)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roleToRight",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "rightId"))
    public Set<Right> getRights() {
        return rights;
    }

    public void setRights(Set<Right> rights) {
        this.rights = rights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole that = (UserRole) o;

        if (id != that.id) {
            return false;
        }
        if (role != null ? !role.equals(that.role) : that.role != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
