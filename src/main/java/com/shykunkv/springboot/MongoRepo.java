package com.shykunkv.springboot;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRepo extends MongoRepository<RandomNumber, String> {

}
