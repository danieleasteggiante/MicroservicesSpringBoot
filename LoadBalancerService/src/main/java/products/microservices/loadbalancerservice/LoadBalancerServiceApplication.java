package products.microservices.loadbalancerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class LoadBalancerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalancerServiceApplication.class, args);
    }

}
