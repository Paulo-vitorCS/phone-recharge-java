package br.com.phoneRecharges.domain;

import br.com.phoneRecharges.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Recharges")
public class Recharge {

    private @Id @GeneratedValue Long id;

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private Long paymentId;

    private double rechargeValue;
    private Date rechargeDate;
    private Status status;

    public Recharge() {}

    public Recharge(Long clientId, Long paymentId, double rechargeValue) throws ParseException {
        this.clientId = clientId;
        this.paymentId = paymentId;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
        return Objects.equals(this.id, recharge.id) && Objects.equals(this.clientId, recharge.clientId) &&
                Objects.equals(this.paymentId, recharge.paymentId) && Objects.equals(this.rechargeValue, recharge.rechargeValue) &&
                Objects.equals(this.rechargeDate, recharge.rechargeDate) && Objects.equals(this.status, recharge.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientId, this.paymentId,
                this.rechargeValue, this.rechargeDate, this.status);
    }

    @Override
    public String toString() {
        return "Recharge: {id: " + this.id + ", clientId: " + this.clientId +
                ", paymentId: " + this.paymentId + "}";
    }

}
