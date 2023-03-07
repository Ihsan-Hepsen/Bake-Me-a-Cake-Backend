package be.kdg.prog6.thebakery.store.core;

import be.kdg.prog6.thebakery.store.core.DefaultPlaceOrderUseCase;
import be.kdg.prog6.thebakery.store.domain.*;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderCommand;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderUseCase;
import be.kdg.prog6.thebakery.store.ports.out.OrderItemUpdatePort;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderUpdatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MockingDefaultPlaceOrderUseCaseTest {

    @Captor
    ArgumentCaptor<SalesOrder> salesOrderArgumentCaptor;

    private final static Customer.CustomerUUID CUSTOMER_UUID = new Customer.CustomerUUID(UUID.fromString("b5ff5966-514a-11ed-bdc3-0242ac120002"));

    @Mock
    private SalesOrderUpdatePort salesOrderUpdatePort;

    @Mock
    private OrderItemUpdatePort orderItemUpdatePort;


    @Test
    void orderShouldPlaceTheCorrectOrderItemAndQuantity() {
        // Arrange

        OrderItem orderItem = new OrderItem(Product.DONUT_JELLY, 1);

        // Act
        PlaceOrderUseCase placeOrderUseCase = new DefaultPlaceOrderUseCase(List.of(salesOrderUpdatePort), List.of(orderItemUpdatePort));
        placeOrderUseCase.placeOrder(new PlaceOrderCommand(CUSTOMER_UUID, List.of(orderItem)));

        // verify if order is placed by the correct user

        verify(salesOrderUpdatePort).updateSalesOrders(salesOrderArgumentCaptor.capture());

        // Assert
        assertEquals(UUID.fromString("7d0d7626-78be-459a-b4dd-9a48701088dd"),
                salesOrderArgumentCaptor.getValue().orderContent().get(0).product().productUuid());

        assertEquals(1, salesOrderArgumentCaptor.getValue().orderContent().get(0).quantity());
    }

}
