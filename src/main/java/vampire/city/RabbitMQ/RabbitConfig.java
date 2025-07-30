package vampire.city.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue placeQueue() {
        // Fila usada por PlaceProducer e PlaceConsumer
        return new Queue("place-events", false);
    }

    @Bean
    public Queue npcQueue() {
        // Fila futura usada por NpcProducer e NpcConsumer
        return new Queue("npc-events", false);
    }
    
}
