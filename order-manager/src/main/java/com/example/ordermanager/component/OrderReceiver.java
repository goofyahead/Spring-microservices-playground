package com.example.ordermanager.component;

import com.example.ordermanager.model.Order;
import com.example.ordermanager.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderReceiver {

    private final JmsTemplate jms;

    @Autowired
    public OrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }

    @JmsListener(destination = "tacocloud.order.queue")
    public void processTaco(Taco order) {
        log.info(order.toString());
    }
}
