package com.connectJPA.filters;


import org.springframework.cloud.gateway.filter.factory.DedupeResponseHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//cấu hình các route thủ công qua Java DSL
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/api/menus/**")
                        .uri("http://localhost:8081"))
                .route("order", r -> r.path("/api/orders/**","/api/tables/**","/api/order-details/**")
                        .uri("http://localhost:8083"))
                .route("menu", r -> r.path("/api/kitchens/**","/uploads/**")
                        .uri("http://localhost:8082"))
                .route("kitchen", r -> r.path("/api/invoices/**")
                        .uri("http://localhost:8084"))
                .route("invoice", r -> r.path("/api/reports/**")
                        .uri("http://localhost:8085"))
                .route("report", r -> r.path("/api/users/**","/api/auth/**")
                        .uri("http://localhost:8086"))
                .route("report", r -> r.path("/api/payments/**")
                        .uri("http://localhost:8087"))
                .route("report", r -> r.path("/api/notifications/**")
                        .uri("http://localhost:8088"))
                .route("report", r -> r.path("/api/chats/**")
                        .uri("http://localhost:8089"))
                .build();
    }
}
