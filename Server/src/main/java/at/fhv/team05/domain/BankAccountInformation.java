package at.fhv.team05.domain;

import javax.persistence.*;

@Entity
@Table(name = "BankAccountInformation")
public class BankAccountInformation implements IDomainObject {
    private int id;
    private String iban;
    private String bic;
    private String accountHolder;

    @Override
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "iban", nullable = false, length = 40)
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Basic
    @Column(name = "bic", nullable = false, length = 40)
    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    @Basic
    @Column(name = "accountHolder", nullable = false, length = 50)
    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAccountInformation that = (BankAccountInformation) o;

        if (id != that.id) {
            return false;
        }
        if (iban != null ? !iban.equals(that.iban) : that.iban != null) {
            return false;
        }
        if (bic != null ? !bic.equals(that.bic) : that.bic != null) {
            return false;
        }
        if (accountHolder != null ? !accountHolder.equals(that.accountHolder) : that.accountHolder != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (bic != null ? bic.hashCode() : 0);
        result = 31 * result + (accountHolder != null ? accountHolder.hashCode() : 0);
        return result;
    }
}
