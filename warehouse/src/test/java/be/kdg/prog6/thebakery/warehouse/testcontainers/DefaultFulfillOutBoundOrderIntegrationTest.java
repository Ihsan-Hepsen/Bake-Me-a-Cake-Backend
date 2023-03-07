package be.kdg.prog6.thebakery.warehouse.testcontainers;

import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderStatus;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.domain.stock.Category;
import be.kdg.prog6.thebakery.warehouse.domain.stock.StockItem;
import be.kdg.prog6.thebakery.warehouse.exceptions.NoSuchOutBoundOrderException;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderLoadPort;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderUpdatePort;
import be.kdg.prog6.thebakery.warehouse.ports.out.stock.StockItemUpdatePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class DefaultFulfillOutBoundOrderIntegrationTest extends AbstractBaseTest {

    private static final String ENDPOINT = "/api/warehouse/orders/outbound";
    private static final UUID ORDER_UUID = UUID.fromString("1e00dc01-4808-48e3-88cd-019a885044b7");
    private static final UUID SUGAR_UUID = UUID.fromString("d56c473b-7f80-496b-b0eb-ee785e72a8e9");

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OutBoundOrderLoadPort outBoundOrderLoadPort;

    @Autowired
    private OutBoundOrderUpdatePort outBoundOrderUpdatePort;

    @Autowired
    private StockItemUpdatePort stockItemUpdatePort;


    @Test
    void fulfillOutBoundOrder() throws Exception {
        // Arrange
        OutBoundOrder order = new OutBoundOrder(ORDER_UUID);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderList(List.of(
                new OutBoundOrderItem(
                        order.outBoundOrderUUID().uuid(),
                        SUGAR_UUID,
                        0.25
                )
        ));

        StockItem sugar = new StockItem(new StockItem.StockItemUuid(SUGAR_UUID));
        sugar.setAmount(100d);
        sugar.setCategory(Category.FLOUR_SUGAR);
        sugar.setExpirationDate(LocalDateTime.now().plusDays(1).toLocalDate());

        outBoundOrderUpdatePort.updateOutBorderOrder(order);
        stockItemUpdatePort.updateIngredient(sugar);

        // Act
        mvc.perform(post(
                        ENDPOINT + "/" + ORDER_UUID.toString()))
                .andExpect(status().isOk());

        Optional<OutBoundOrder> outBoundOrder = outBoundOrderLoadPort.loadOutBoundOrder(ORDER_UUID);

        // Assert
        assertEquals(OrderStatus.FULFILLED, outBoundOrder.get().status());
        assertEquals(0.25, outBoundOrder.get().orderList().get(0).amount());
    }

    @Test
    void cannotFulfillIfOutBoundOrderIsNotFound() throws Exception {
        // Arrange
        OutBoundOrder order = new OutBoundOrder(UUID.randomUUID());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderList(List.of(
                new OutBoundOrderItem(
                        order.outBoundOrderUUID().uuid(),
                        SUGAR_UUID,
                        0.25
                )
        ));


        mvc.perform(post(
                        ENDPOINT + "/" + order.outBoundOrderUUID().uuid()))
                .andExpect(status().isNotFound());


        assertEquals(OrderStatus.PENDING, order.status());
    }

}
