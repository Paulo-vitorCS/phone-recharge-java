package clientsRegistration;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Client {

    private @Id @GeneratedValue Long id;
    private String firstName, lastName;
    private String cpf;
    private String phoneNumber;

    Client() {}

    public Client(String firstName, String lastName, String cpf, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Client))
            return false;

        Client client = (Client) o;
        return Objects.equals(this.id, client.id) && Objects.equals(this.firstName, client.firstName) &&
                Objects.equals(this.lastName, client.lastName) && Objects.equals(this.cpf, client.cpf) &&
                Objects.equals(this.phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.cpf, this.phoneNumber);
    }

    @Override
    public String toString() {
        return "Client {" + "id: " + this.id + ", First Name: " + this.firstName + ", Last Name: " + this.lastName +
                ", CPF: " + this.cpf + ", Phone Number: " + this.phoneNumber + "}";
    }

}
