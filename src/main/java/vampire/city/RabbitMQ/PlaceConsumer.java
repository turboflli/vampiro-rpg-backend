package vampire.city.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.city.model.BlocksDTO;
import vampire.city.model.PlaceDTO;
import vampire.city.service.PlaceService;
import vampire.city.service.BlocksService;

@Component
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class PlaceConsumer {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private BlocksService blocksService;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "place-events")
    public void receiveMessage(String object) throws JsonMappingException, JsonProcessingException {
        PlaceEventPayload payload = objectMapper.readValue(object, PlaceEventPayload.class);
        System.out.println("Recebendo evento: " + objectMapper.writeValueAsString(payload));
        PlaceDTO placeDTO = payload.getPlace();
        switch (payload.getEvent()) {
            case "CREATE":
                this.placeService.fillBlocksColor(placeDTO);
                break;
            case "BEFORE_UPDATE":
                this.placeService.removeBlocksColor(placeDTO.getX_coordinate(), placeDTO.getY_coordinate());
                break;
            case "AFTER_UPDATE":
                this.placeService.fillBlocksColor(placeDTO);
                break;
            case "DELETE":
                this.placeService.removeBlocksColor(placeDTO.getX_coordinate(), placeDTO.getY_coordinate());
                break;
            case "CREATE_BLOCKS":
                this.blocksService.initiateBlocksColor(payload.getId());
                break;
            default:
                System.out.println("📥 Mensagem recebida: " + payload.getEvent() + " - " + object);
                break;
        }
    }
}
