package be.kdg.prog6.thebakery.factory.adapters.out;

import be.kdg.prog6.thebakery.common.commands.OutBoundOrderCommand;
import be.kdg.prog6.thebakery.common.events.EventHeader;
import be.kdg.prog6.thebakery.common.events.EventMessage;
import be.kdg.prog6.thebakery.common.events.EventType;
import be.kdg.prog6.thebakery.common.events.IngredientListIsSentEvent;
import be.kdg.prog6.thebakery.factory.config.RabbitMQFactoryModuleConfig;
import be.kdg.prog6.thebakery.factory.exceptions.SendingOutBoundOrderFailedException;
import be.kdg.prog6.thebakery.factory.ports.out.SendIngredientsPort;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SendIngredientListAdapter implements SendIngredientsPort {

    private final List<BakingOrderUpdatePort> bakingOrderUpdatePorts;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public SendIngredientListAdapter(List<BakingOrderUpdatePort> bakingOrderUpdatePorts, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.bakingOrderUpdatePorts = bakingOrderUpdatePorts;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void sendIngredients(OutBoundOrderCommand command) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQFactoryModuleConfig.TOPIC_EXCHANGE,
                    IngredientListIsSentEvent.ROUTING_KEY,
                    objectMapper.writeValueAsString(new EventMessage(
                            new EventHeader(
                                    UUID.randomUUID(),
                                    EventType.IngredientListIsSentEvent,
                                    LocalDateTime.now()),
                            objectMapper.valueToTree(new IngredientListIsSentEvent(command.ingredientList())))));
        } catch (JsonProcessingException e) {
            log.error("Failed to send ingredients", e);
            throw new SendingOutBoundOrderFailedException(e);
        }
    }
}
