package br.com.phoneRecharges.app;

import br.com.phoneRecharges.domain.Recharge;
import br.com.phoneRecharges.enums.Status;
import br.com.phoneRecharges.repositories.RechargeRepository;
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
    CommandLineRunner initDatabase(ClientRepository clientRepository, PaymentRepository paymentRepository, RechargeRepository rechargeRepository) {
        return args -> {
            Client firstClient = new Client("João", "Costa", "461.784.230-63", "(34) 9 9999-9999");
            Client secondClient = new Client("Maria", "Silva", "733.717.170-62", "(34) 9 9999-9998");
            clientRepository.save(firstClient);
            clientRepository.save(secondClient);

            clientRepository.findAll().forEach(client -> log.info("Preloaded " + client));

            Payment firstPayment = new Payment(firstClient.getId(), "5242 1351 3541 9217", "Joao Costa", "30/11/2025", 353);
            Payment sndPayment = new Payment(secondClient.getId(), "5405 7201 1902 8424", "Maria Silva", "12/05/2027", 311);
            Payment trdPayment = new Payment(secondClient.getId(), "5405 7201 1902 8424", "Maria Silva", "12/05/2027", 311);

            paymentRepository.save(firstPayment);
            paymentRepository.save(sndPayment);
            paymentRepository.save(trdPayment);

            paymentRepository.findAll().forEach(payment -> {
                log.info("Preloaded " + payment.getId());
            });

            rechargeRepository.save(new Recharge(firstClient, firstPayment, 40.00, "14/06/2023 11:36:07", Status.APPROVED));
            rechargeRepository.save(new Recharge(secondClient, sndPayment, 30.00, "14/06/2023 13:23:18", Status.REFUSED));
            rechargeRepository.save(new Recharge(secondClient, trdPayment, 20.00, "14/06/2023 13:29:24", Status.APPROVED));

            rechargeRepository.findAll().forEach(recharge -> {
                log.info("Preloaded " + recharge);
            });

        };
    }

}
