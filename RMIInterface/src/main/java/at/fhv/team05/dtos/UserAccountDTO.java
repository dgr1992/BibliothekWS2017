package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IUserAccount;

import java.io.Serializable;

public class UserAccountDTO implements IUserAccount, Serializable {
    private int id;
    private String username;
    private String email;
    //bruchma des?
//    private Set<UserRoleDTO> roles;
    private String password;

    public UserAccountDTO(IUserAccount object) {
        id = object.getId();
        username = object.getUsername();
        email = object.getEmail();
    }

    public UserAccountDTO() {

    }


    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        password = pw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAccountDTO that = (UserAccountDTO) o;

        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        return result;
    }
}
