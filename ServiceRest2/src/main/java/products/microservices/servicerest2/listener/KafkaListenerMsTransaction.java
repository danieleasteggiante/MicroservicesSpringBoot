package products.microservices.servicerest2.listener;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Daniele Asteggiante
 */
@Service
public class KafkaListenerMsTransaction {

    private Logger logger = LoggerFactory.getLogger(KafkaListenerMsTransaction.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaListenerMsTransaction(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "ms-transaction", groupId = "group-ms-transactions")
    public void listen(String message) {
        boolean isPerformedTransaction = new Random().nextBoolean();
        logger.info("Received message: {}", message);

        if (isPerformedTransaction) {
            logger.info("Transaction performed");
            kafkaTemplate.send("ms-transaction-response", "Transaction performed for message: " + message);
            return;
        }
        logger.info("Transaction not performed");
        kafkaTemplate.send("ms-transaction-response", "Transaction ERROR for message: " + message);
    }
}
