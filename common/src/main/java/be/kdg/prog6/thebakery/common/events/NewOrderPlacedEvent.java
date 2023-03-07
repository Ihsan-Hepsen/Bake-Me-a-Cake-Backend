package be.kdg.prog6.thebakery.common.events;

import java.util.Map;
import java.util.UUID;

public record NewOrderPlacedEvent(UUID customer, Map<UUID, Integer> quantity) {

    public static final String ROUTING_KEY = "SalesOrder";
}
