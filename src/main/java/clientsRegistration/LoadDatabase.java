package clientsRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ClientRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Client("João", "Costa", "461.784.230-63", "(34) 9 9999-9999")));
            log.info("Preloading " + repository.save(new Client("Maria", "Silva", "733.717.170-62", "(34) 9 9999-9998")));
        };
    }

}
