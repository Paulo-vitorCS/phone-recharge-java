package phoneRechargesAPI.paymentRecord;

public class PaymentNotFoundException extends RuntimeException{

    PaymentNotFoundException(Long id) {
        super("Could not find payment " + id + "\n");
    }

}
