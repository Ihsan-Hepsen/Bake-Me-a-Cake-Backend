package be.kdg.prog6.thebakery.factory.ports.out;

import be.kdg.prog6.thebakery.common.commands.OutBoundOrderCommand;

public interface SendIngredientsPort {
    void sendIngredients(OutBoundOrderCommand command);
}

