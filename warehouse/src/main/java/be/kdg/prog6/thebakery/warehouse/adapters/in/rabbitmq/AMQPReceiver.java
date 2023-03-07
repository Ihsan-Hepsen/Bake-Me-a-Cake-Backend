package be.kdg.prog6.thebakery.warehouse.adapters.in.rabbitmq;

import be.kdg.prog6.thebakery.common.events.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface AMQPReceiver {

    boolean apply(EventType eventType);

    void receiveMessage(JsonNode message) throws JsonProcessingException;

}
