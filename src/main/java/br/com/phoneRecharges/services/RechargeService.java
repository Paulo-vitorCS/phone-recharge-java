package br.com.phoneRecharges.services;

import br.com.phoneRecharges.domain.Recharge;
import br.com.phoneRecharges.enums.Status;
import br.com.phoneRecharges.rabbitMQ.RechargeAMQPConfig;
import br.com.phoneRecharges.repositories.RechargeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void updateRechargeStatus(Long id, String status) {
        Status rechargeStatus;
        if (status.equals("APPROVED")) {
            rechargeStatus = Status.APPROVED;
        } else {
            rechargeStatus = Status.REFUSED;
        }
        rechargeRepository.findById(id).map(recharge -> {
            recharge.setStatus(rechargeStatus);
            return rechargeRepository.save(recharge);
        });
    }

    public Recharge save(Recharge recharge) {
        Recharge rechargeSaved = rechargeRepository.save(recharge);
        sendRechargeToRabbit(rechargeSaved);
        return rechargeSaved;
    }

    //@Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendRechargeToRabbit(Recharge recharge) {
        try {
            String json = new ObjectMapper().writeValueAsString(recharge);
            String response = (String)rabbitTemplate.convertSendAndReceive(RechargeAMQPConfig.EXCHANGE_NAME, RechargeAMQPConfig.ROUTING_KEY, json);

            System.out.println("Recharge sent. Waiting for approval...");
            System.out.println("Response: " + response);
            if (response != null) {
                String array[] = new String[2];
                array = response.split(";");
                Long id = Long.parseLong(array[0]);
                updateRechargeStatus(id, array[1]);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
