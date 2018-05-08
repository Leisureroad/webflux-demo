package com.example.webfluxdemo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CityRepository extends ReactiveCrudRepository<City, String> {

    Flux<City> findByName(String name);
}
