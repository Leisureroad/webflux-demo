package com.example.webfluxdemo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CityRepository extends ReactiveCrudRepository<City, String> {

}
