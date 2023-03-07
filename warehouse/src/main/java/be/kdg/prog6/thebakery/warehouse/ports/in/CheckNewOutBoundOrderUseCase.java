package be.kdg.prog6.thebakery.warehouse.ports.in;

import be.kdg.prog6.thebakery.common.events.IngredientListIsSentEvent;

public interface CheckNewOutBoundOrderUseCase {

    void handle(String message);
}
