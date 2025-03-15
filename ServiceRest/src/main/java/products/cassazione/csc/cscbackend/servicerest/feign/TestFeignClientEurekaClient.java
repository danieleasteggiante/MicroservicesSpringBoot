package products.cassazione.csc.cscbackend.servicerest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Daniele Asteggiante
 */
@FeignClient(name = "EurekaClient")
public interface TestFeignClientEurekaClient {
    @RequestMapping(value = "/api/eurekaclient/test", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<String> testClientRest2();
}
