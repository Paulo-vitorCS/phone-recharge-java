package clientsRegistration;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
class ClientController {

    private final ClientRepository repository;

    ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/clients")
    List<Client> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/clients")
    Client newClient(@RequestBody Client newClient) {
        return repository.save(newClient);
    }

    // Single item

    @GetMapping("/clients/{id}")
    Client one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @PutMapping("/clients/{id}")
    Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
        return repository.findById(id).map(client -> {
            client.setFirstName(newClient.getFirstName());
            client.setLastName(newClient.getLastName());
            client.setCpf(newClient.getCpf());
            client.setPhoneNumber(newClient.getPhoneNumber());
            return repository.save(client);
        }).orElseGet(() -> {
            newClient.setId(id);
            return repository.save(newClient);
        });
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
