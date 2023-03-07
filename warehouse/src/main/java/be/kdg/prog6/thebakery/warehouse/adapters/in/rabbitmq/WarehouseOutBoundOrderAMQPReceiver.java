package be.kdg.prog6.thebakery.warehouse.adapters.in.rabbitmq;

import be.kdg.prog6.thebakery.common.events.EventType;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.warehouse.config.RabbitMQWarehouseQueueConfig;
import be.kdg.prog6.thebakery.warehouse.ports.in.CheckNewOutBoundOrderUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseOutBoundOrderAMQPReceiver implements AMQPReceiver {

    private final CheckNewOutBoundOrderUseCase checkNewOutBoundOrderUseCase;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WarehouseOutBoundOrderAMQPReceiver(CheckNewOutBoundOrderUseCase checkNewOutBoundOrderUseCase, ObjectMapper objectMapper) {
        this.checkNewOutBoundOrderUseCase = checkNewOutBoundOrderUseCase;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean apply(EventType eventType) {
        return eventType.equals(EventType.IngredientListIsSentEvent);
    }

    @Override
    public void receiveMessage(JsonNode message) throws JsonProcessingException {
        NewOrderPlacedEvent event = objectMapper.treeToValue(message, NewOrderPlacedEvent.class);
        log.info("Received event: " + event);
    }

    @RabbitListener(queues = RabbitMQWarehouseQueueConfig.OUTBOUND_ORDER_QUEUE)
    public void receiveTopicMessage(String message) {
        checkNewOutBoundOrderUseCase.handle(message);
    }
}
