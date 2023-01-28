package com.example.doenerwo.repository;

import com.example.doenerwo.DönerBude;
import com.example.doenerwo.domain.Doenerstand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoenerstandRepository extends MongoRepository<DönerBude, String> {
}
