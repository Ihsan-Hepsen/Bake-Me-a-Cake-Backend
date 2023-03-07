package be.kdg.prog6.thebakery.factory.core;

import be.kdg.prog6.thebakery.common.events.NewOrderPlacedEvent;
import be.kdg.prog6.thebakery.factory.domain.order.BakingOrder;
import be.kdg.prog6.thebakery.factory.exceptions.NoSuchProductException;
import be.kdg.prog6.thebakery.factory.ports.out.BakingOrderUpdatePort;
import be.kdg.prog6.thebakery.factory.ports.out.ProductLoadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MockingDefaultCheckNewOrdersUseCaseTest {

    @Captor
    ArgumentCaptor<BakingOrder> bakingOrderArgumentCaptor;


    @Mock
    private BakingOrderUpdatePort bakingOrderUpdatePort;

    @Mock
    private ProductLoadPort productLoadPort;

    private static final UUID CUSTOMER_UUID = UUID.fromString("b5ff5966-514a-11ed-bdc3-0242ac120002");


    @Test
    void cannotMakeBakingOrderForNonExistingProducts() {
        // Arrange
        NewOrderPlacedEvent newOrderEvent = new NewOrderPlacedEvent(
                CUSTOMER_UUID, Map.of(UUID.fromString("6b026613-450e-4fda-8ea1-7c403e0e9212"), 1));

        DefaultCheckNewOrderUseCase useCase = new DefaultCheckNewOrderUseCase(
                List.of(bakingOrderUpdatePort),
                productLoadPort);

        // Assert
        assertThrows(NoSuchProductException.class, () -> {
            // Act
            useCase.handle(newOrderEvent);
        });
    }

}
