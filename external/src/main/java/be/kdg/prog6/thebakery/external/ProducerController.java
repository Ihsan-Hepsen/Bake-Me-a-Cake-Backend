package be.kdg.prog6.thebakery.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProducerController {

    private final Spammer spammer;

    public ProducerController(Spammer spammer) {
        this.spammer = spammer;
    }


    @PostMapping("/message/send/{orders}")
    public String startSpamming(@PathVariable int orders) throws JsonProcessingException {
        spammer.startSpamming(orders);
        return "Spammed!!!!!";
    }
}
