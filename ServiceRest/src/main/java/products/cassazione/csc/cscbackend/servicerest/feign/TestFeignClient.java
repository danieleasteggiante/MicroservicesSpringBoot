package products.cassazione.csc.cscbackend.servicerest.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daniele Asteggiante
 */
@FeignClient(name = "ServiceRest2Application", url = "http://localhost:9991")
public interface TestFeignClient {
    @RequestMapping(value = "/api/rest2/test", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<String> testClientRest2();
}
