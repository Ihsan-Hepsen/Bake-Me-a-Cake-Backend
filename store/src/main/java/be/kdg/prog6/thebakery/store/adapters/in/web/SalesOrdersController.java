package be.kdg.prog6.thebakery.store.adapters.in.web;

import be.kdg.prog6.thebakery.store.domain.Customer;
import be.kdg.prog6.thebakery.store.domain.OrderItem;
import be.kdg.prog6.thebakery.store.domain.Product;
import be.kdg.prog6.thebakery.store.adapters.in.web.dto.SalesOrderDTO;
import be.kdg.prog6.thebakery.store.exceptions.CannotPlaceOrderException;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderCommand;
import be.kdg.prog6.thebakery.store.ports.in.PlaceOrderUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class SalesOrdersController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public SalesOrdersController(PlaceOrderUseCase placeOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
    }

    @PostMapping("/order")
    public ResponseEntity<Void> placeOrder(@Valid @RequestBody SalesOrderDTO salesOrderDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            log.error("Cannot place order: " + errors.getAllErrors());
            throw new CannotPlaceOrderException("SalesOrderDTO is not valid");
        }
        log.info(salesOrderDTO.toString());
        log.info("POST call received, placing order.");

        List<OrderItem> orderItems = salesOrderDTO.orderItems().stream()
                .map(oiDto -> new OrderItem(Product.fromUuid(UUID.fromString(oiDto.productId())), oiDto.quantity()))
                .toList();
        placeOrderUseCase.placeOrder(
                new PlaceOrderCommand(
                        new Customer.CustomerUUID(UUID.fromString(salesOrderDTO.customerUUID())), orderItems));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
