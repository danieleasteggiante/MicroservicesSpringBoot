package products.microservices.cloudgateway.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daniele Asteggiante
 */
@Configuration
public class LoggerProducer {
    // se uso lo stesso nome della classe anche per il metodo ho la doppia scansione
    // quindi devo usare un nome diverso
    @Bean
    public Logger loggerProd(InjectionPoint injectionPoint) {
        return org.slf4j.LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
