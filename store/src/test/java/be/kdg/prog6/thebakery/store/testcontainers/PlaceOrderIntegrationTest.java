package be.kdg.prog6.thebakery.store.testcontainers;

import be.kdg.prog6.thebakery.store.domain.Customer;
import be.kdg.prog6.thebakery.store.domain.SalesOrder;
import be.kdg.prog6.thebakery.store.ports.out.SalesOrderLoadPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PlaceOrderIntegrationTest extends AbstractBaseTest {

    private static final String ENDPOINT = "/api/store/order";

    private static final String CUSTOMER_UUID = "b5ff5966-514a-11ed-bdc3-0242ac120092";

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SalesOrderLoadPort salesOrderLoadPort;

    @Autowired
    protected MockMvc mvc;


    @Test
    void placeOrder() throws Exception {

        // Act
        mvc.perform(post(ENDPOINT)
                        // body
                        .accept(APPLICATION_JSON)
                        // content body
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "customerUUID": "b5ff5966-514a-11ed-bdc3-0242ac120092",
                                    "orderItems": [
                                        {"productId": "7d0d7626-78be-459a-b4dd-9a48701088dd", "quantity": 1}
                                    ]
                                }
                                """))
                .andExpect(status().isCreated());

        Optional<SalesOrder> salesOrder = salesOrderLoadPort.loadSalesOrder(new Customer.CustomerUUID(UUID.fromString(CUSTOMER_UUID)));


        // Assert
        assertEquals(1, salesOrder.get().orderContent().size());
        assertEquals("7d0d7626-78be-459a-b4dd-9a48701088dd", salesOrder.get().orderContent().get(0).product().productUuid().toString());

        // Only topic exchange is used
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }

}
