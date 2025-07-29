package vampire.city.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class NpcProducer {

    private final AmqpTemplate rabbitTemplate;

    public NpcProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNpcEvent(String message) {
        rabbitTemplate.convertAndSend("npc-events", message);
    }
}

