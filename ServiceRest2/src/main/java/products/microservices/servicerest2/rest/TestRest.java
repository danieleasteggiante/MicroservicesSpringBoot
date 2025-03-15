package products.microservices.servicerest2.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniele Asteggiante
 */
@RestController
@RequestMapping("/api/rest2")
public class TestRest {
    @Value("${message}")
    private String message;

    @GetMapping( path = "/test", produces = "application/json")
    public String getTest() {
        return "from service-rest2: " + message;
    }
}
