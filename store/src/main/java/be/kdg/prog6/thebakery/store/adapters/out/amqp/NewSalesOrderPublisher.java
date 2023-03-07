package be.kdg.prog6.thebakery.store.adapters.out.amqp;

import be.kdg.prog6.thebakery.common.events.EventHeader;
import be.kdg.prog6.thebakery.common.events.EventMessage;
import be.kdg.prog6.thebakery.common.events.EventType;
import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.store.config.RabbitMQModuleConfig;
import be.kdg.prog6.thebakery.store.domain.Customer;
import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import be.kdg.prog6.thebakery.store.ports.out.NewSalesOrderPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class NewSalesOrderPublisher implements NewSalesOrderPort {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;


    public NewSalesOrderPublisher(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    private void newSalesOrder(Customer.CustomerUUID customerUUID, SalesOrder salesOrder) throws JsonProcessingException {
        Map<UUID, Integer> productQuantity = new HashMap<>();
        for (OrderItem item : salesOrder.orderContent()) {
            productQuantity.put(item.product().productUuid(), item.quantity());
        }
        // TOPIC WAY
        rabbitTemplate.convertAndSend(RabbitMQModuleConfig.topicExchange, NewOrderPlacedEvent.ROUTING_KEY, objectMapper.writeValueAsString(new EventMessage(new EventHeader(UUID.randomUUID(), EventType.NewOrderPlacedEvent, LocalDateTime.now()),objectMapper.valueToTree(new NewOrderPlacedEvent(customerUUID.uuid(), productQuantity)))));
    }

    @Override
    public void newSalesOrder(SalesOrder salesOrder) {
        try {
            this.newSalesOrder(salesOrder.customer(), salesOrder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
