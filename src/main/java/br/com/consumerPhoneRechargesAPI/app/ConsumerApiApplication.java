package br.com.consumerPhoneRechargesAPI.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.Collections;

@SpringBootApplication
@ComponentScan("br.com.consumerPhoneRechargesAPI")
public class ConsumerApiApplication {

    public static void main(String... args) {
        SpringApplication app = new SpringApplication(ConsumerApiApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8050"));
        app.run(args);
    }

}
