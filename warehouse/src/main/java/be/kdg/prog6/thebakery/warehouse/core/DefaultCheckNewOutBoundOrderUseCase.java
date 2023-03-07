package be.kdg.prog6.thebakery.warehouse.core;

import be.kdg.prog6.thebakery.common.annotations.UseCase;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderItemStatus;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OrderStatus;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrderItem;
import be.kdg.prog6.thebakery.warehouse.ports.in.CheckNewOutBoundOrderUseCase;
import be.kdg.prog6.thebakery.warehouse.ports.out.delivery.outbound.OutBoundOrderUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@UseCase
public class DefaultCheckNewOutBoundOrderUseCase implements CheckNewOutBoundOrderUseCase {

    private final List<OutBoundOrderUpdatePort> updatePorts;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;

    @Autowired
    public DefaultCheckNewOutBoundOrderUseCase(List<OutBoundOrderUpdatePort> updatePorts, ObjectMapper objectMapper) {
        this.updatePorts = updatePorts;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(String message) {
        log.info("\n\n\n\nReceived message: " + message + "\n\n\n\n");
        try {
            var stockItemQuantity = objectMapper.readValue(objectMapper.readTree(message).get("messageBody").get("ingredientAndQuantity").toString(), Map.class);
            OutBoundOrder outBoundOrder = new OutBoundOrder();
            stockItemQuantity.forEach((key, value) -> {
                var outBoundItem = new OutBoundOrderItem(
                        outBoundOrder.outBoundOrderUUID().uuid(),
                        UUID.fromString(key.toString()),
                        (Double) value
                );
                outBoundOrder.addOutBoundItem(outBoundItem);
            });
            outBoundOrder.setStatus(OrderStatus.PENDING);
            log.info("Message converted: " + outBoundOrder);
            updatePorts.stream().forEach(port -> port.updateOutBorderOrder(outBoundOrder));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
