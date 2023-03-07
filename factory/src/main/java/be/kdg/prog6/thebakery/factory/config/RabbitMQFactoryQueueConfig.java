package be.kdg.prog6.thebakery.factory.config;

import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFactoryQueueConfig {

    public static final String FANOUT_QUEUE = "factory-fanoutqueue";
    public static final String BAKING_ORDER_QUEUE = "bakingorder-topicqueue";
    public static final String DLQ_QUEUE = "dlq-directqueue";



    @Bean
    Queue fanoutQueue() {
        return QueueBuilder.nonDurable(FANOUT_QUEUE).build();
    }


    @Bean
    Queue dlqQueue() {
        return QueueBuilder.nonDurable(DLQ_QUEUE).build();
    }

    @Bean
    Queue bakingOrderQueue() {
        return QueueBuilder.nonDurable(BAKING_ORDER_QUEUE).build();
    }



    @Bean
    Binding bakingOrderBinding(Queue bakingOrderQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(bakingOrderQueue).to(topicExchange).with(NewOrderPlacedEvent.ROUTING_KEY);
    }
}
