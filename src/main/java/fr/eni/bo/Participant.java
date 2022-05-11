package fr.eni.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;

    public Participant() {
    }

    public Participant(long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return firstName +" "+ lastName + " [id=" + id + "]";
    }
}
