package products.microservices.eurekaclient;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniele Asteggiante
 */

@RestController
@RequestMapping("/api/eurekaclient")
public class RestControllerEureka {

    @GetMapping( path = "/test", produces = "application/json")
    public String getTest() {
        return "Test from EurekaClient";
    }
}
