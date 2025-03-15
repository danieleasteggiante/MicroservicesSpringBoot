package products.microservices.loadbalancerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniele Asteggiante
 */
@RestController
@RequestMapping("/api/loadbalancer")
public class LoadBalancerRest {
    @Value("${istanza}")
    private String instanceId;

    @GetMapping( path = "/test", produces = "application/json")
    public String test() {
        return "Load Balancer Service is working! Instance ID: " + instanceId;
    }
}
