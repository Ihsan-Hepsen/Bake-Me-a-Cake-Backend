package be.kdg.prog6.thebakery.factory.testcontainers;

import be.kdg.prog6.thebakery.common.commands.OutBoundOrderCommand;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import be.kdg.prog6.thebakery.factory.domain.order.OrderStatus;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import be.kdg.prog6.thebakery.factory.ports.out.RecipeLoadPort;
import be.kdg.prog6.thebakery.factory.ports.out.SendIngredientsPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@AutoConfigureMockMvc
@SpringBootTest
public class SendIngredientListIntegrationTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    SendIngredientsPort sendIngredientsPort;

    @Autowired
    protected MockMvc mvc;

    private static final UUID SUGAR_UUID = UUID.fromString("d56c473b-7f80-496b-b0eb-ee785e72a8e9");


    @Test
    void sendIngredients() {
        // Act
        sendIngredientsPort.sendIngredients(new OutBoundOrderCommand(Map.of(SUGAR_UUID, 100.0)));

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }
}
