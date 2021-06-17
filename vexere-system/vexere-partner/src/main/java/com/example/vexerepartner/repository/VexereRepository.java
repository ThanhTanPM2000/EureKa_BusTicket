package com.example.vexerepartner.repository;

import com.example.vexerepartner.entity.VeXeRe;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VexereRepository extends MongoRepository<VeXeRe, ObjectId> {
}
