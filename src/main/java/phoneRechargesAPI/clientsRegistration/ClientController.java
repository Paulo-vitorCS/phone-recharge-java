package phoneRechargesAPI.clientsRegistration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class ClientController {

    private final ClientRepository repository;
    private final ClientModelAssembler assembler;

    ClientController(ClientRepository repository, ClientModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/clients")
    CollectionModel<EntityModel<Client>> all() {
        List<EntityModel<Client>> clients = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/clients")
    ResponseEntity<?> newClient(@RequestBody Client newClient) {
        EntityModel<Client> entityModel = assembler.toModel(repository.save(newClient));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Single item

    @GetMapping("/clients/{id}")
    EntityModel<Client> one(@PathVariable Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

        return assembler.toModel(client);
    }

    @PutMapping("/clients/{id}")
    ResponseEntity<?> replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
        Client updatedClient = repository.findById(id).map(client -> {
            client.setName(newClient.getName());
            client.setCpf(newClient.getCpf());
            client.setPhoneNumber(newClient.getPhoneNumber());
            return repository.save(client);
        }).orElseGet(() -> {
            newClient.setId(id);
            return repository.save(newClient);
        });

        EntityModel<Client> entityModel = assembler.toModel(updatedClient);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/clients/{id}")
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
