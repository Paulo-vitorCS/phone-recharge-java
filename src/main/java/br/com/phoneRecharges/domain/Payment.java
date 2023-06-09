package br.com.phoneRecharges.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="PAYMENTS")
public class Payment {

    private @Id @GeneratedValue Long id;

    @Column(nullable = false)
    private Long clientId = null;
    private String cardNumber;
    private String cardHolder;
    private Date cardExpiringDate;
    private int cvv;

    public Payment() {}

    public Payment(Long clientId, String cardNumber, String cardHolder, String cardExpiringDate, int cvv) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.clientId = clientId;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cardExpiringDate = formatter.parse(cardExpiringDate);
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId () {
        return clientId;
    }

    public void setClientId(Long ClientId) {
        this.clientId = ClientId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getCardExpiringDate() {
        return cardExpiringDate;
    }

    public void setCardExpiringDate(Date cardExpiringDate) {
        this.cardExpiringDate = cardExpiringDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Payment))
            return false;

        Payment payment = (Payment) o;
        return Objects.equals(this.id, payment.id) && Objects.equals(this.clientId, payment.clientId) &&
                Objects.equals(this.cardNumber, payment.cardNumber) && Objects.equals(this.cardHolder, payment.cardHolder) &&
                Objects.equals(this.cardExpiringDate, payment.cardExpiringDate) && Objects.equals(this.cvv, payment.cvv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientId, this.cardNumber,
                this.cardHolder, this.cardExpiringDate, this.cvv);
    }

    @Override
    public String toString() {
        return "Payment: {id: " + this.id + ", clientId: " + this.clientId + "}";
    }

}
