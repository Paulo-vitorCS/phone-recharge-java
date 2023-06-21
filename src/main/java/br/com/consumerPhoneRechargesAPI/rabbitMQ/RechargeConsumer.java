package br.com.consumerPhoneRechargesAPI.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RechargeConsumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RechargeAMQPConfig.QUEUE_RECHARGE)
    public void consumer(Message message) {
        simpMessagingTemplate.convertAndSend(RechargeWebSocketConfiguration.BROKER,
                new String(message.getBody()));
    }

    @RabbitListener(queues = RechargeAMQPConfig.QUEUE_RECHARGE)
    public String receive(@Payload String fileBody) {
        System.out.println("Message: " + fileBody);
        try {
            JSONObject json = new JSONObject(fileBody);
            String content = json.getString("id") + ";" + "APPROVED";
            System.out.println("Recharge approved!");
            return content;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Failed!";
    }

}
