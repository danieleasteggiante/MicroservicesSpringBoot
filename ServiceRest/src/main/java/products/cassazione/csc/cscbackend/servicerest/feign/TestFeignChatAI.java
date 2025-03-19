package products.cassazione.csc.cscbackend.servicerest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Daniele Asteggiante
 */
@FeignClient(name = "AiChat")
public interface TestFeignChatAI {
    @RequestMapping(value = "/chat/questions", method = RequestMethod.GET, produces = "application/json")
    String testChatAI(@RequestParam("q") String question);
}
