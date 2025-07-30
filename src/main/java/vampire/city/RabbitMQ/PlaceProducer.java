package vampire.city.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.city.model.PlaceDTO;

@Service
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class PlaceProducer {
    
    private final AmqpTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public PlaceProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPlaceEvent(PlaceEvents event, PlaceDTO placeDTO) {
        PlaceEventPayload payload = new PlaceEventPayload(event.name(), placeDTO);
        String json;
        try {
            json = this.objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend("", "place-events", json);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendBlockEvent(PlaceEvents event, Integer id) {
        PlaceEventPayload payload = new PlaceEventPayload(event.name(), id);
        String json;
        try {
            json = this.objectMapper.writeValueAsString(payload);
            rabbitTemplate.convertAndSend("", "place-events", json);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
