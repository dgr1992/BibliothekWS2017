package at.fhv.team05.server.domain;

import at.fhv.team05.library.ObjectInterfaces.IUserAccount;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements IDomainObject, IUserAccount {
    private int id;
    private String username;
    private String email;
    private Set<UserRole> roles;
    /**
     * organizational unit
     */
    private String ou;


    @Id
    @Column(name = "id", nullable = false)
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Basic
    @Column(name = "email", length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserToRole", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Basic
    @Column(name = "organizationalUnit", nullable = false, length = 50)
    public String getOu() {
        return ou;
    }

    public void setOu(String ou) {
        this.ou = ou;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAccount that = (UserAccount) o;

        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        if (email != null ? !email.equalsIgnoreCase(that.email) : that.email != null) {
            return false;
        }
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) {
            return false;
        }
        return ou != null ? ou.equals(that.ou) : that.ou == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        result = 31 * result + (ou != null ? ou.hashCode() : 0);
        return result;
    }
}
