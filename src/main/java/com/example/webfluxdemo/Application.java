package com.example.webfluxdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {
		return (String... args) -> {
			mongo.dropCollection(City.class);
			mongo.createCollection(City.class, CollectionOptions.empty().size(1000000).capped());

			mongo.save(new City("北京", "中国"));
			mongo.save(new City("旧金山", "美国"));
			mongo.save(new City("伦敦", "英国"));
			mongo.save(new City("巴黎", "法国"));
			mongo.save(new City("罗马", "意大利"));
			mongo.save(new City("马德里", "西班牙"));
			mongo.save(new City("纽约", "美国"));
			mongo.save(new City("西雅图", "美国"));
			mongo.save(new City("东京", "日本"));
			mongo.save(new City("新德里", "印度"));
			mongo.save(new City("拉斯维加斯", "美国"));
		};
	}

}
