package br.com.phoneRecharges.exceptions;

import br.com.phoneRecharges.domain.Recharge;

public class RechargeNotFoundException extends RuntimeException {

    public RechargeNotFoundException(Long id) {
        super("Could not find recharge " + id + "\n");
    }

}
