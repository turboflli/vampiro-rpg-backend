package vampire.city.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.city.service.DefaultRoutineStarter;

@Component
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class NpcConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DefaultRoutineStarter defaultRoutineStarter;

    @RabbitListener(queues = "npc-events")
    public void receiveMessage(String object) throws JsonMappingException, JsonProcessingException {
        CharacterEventPayload payload = objectMapper.readValue(object, CharacterEventPayload.class);
        System.out.println("Recebendo evento: " + objectMapper.writeValueAsString(payload));
        String event = payload.getEvent();
        Integer id = payload.getId();
        switch (event) {
            case "CREATE":
                this.defaultRoutineStarter.createDefaultRoutineForCharacter(id);
                break;
            case "AFTER_UPDATE":
                this.defaultRoutineStarter.createDefaultRoutineForCharacter(id);
                break;
            case "CREATE_PLACE":
                this.defaultRoutineStarter.createDefaultRoutineForPlace(id);
                break;
            case "AFTER_UPDATE_PLACE":
                this.defaultRoutineStarter.createDefaultRoutineForPlace(id);
                break;
            default:
                break;
        }
    }
}
    