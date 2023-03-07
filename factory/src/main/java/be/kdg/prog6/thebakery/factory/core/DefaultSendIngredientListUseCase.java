package be.kdg.prog6.thebakery.factory.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.common.commands.OutBoundOrderCommand;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import be.kdg.prog6.thebakery.factory.domain.order.OrderStatus;
import be.kdg.prog6.thebakery.factory.ports.in.SendIngredientListUseCase;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import be.kdg.prog6.thebakery.factory.ports.out.RecipeLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.SendIngredientsPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


@UseCase
@EnableAsync
@EnableScheduling
public class DefaultSendIngredientListUseCase implements SendIngredientListUseCase {

    private final BakingOrderLoadPort bakingOrderLoadPort;

    private final BakingOrderUpdatePort bakingOrderUpdatePort;
    private final RecipeLoadPort recipeLoadPort;

    private final SendIngredientsPort sendIngredientsPort;


    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DefaultSendIngredientListUseCase(BakingOrderLoadPort bakingOrderLoadPort,
                                            BakingOrderUpdatePort bakingOrderUpdatePort,
                                            RecipeLoadPort recipeLoadPort,
                                            SendIngredientsPort sendIngredientsPort) {
        this.bakingOrderLoadPort = bakingOrderLoadPort;
        this.bakingOrderUpdatePort = bakingOrderUpdatePort;
        this.recipeLoadPort = recipeLoadPort;
        this.sendIngredientsPort = sendIngredientsPort;
    }


    public void sendBakingOrders(List<BakingOrder> bakingOrders) throws JsonProcessingException {
        Map<UUID, Double> ingredientQuantity = new HashMap<>();
        for (BakingOrder bo : bakingOrders) {
            var recipe = recipeLoadPort.loadRecipe(bo.recipeUuid().uuid()).orElse(null);
            recipe.ingredients().stream().forEach(ing -> {
                if (ingredientQuantity.containsKey(ing.ingredientUuid())) {
                    ingredientQuantity.put(ing.ingredientUuid(), ingredientQuantity.get(ing.ingredientUuid()) + ing.amount());
                } else {
                    ingredientQuantity.put(ing.ingredientUuid(), ing.amount());
                }
            });
            bo.sendOrder();
        }
        sendIngredientsPort.sendIngredients(new OutBoundOrderCommand(ingredientQuantity));
        bakingOrders.forEach(bakingOrderUpdatePort::updateOrder);
    }

    @Override
    @Async
    @Scheduled(cron = "0 00 22 * * *", zone = "Europe/Brussels")
    public void sendBakingOrders() {
        Optional<List<BakingOrder>> bakingOrders = bakingOrderLoadPort.loadAllBakingOrdersForTheDay();
        if (bakingOrders.isPresent()) {
            try {
                this.sendBakingOrders(bakingOrders.get());
                log.info("Ingredient list is sent to warehouse.");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
