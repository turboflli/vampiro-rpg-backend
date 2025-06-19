package vampire.city.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class NpcProducer {

    private final AmqpTemplate rabbitTemplate;

    public NpcProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNpcEvent(String message) {
        rabbitTemplate.convertAndSend("npc-events", message);
    }
}

