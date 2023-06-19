package br.com.phoneRecharges.rabbitMQConfig;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final AmqpTemplate queueSender;

    public TestController(AmqpTemplate queueSender) {
        this.queueSender = queueSender;
    }

    @GetMapping
    public String send() {
        String msg = "test message";

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("last", "yes");

        Message message = new Message(msg.getBytes(), messageProperties);


        queueSender.convertAndSend("test", "routing-key-test", message);
        return "Ok. Done!";
    }

}
