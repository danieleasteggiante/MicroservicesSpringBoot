package products.microservices.aichat.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

/**
 * @author Daniele Asteggiante
 */
@Service
public class ChatAIService {

    private ChatClient chatClient;

    public ChatAIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public ChatResponse generateResponse(String question) {
        return chatClient.prompt(question).call().chatResponse();
    }
}
