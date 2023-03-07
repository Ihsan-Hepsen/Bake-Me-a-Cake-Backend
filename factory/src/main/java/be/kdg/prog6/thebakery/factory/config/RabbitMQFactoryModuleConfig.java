package be.kdg.prog6.thebakery.factory.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQFactoryModuleConfig {

    public static final String TOPIC_EXCHANGE = "kdg-topic";


    @Bean
    TopicExchange topicExchangeFactory() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

}
