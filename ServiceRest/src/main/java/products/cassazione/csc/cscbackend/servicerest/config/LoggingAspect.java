package products.cassazione.csc.cscbackend.servicerest.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Daniele Asteggiante
 */
@Aspect
@Component
public class LoggingAspect {

    // non posso usare l'injection point per il logger perchè sarebbe una dipendenza circolare
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* products.cassazione.csc.cscbackend.servicerest..*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method called: {}", methodName);
    }
}
