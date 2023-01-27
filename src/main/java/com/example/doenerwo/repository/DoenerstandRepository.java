package com.example.doenerwo.repository;

import com.example.doenerwo.domain.Doenerstand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoenerstandRepository extends MongoRepository<Doenerstand, String> {
}
