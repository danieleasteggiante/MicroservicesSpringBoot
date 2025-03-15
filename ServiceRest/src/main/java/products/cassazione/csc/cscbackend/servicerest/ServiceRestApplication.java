package products.cassazione.csc.cscbackend.servicerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRestApplication.class, args);
    }

}
