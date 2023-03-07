package be.kdg.prog6.thebakery.factory.adapters.in;

import be.kdg.prog6.thebakery.common.events.EventType;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.factory.config.RabbitMQFactoryQueueConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryBakingOrderAMQPReceiver implements AMQPReceiver {

    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FactoryBakingOrderAMQPReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean apply(EventType eventType) {
        return eventType.equals(EventType.NewOrderPlacedEvent);
    }


    public void receiveMessage(JsonNode message) throws JsonProcessingException {
        log.info(">>> VIA FANOUT");
        NewOrderPlacedEvent event = objectMapper.treeToValue(message, NewOrderPlacedEvent.class);
        log.info("Received event: " + event);
    }

    @RabbitListener(queues = RabbitMQFactoryQueueConfig.BAKING_ORDER_QUEUE)
    public void receiveTopicMessage(String message) throws JsonProcessingException {
        log.info(">>> VIA TOPIC");
        System.out.println(message);
    }
}
