package br.com.phoneRecharges.controllers;

import br.com.phoneRecharges.assemblers.RechargeModelAssembler;
import br.com.phoneRecharges.domain.Recharge;
import br.com.phoneRecharges.exceptions.ClientNotFoundException;
import br.com.phoneRecharges.exceptions.PaymentNotFoundException;
import br.com.phoneRecharges.exceptions.RechargeNotFoundException;
import br.com.phoneRecharges.repositories.ClientRepository;
import br.com.phoneRecharges.repositories.PaymentRepository;
import br.com.phoneRecharges.repositories.RechargeRepository;
import br.com.phoneRecharges.services.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RechargeController {

    private final RechargeRepository rechargeRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final RechargeModelAssembler assembler;

    @Autowired
    private RechargeService rechargeService;

    public RechargeController(RechargeRepository rechargeRepository, ClientRepository clientRepository,
                              PaymentRepository paymentRepository, RechargeModelAssembler assembler) {
        this.rechargeRepository = rechargeRepository;
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
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
        if (paymentRepository.findById(newRecharge.getPaymentId()).isEmpty()) {
            throw new PaymentNotFoundException(newRecharge.getPaymentId());
        } else if (clientRepository.findById(newRecharge.getClientId()).isEmpty()) {
            throw new ClientNotFoundException(newRecharge.getClientId());
        } else {
            EntityModel<Recharge> entityModel = assembler.toModel(rechargeService.save(newRecharge));

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }
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
            recharge.setClientId(newRecharge.getClientId());
            recharge.setPaymentId(newRecharge.getPaymentId());
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
