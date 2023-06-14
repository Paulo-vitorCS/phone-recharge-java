package phoneRechargesAPI.clientsRegistration;

class ClientNotFoundException extends RuntimeException {

    ClientNotFoundException(Long id) {
        super("Could not find client " + id + "\n");
    }

}
