package br.com.phoneRecharges.rabbitMQ;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RechargeAMQPConfig {

    public static final String EXCHANGE_NAME = "Recharges";
    public static final String QUEUE = "recharge-created";
    public static final String ROUTING_KEY = "recharge-route";

    @Bean
    public Queue declareRechargeQueue() {
            return QueueBuilder.durable(QUEUE)
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
