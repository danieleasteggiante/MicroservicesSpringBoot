package products.cassazione.csc.cscbackend.servicerest.producer;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Daniele Asteggiante
 */
@Service
public class MsTopicProducer {
    private static final String TOPIC_NAME = "ms-topic";

    private KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger;

    public MsTopicProducer(Logger logger, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.logger = logger;
    }

    public void sendMessage(String message) {
        logger.info("Sending message: {}", message);
        kafkaTemplate.send(TOPIC_NAME, message);
        logger.info("Sent message: {}", message);
    }
}
