package be.kdg.prog6.thebakery.warehouse.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQWarehouseModuleConfig {

    public static final String TOPIC_EXCHANGE = "kdg-topic";

    @Bean
    TopicExchange topicExchangeWarehouse() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

}
