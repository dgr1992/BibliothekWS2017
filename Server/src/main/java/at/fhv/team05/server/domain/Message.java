package at.fhv.team05.server.domain;

import at.fhv.team05.library.ObjectInterfaces.IMessage;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message implements IDomainObject, IMessage {
    private int id;
    private String message;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Basic
    @Column(name = "message", nullable = false, length = 5000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message that = (Message) o;
        if (id != that.id) {
            return false;
        }

        if (!message.equals(that.message)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
