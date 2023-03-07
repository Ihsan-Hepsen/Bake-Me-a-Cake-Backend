package be.kdg.prog6.thebakery.common.events;

import com.fasterxml.jackson.databind.JsonNode;

public record EventMessage(EventHeader eventHeader, JsonNode messageBody) {

}
