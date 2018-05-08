package com.example.webfluxdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
class WebConfiguration {
    @Autowired
    CityRepository cityRepository;

    WebConfiguration() {
    }

    @Bean
    RouterFunction<?> routes() {
        return RouterFunctions.route(RequestPredicates.GET("/reactive-cities"), (serverRequest) -> {
            return ServerResponse.ok().body(this.cityRepository.findAll(), City.class);
        }).andRoute(RequestPredicates.GET("/reactive-cities/{name}"), (serverRequest) -> {
            return ServerResponse.ok().body(this.cityRepository.findByName(serverRequest.pathVariable("name")), City.class);
        });
    }

    @Bean
    CityHandler handler() {
        return new CityHandler(this.cityRepository);
    }

    @Bean
    RouterFunction<?> routes2(CityHandler handler) {
        RequestPredicate var10000 = RequestPredicates.GET("/r2-cities");
        handler.getClass();
        RouterFunction var2 = RouterFunctions.route(var10000, handler::all);
        RequestPredicate var10001 = RequestPredicates.GET("/r2-cities/{name}");
        handler.getClass();
        return var2.andRoute(var10001, handler::byName);
    }
}

@Component
class CityHandler {
    private final CityRepository cityRepository;

    CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    Mono<ServerResponse> byName(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityRepository.findByName(request.pathVariable("name")), City.class);
    }

    Mono<ServerResponse> all(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cityRepository.findAll(),City.class);
    }
}


