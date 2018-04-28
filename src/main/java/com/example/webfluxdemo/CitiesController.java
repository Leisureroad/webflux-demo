package com.example.webfluxdemo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CitiesController {

	private CityRepository repository;

	public CitiesController(CityRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/cities/{id}")
	public Mono<City> findById(@PathVariable String id) {
		return this.repository.findById(id);
	}

	@GetMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<City> all() {
		return this.repository.findAll()
				.log();
	}
}
