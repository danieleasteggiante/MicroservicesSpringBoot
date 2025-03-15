package products.microservices.cloudgateway.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Daniele Asteggiante
 */
@Component
public class LoggingFilter implements GlobalFilter {
    @Autowired
    private Logger logger;
    @Override
    // Mono<Void> e' un tipo di dato che rappresenta un'operazione asincrona che restituisce un void
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // ServerWebExchange contiene la richiesta e la risposta e' tipo HttpServletRequest e HttpServletResponse
        logger.info("Request -> " + exchange.getRequest().getURI());
        return chain.filter(exchange);
    }

//    Questo se volessi ad esempio fare un filtro per controllare se la richiesta ha un header Authorization
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); // Blocca la richiesta
//        }
//        return chain.filter(exchange); // Continua se il controllo passa
//    }
}
