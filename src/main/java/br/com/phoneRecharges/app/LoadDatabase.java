package br.com.phoneRecharges.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import br.com.phoneRecharges.domain.Client;
import br.com.phoneRecharges.repositories.ClientRepository;
import br.com.phoneRecharges.domain.Payment;
import br.com.phoneRecharges.repositories.PaymentRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, PaymentRepository paymentRepository) {
        return args -> {
            Client firstClient = new Client("João", "Costa", "461.784.230-63", "(34) 9 9999-9999");
            Client secondClient = new Client("Maria", "Silva", "733.717.170-62", "(34) 9 9999-9998");
            clientRepository.save(firstClient);
            clientRepository.save(secondClient);

            clientRepository.findAll().forEach(client -> log.info("Preloaded " + client));

            paymentRepository.save(new Payment(firstClient, "5242 1351 3541 9217", "Joao Costa", "30/11/2025", 353));
            paymentRepository.save(new Payment(secondClient, "5405 7201 1902 8424", "Maria Silva", "12/05/2027", 311));
            paymentRepository.save(new Payment(secondClient, "5405 7201 1902 8424", "Maria Silva", "12/05/2027", 311));

            paymentRepository.findAll().forEach(payment -> {
                log.info("Preloaded " + payment.getId());
            });

        };
    }

}
