package br.com.phoneRecharges.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RechargeAMQPConfig {

    public static final String EXCHANGE_NAME = "Recharges";
    public static final String QUEUE_RECHARGE = "recharge-created";
    public static final String ROUTING_KEY = "recharge-route";

    @Bean
    public Queue declareRechargeQueue() {
            return QueueBuilder.durable(QUEUE_RECHARGE)
                    .build();
    }

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }

}
