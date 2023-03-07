package be.kdg.prog6.thebakery.store.adapters.out.publisher;

import be.kdg.prog6.thebakery.common.events.EventHeader;
import be.kdg.prog6.thebakery.common.events.EventMessage;
import be.kdg.prog6.thebakery.common.events.EventType;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.store.config.RabbitMQModuleConfig;
import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SalesOrderUpdatePublisher implements SalesOrderUpdatePort {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    @Autowired
    public SalesOrderUpdatePublisher(ApplicationEventPublisher applicationEventPublisher, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void updateSalesOrders(SalesOrder order) {
        log.info("EVENT: Order Placed.");
        Map<UUID, Integer> productQuantity = new HashMap<>();
        for (OrderItem item : order.orderContent()) {
            productQuantity.put(item.product().productUuid(), item.quantity());
        }
        applicationEventPublisher.publishEvent(new NewOrderPlacedEvent(order.customer().uuid(), productQuantity));

        // rabbitmq
        try {
            log.info("EVENT: Order Placed (Rabbit MQ).");
            rabbitTemplate.convertAndSend(RabbitMQModuleConfig.topicExchange, NewOrderPlacedEvent.ROUTING_KEY, objectMapper.writeValueAsString(new EventMessage(new EventHeader(UUID.randomUUID(), EventType.NewOrderPlacedEvent, LocalDateTime.now()),objectMapper.valueToTree(new NewOrderPlacedEvent(order.customer().uuid(), productQuantity)))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
