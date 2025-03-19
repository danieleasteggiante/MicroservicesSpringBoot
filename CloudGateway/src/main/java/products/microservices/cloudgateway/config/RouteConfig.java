package products.microservices.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Daniele Asteggiante
 */
@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // avendo lo stesso prefisso /api sia per il servizio rest che per il servizio api,non c e bisogno di riscrivere il path
                .route(r->r.path("/api/**").uri("lb://SERVICEREST"))
                // spring.cloud.gateway.discovery.locator.lower-case-service-id=true per abilitare il lowercase
                .route(r->r.path("/lower/**")
                        .filters(f ->
                                f.rewritePath("/lower/(?<segment>.*)", "/${segment}")
                                        .addRequestHeader("X-Custom-Header", "Header added by Gateway"))
                        .uri("lb://servicerest"))
                .route(r -> r.path("/v/**")
                        // serve per riscrivere il path perche' altrimenti il servizio rest non lo riconosce essendoci il prefisso /v
                        .filters(f -> f.rewritePath("/v/(?<segment>.*)", "/${segment}"))  // Riscrive il path
                        .uri("lb://SERVICEREST"))
                .route(r -> r.path("/get").uri("http://httpbin.org:80"))
                .route(r -> r.path("/chat/**").uri("lb://AICHAT"))
                .build();
    }
}
