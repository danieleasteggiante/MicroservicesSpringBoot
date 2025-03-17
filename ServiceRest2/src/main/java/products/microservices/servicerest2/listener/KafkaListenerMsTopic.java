package products.microservices.servicerest2.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Daniele Asteggiante
 */
@Service
public class KafkaListenerMsTopic {

    private Logger logger = LoggerFactory.getLogger(KafkaListenerMsTopic.class);

    @KafkaListener(topics="ms-topic", groupId = "group-ms-topic")
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }
}
