package products.cassazione.csc.cscbackend.servicerest.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daniele Asteggiante
 */
@Configuration
public class LoggerProducerFactory {

    @Bean
    public Logger loggerProducer(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
