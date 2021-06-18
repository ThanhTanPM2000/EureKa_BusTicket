package com.example.vexerepartner.repository;

import com.example.vexerepartner.entity.Futa;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutaRepository extends MongoRepository<Futa, ObjectId> {
}
