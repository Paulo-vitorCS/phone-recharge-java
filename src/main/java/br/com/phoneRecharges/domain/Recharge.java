package br.com.phoneRecharges.domain;

import br.com.phoneRecharges.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Recharges")
public class Recharge {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="payment_id", nullable = false)
    private Payment payment;

    private double rechargeValue;
    private Date rechargeDate;
    private Status status;

    public Recharge() {}

    public Recharge(Client client, Payment payment, double rechargeValue) throws ParseException {
        this.client = client;
        this.payment = payment;
        this.rechargeValue = rechargeValue;
        this.rechargeDate = new Date();
        this.status = Status.WAITING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double getRechargeValue() {
        return rechargeValue;
    }

    public void setRechargeValue(double rechargeValue) {
        this.rechargeValue = rechargeValue;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Recharge))
            return false;

        Recharge recharge = (Recharge) o;
        return Objects.equals(this.id, recharge.id) && Objects.equals(this.client, recharge.client) &&
                Objects.equals(this.payment, recharge.payment) && Objects.equals(this.rechargeValue, recharge.rechargeValue) &&
                Objects.equals(this.rechargeDate, recharge.rechargeDate) && Objects.equals(this.status, recharge.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.client, this.payment,
                this.rechargeValue, this.rechargeDate, this.status);
    }

    @Override
    public String toString() {
        return "Recharge: {id: " + this.id + ", client: " + this.client.getName() +
                ", Phone Number: " + this.client.getPhoneNumber() + "}";
    }

}
