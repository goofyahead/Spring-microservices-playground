package com.example.tacomanager.service;

import com.example.tacomanager.config.MessageQueues;
import com.example.tacomanager.models.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class TacoMessagingService {

    private JmsTemplate jms;

    @Autowired
    public TacoMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    public void sendTaco(Taco taco) {
        jms.convertAndSend(MessageQueues.ORDER_QUEUE, taco, this::setWebOrigin);
    }

    private Message setWebOrigin(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
