package vampire.city.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "rabbit.enabled", havingValue = "true", matchIfMissing = true)
public class NpcConsumer {

    @RabbitListener(queues = "npc-events")
    public void receiveMessage(String message) {
        System.out.println("📥 Mensagem recebida: " + message);
        // aqui você pode fazer qualquer lógica: logar, agendar eventos, etc.
    }
}
    