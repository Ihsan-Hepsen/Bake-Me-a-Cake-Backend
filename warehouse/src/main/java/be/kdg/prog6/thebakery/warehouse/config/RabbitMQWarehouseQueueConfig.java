package be.kdg.prog6.thebakery.warehouse.config;

import be.kdg.prog6.thebakery.common.events.IngredientListIsSentEvent;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQWarehouseQueueConfig {

    public static final String OUTBOUND_ORDER_QUEUE = "outboundorder-topicqueue";


    @Bean
    Queue ingredientListQueue() {
        return QueueBuilder.nonDurable(OUTBOUND_ORDER_QUEUE).build();
    }

    @Bean
    Binding ingredientListBinding(Queue ingredientListQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(ingredientListQueue).to(topicExchange).with(IngredientListIsSentEvent.ROUTING_KEY);
    }
}
