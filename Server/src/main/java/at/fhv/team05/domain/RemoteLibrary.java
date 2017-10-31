package at.fhv.team05.domain;

import javax.persistence.*;

@Entity
@Table(name = "RemoteLibrary")
public class RemoteLibrary {
    private int id;
    private String libraryName;
    private String contactPerson;
    private int bankaccountId;
    private int addressId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "libraryName", nullable = false, length = 20)
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Basic
    @Column(name = "contactPerson", nullable = false, length = 50)
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Basic
    @Column(name = "bankaccountId", nullable = false)
    public int getBankaccountId() {
        return bankaccountId;
    }

    public void setBankaccountId(int bankaccountId) {
        this.bankaccountId = bankaccountId;
    }

    @Basic
    @Column(name = "addressId", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RemoteLibrary that = (RemoteLibrary) o;

        if (id != that.id) {
            return false;
        }
        if (bankaccountId != that.bankaccountId) {
            return false;
        }
        if (addressId != that.addressId) {
            return false;
        }
        if (libraryName != null ? !libraryName.equals(that.libraryName) : that.libraryName != null) {
            return false;
        }
        if (contactPerson != null ? !contactPerson.equals(that.contactPerson) : that.contactPerson != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (libraryName != null ? libraryName.hashCode() : 0);
        result = 31 * result + (contactPerson != null ? contactPerson.hashCode() : 0);
        result = 31 * result + bankaccountId;
        result = 31 * result + addressId;
        return result;
    }
}
