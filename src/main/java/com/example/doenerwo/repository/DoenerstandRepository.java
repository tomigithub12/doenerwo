package com.example.doenerwo.repository;

import com.example.doenerwo.DoenerBude;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoenerstandRepository extends MongoRepository<DoenerBude, String> {
}
