package br.com.phoneRecharges.exceptions;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(Long id) {
        super("Could not find payment " + id + "\n");
    }

}
