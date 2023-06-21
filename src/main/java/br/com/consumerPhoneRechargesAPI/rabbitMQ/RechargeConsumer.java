package br.com.consumerPhoneRechargesAPI.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RechargeConsumer {

    @RabbitListener(queues = RechargeAMQPConfig.QUEUE)
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
        return null;
    }

}
