package products.microservices.aichat.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import products.microservices.aichat.service.ChatAIService;

/**
 * @author Daniele Asteggiante
 */

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final static Logger logger = LoggerFactory.getLogger(ChatController.class);
    private ChatAIService chatAIService;


    public ChatController(ChatAIService chatAIService) {
        this.chatAIService = chatAIService;
    }

    @GetMapping(path = "/questions", produces = "application/json")
    public String chat(@RequestParam("q") String question) {
        logger.info("Calling chat AI from question: " + question);
        ChatResponse chatResponse = chatAIService.generateResponse(question);
        logger.info(chatResponse.getResult().getOutput().toString());
        return chatResponse.getResult().getOutput().toString();
    }
}
