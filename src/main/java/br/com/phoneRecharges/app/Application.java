package br.com.phoneRecharges.app;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableRabbit
@SpringBootApplication
@ComponentScan("br.com.phoneRecharges")
@EntityScan("br.com.phoneRecharges.domain")
@EnableJpaRepositories("br.com.phoneRecharges.repositories")
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

}
