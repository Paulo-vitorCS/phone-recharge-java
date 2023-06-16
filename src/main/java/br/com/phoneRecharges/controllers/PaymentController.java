package br.com.phoneRecharges.controllers;

import br.com.phoneRecharges.assemblers.PaymentModelAssembler;
import br.com.phoneRecharges.domain.Payment;
import br.com.phoneRecharges.exceptions.ClientNotFoundException;
import br.com.phoneRecharges.repositories.ClientRepository;
import jakarta.validation.constraints.Null;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import br.com.phoneRecharges.exceptions.PaymentNotFoundException;
import br.com.phoneRecharges.repositories.PaymentRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final PaymentModelAssembler assembler;
    private final ClientRepository clientRepository;

    public PaymentController(PaymentRepository paymentRepository, PaymentModelAssembler assembler, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.assembler = assembler;
        this.clientRepository = clientRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/payments")
    public CollectionModel<EntityModel<Payment>> all() {
        List<EntityModel<Payment>> payments = paymentRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(payments,
                linkTo(methodOn(PaymentController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/payments")
    ResponseEntity<?> newPayment(@RequestBody Payment newPayment) {
        if (clientRepository.findById(newPayment.getClientId()).isEmpty()) {

            throw new ClientNotFoundException(newPayment.getClientId());
        } else {

            EntityModel<Payment> entityModel = assembler.toModel(paymentRepository.save(newPayment));

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }
    }

    // Single item

    @GetMapping("/payments/{id}")
    public EntityModel<Payment> one(@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new PaymentNotFoundException(id));

        return assembler.toModel(payment);
    }

    @PutMapping("/payments/{id}")
    ResponseEntity<?> replacePayment(@RequestBody Payment newPayment, @PathVariable Long id) {
        Payment updatedPayment = paymentRepository.findById(id).map(payment -> {
            payment.setClientId(newPayment.getClientId());
            payment.setCardNumber(newPayment.getCardNumber());
            payment.setCardHolder(newPayment.getCardHolder());
            payment.setCardExpiringDate(newPayment.getCardExpiringDate());
            payment.setCvv(newPayment.getCvv());
            return paymentRepository.save(payment);
        }).orElseGet(() -> {
            newPayment.setId(id);
            return paymentRepository.save(newPayment);
        });

        EntityModel<Payment> entityModel = assembler.toModel(updatedPayment);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/payments/{id}")
    ResponseEntity<?> deletePayment(@PathVariable Long id) {
        paymentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
