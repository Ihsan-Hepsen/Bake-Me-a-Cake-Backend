package be.kdg.prog6.thebakery.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
public class Spammer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public Spammer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * In a rabbitmq/AMQP world it makes more sense to get the type of event in a routing key.
     *
     * @param donations
     * @throws JsonProcessingException
     */
    public void startSpamming(int donations) throws JsonProcessingException {
    }
}
