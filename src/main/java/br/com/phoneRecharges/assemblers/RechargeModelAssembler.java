package br.com.phoneRecharges.assemblers;

import br.com.phoneRecharges.controllers.RechargeController;
import br.com.phoneRecharges.domain.Recharge;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RechargeModelAssembler implements RepresentationModelAssembler<Recharge, EntityModel<Recharge>> {

    @Override
    public EntityModel<Recharge> toModel(Recharge recharge) {
        return EntityModel.of(recharge,
                linkTo(methodOn(RechargeController.class).one(recharge.getId())).withSelfRel(),
                linkTo(methodOn(RechargeController.class).all()).withRel("recharges"));
    }

}
