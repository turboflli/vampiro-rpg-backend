package vampire.city.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class NpcProducer {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public NpcProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNpcEvent(CharacterEvents event, Integer id) {
        CharacterEventPayload payload = new CharacterEventPayload(event.name(), id);
        String json;
        try {
            json = this.objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend("", "npc-events", json);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

