package br.com.phoneRecharges.controllers;

import br.com.phoneRecharges.assemblers.RechargeModelAssembler;
import br.com.phoneRecharges.domain.Recharge;
import br.com.phoneRecharges.exceptions.PaymentNotFoundException;
import br.com.phoneRecharges.exceptions.RechargeNotFoundException;
import br.com.phoneRecharges.repositories.RechargeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RechargeController {

    private final RechargeRepository rechargeRepository;
    private final RechargeModelAssembler assembler;

    public RechargeController(RechargeRepository rechargeRepository, RechargeModelAssembler assembler) {
        this.rechargeRepository = rechargeRepository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/recharges")
    public CollectionModel<EntityModel<Recharge>> all() {
        List<EntityModel<Recharge>> recharges = rechargeRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(recharges,
                linkTo(methodOn(PaymentController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/recharges")
    ResponseEntity<?> newRecharge(@RequestBody Recharge newRecharge) {
        EntityModel<Recharge> entityModel = assembler.toModel(rechargeRepository.save(newRecharge));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Single item

    @GetMapping("/recharges/{id}")
    public EntityModel<Recharge> one(@PathVariable Long id) {
        Recharge recharge = rechargeRepository.findById(id).orElseThrow(() -> new RechargeNotFoundException(id));

        return assembler.toModel(recharge);
    }

    @PutMapping("/recharges/{id}")
    ResponseEntity<?> replaceRecharge(@RequestBody Recharge newRecharge, @PathVariable Long id) {
        Recharge updatedRecharge = rechargeRepository.findById(id).map(recharge -> {
            recharge.setClient(newRecharge.getClient());
            recharge.setPayment(newRecharge.getPayment());
            recharge.setRechargeValue(newRecharge.getRechargeValue());
            recharge.setRechargeDate(newRecharge.getRechargeDate());
            recharge.setStatus(newRecharge.getStatus());

            return rechargeRepository.save(recharge);
        }).orElseGet(() -> {
            newRecharge.setId(id);
            return rechargeRepository.save(newRecharge);
        });

        EntityModel<Recharge> entityModel = assembler.toModel(updatedRecharge);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/recharges/{id}")
    ResponseEntity<?> deleteRecharges(@PathVariable Long id) {
        rechargeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
