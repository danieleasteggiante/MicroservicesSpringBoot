package products.cassazione.csc.cscbackend.servicerest.rest;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import products.cassazione.csc.cscbackend.servicerest.feign.TestFeignCircuitBreaker;
import products.cassazione.csc.cscbackend.servicerest.feign.TestFeignClient;
import products.cassazione.csc.cscbackend.servicerest.feign.TestFeignClientEurekaClient;
import products.cassazione.csc.cscbackend.servicerest.feign.TestLoadBalancer;
import products.cassazione.csc.cscbackend.servicerest.producer.MsTopicProducer;

/**
 * @author Daniele Asteggiante
 */

@RestController
@RequestMapping("/api")
public class TestController {

    @Value("${message}")
    private String message;

    @Value("${spring.application.name}")
    private String applicationName;

    private final Logger logger;
    private final TestFeignClient testFeignClient;
    private final TestFeignClientEurekaClient testFeignClientEurekaClient;
    private final TestLoadBalancer testLoadBalancer;
    private final TestFeignCircuitBreaker testFeignCircuitBreaker;
    private final MsTopicProducer msTopicProducer;

    public TestController(Logger logger,
                          TestFeignClient testFeignClient,
                          TestFeignClientEurekaClient testFeignClientEurekaClient,
                          TestLoadBalancer testLoadBalancer,
                          TestFeignCircuitBreaker testFeignCircuitBreaker,
                          MsTopicProducer msTopicProducer)
    {
        this.logger = logger;
        this.testFeignClient = testFeignClient;
        this.testFeignClientEurekaClient = testFeignClientEurekaClient;
        this.testLoadBalancer = testLoadBalancer;
        this.testFeignCircuitBreaker = testFeignCircuitBreaker;
        this.msTopicProducer = msTopicProducer;
    }

    @GetMapping( path = "/test", produces = "application/json")
    public String getTest() {
        return "Test";
    }

    @GetMapping( path = "/callOtherServices", produces = "application/json")
    public String getConfig() {
        String response = testFeignClient.testClientRest2().getBody();
        String responseEureka = testFeignClientEurekaClient.testClientRest2().getBody();
        String responseLoadBalancer = testLoadBalancer.testLoadBalancer().getBody();
        return "Call from" + applicationName + " - tutti con Openfeign\n" +
                " - Response from ServiceRest2 tramite URL diretto: " + response + "\n" +
                " - Response from EurekaService tramite discovery Eureka: " + responseEureka + "\n" +
                " - Response from LoadBalancerService(multiple instance) tramite discovery Eureka: " + responseLoadBalancer;
    }

    @CircuitBreaker(name = "cb-microservice", fallbackMethod = "getCircuitBreakerFallback")
    @GetMapping( path = "/callCircuitBreaker", produces = "application/json")
    public String getCircuitBreaker() {
        String response = testFeignCircuitBreaker.testClientRe3().getBody();
        return "Call from" + applicationName + " - con Circuit Breaker\n" +
                " - Response from ServiceRest3 tramite URL diretto: " + response;
    }

    public String getCircuitBreakerFallback(Throwable t) {
        return "Circuit Breaker fallback method";
    }

    @GetMapping( path = "/callRateLimiter", produces = "application/json")
    @RateLimiter(name = "rl-microservice", fallbackMethod = "rateLimiterFallback")
    // @Bulkhead(name = "bh-microservice")
    public String rateLimiterEndpoint(Throwable t) {
        return "Rate Limiter method";
    }

    public String rateLimiterFallback(Throwable t) {
        return "Rate Limiter fallback method";
    }

    @GetMapping( path = "/callKafka", produces = "application/json")
    public ResponseEntity<String> callKafka() {
        logger.info("Sending message to Kafka");
        msTopicProducer.sendMessage("Message to Kafka");
        return ResponseEntity.ok("Message sent to Kafka");
    }

}
