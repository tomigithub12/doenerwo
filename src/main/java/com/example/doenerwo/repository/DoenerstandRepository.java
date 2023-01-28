package com.example.doenerwo.repository;

import com.example.doenerwo.DoenerBude;
import com.example.doenerwo.domain.Doenerstand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface DoenerstandRepository extends MongoRepository<Doenerstand, String> {
}
