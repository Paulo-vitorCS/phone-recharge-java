package br.com.phoneRecharges.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import br.com.phoneRecharges.controllers.PaymentController;
import br.com.phoneRecharges.domain.Payment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<Payment, EntityModel<Payment>> {

    @Override
    public EntityModel<Payment> toModel(Payment payment) {

        return EntityModel.of(payment,
                linkTo(methodOn(PaymentController.class).one(payment.getId())).withSelfRel(),
                linkTo(methodOn(PaymentController.class).all()).withRel("payments"));

    }
}
