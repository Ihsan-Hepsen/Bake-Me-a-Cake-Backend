package be.kdg.prog6.thebakery.warehouse.adapters.in.web;

import be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto.InBoundOrderDTO;
import be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto.OrderItemDTO;
import be.kdg.prog6.thebakery.warehouse.adapters.in.web.dto.OutBoundOrderDTO;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.InBoundOrder;
import be.kdg.prog6.thebakery.warehouse.domain.delivery.OutBoundOrder;
import be.kdg.prog6.thebakery.warehouse.ports.in.FetchInBoundOrdersQuery;
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
@RequestMapping("/api/warehouse/orders/inbound")
@CrossOrigin(origins = "http://localhost:3000")
public class InBoundOrdersController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final FetchInBoundOrdersQuery fetchInBoundOrdersQuery;

    @Autowired
    public InBoundOrdersController(FetchInBoundOrdersQuery fetchInBoundOrdersQuery) {
        this.fetchInBoundOrdersQuery = fetchInBoundOrdersQuery;
    }


    @GetMapping("")
    public ResponseEntity<List<InBoundOrderDTO>> getAllInBoundOrders() {
        List<InBoundOrder> outBoundOrders = fetchInBoundOrdersQuery.fetchInBoundOrders();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy : HH:mm");

        List<InBoundOrderDTO> outBoundOrderDTOList = outBoundOrders.stream()
                .map(order -> {
                    List<OrderItemDTO> orderItemDTOS = order.orderList().stream()
                            .map(orderItem -> new OrderItemDTO(orderItem.orderItemUuid().uuid().toString(),
                                    orderItem.inBoundOrderUuid().uuid().toString(),
                                    orderItem.stockItemUuid().uuid().toString(),
                                    orderItem.status().toString(),
                                    orderItem.amount()))
                            .toList();
                    return new InBoundOrderDTO(order.inBoundOrderUuid().uuid().toString(),
                            order.supplierUuid().toString(),
                            orderItemDTOS,
                            order.orderDate().format(formatter),
                            order.status().toString());
                }).toList();
        return ResponseEntity.ok(outBoundOrderDTOList);
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<Void> fulfillInBoundOrder(@PathVariable UUID uuid) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

}
