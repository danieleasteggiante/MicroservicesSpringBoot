package products.cassazione.csc.cscbackend.servicerest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daniele Asteggiante
 */
@FeignClient(name = "ServiceRest3Application", url = "http://localhost:9997")
public interface TestFeignCircuitBreaker {
    @RequestMapping(value = "/api/rest3/test", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<String> testClientRe3();
}
