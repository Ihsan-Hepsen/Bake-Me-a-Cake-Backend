package be.kdg.prog6.thebakery.common.events;

import java.util.Map;
import java.util.UUID;

public record IngredientListIsSentEvent(Map<UUID, Double> ingredientAndQuantity) {

    public static final String ROUTING_KEY = "IngredientList";

}
