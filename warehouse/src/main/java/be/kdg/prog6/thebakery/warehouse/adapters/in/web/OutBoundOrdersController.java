package be.kdg.prog6.thebakery.warehouse.adapters.in.web;

import be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto.OrderItemDTO;
import be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto.OutBoundOrderDTO;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchOutBoundOrdersQuery;
import be.kdg.prog6.thebakery.warehouse.ports.in.FulfillOutBoundOrderUseCase;
import be.kdg.prog6.thebakery.warehouse.ports.in.FulfillOutboundOrderCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/warehouse/orders/outbound")
@CrossOrigin(origins = "http://localhost:3000")
public class OutBoundOrdersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FetchOutBoundOrdersQuery fetchOutBoundOrdersQuery;
    private final FulfillOutBoundOrderUseCase fulfillOutBoundOrderUseCase;

    @Autowired
    public OutBoundOrdersController(FetchOutBoundOrdersQuery fetchOutBoundOrdersQuery, FulfillOutBoundOrderUseCase fulfillOutBoundOrderUseCase) {
        this.fulfillOutBoundOrderUseCase = fulfillOutBoundOrderUseCase;
        log.info("Fetching all OutBoundOrders");
        this.fetchOutBoundOrdersQuery = fetchOutBoundOrdersQuery;
    }

    @GetMapping("")
    public ResponseEntity<List<OutBoundOrderDTO>> getAllOutBoundOrders() {
        log.info("Fetching all OutBoundOrders");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy : HH:mm");

        List<OutBoundOrder> outBoundOrders = fetchOutBoundOrdersQuery.fetchOutBoundOrders();
        List<OutBoundOrderDTO> outBoundOrderDTOList = outBoundOrders.stream()
                .map(order -> {
                    List<OrderItemDTO> orderItemDTOS = order.orderList().stream()
                            .map(orderItem -> new OrderItemDTO(
                                    orderItem.orderItemUuid().uuid().toString(),
                                    orderItem.outBoundOrderUuid().uuid().toString(),
                                    orderItem.stockItemUuid().uuid().toString(),
                                    orderItem.status().toString(),
                                    orderItem.amount()))
                            .toList();
                    return new OutBoundOrderDTO(
                            order.outBoundOrderUUID().uuid().toString(),
                            orderItemDTOS,
                            order.orderDate().format(formatter),
                            order.status().toString());
                }).toList();
        return ResponseEntity.ok(outBoundOrderDTOList);
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<Void> fulfillOutBoundOrder(@PathVariable UUID uuid) {
        log.info("Fulfilling OutBoundOrder with id: " + uuid);
        if (uuid == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        fulfillOutBoundOrderUseCase.fulfillOutBoundOrder(new FulfillOutboundOrderCommand(new OutBoundOrder.OutBoundOrderUUID(uuid)));
        return new ResponseEntity<>(HttpStatus.OK); // can be 204 but OK made more sense here.
    }

}
