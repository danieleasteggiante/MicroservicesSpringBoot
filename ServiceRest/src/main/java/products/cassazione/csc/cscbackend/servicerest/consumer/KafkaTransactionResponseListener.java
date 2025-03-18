package products.cassazione.csc.cscbackend.servicerest.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Daniele Asteggiante
 */
@Service
public class KafkaTransactionResponseListener {
    private final Logger logger;

    public KafkaTransactionResponseListener(Logger logger) {
        this.logger = logger;
    }

    @KafkaListener(topics = "ms-transaction-response", groupId = "group-ms-topic")
    public void listen(String message) {
        logger.info("Received message: {}", message);
        if (message.contains("ERROR")) {
            logger.error("Performing rollback");
            return;
        }
        logger.info("Transaction OK Performing commit");
    }
}
