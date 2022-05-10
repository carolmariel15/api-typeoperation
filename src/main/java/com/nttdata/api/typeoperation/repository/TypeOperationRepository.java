package com.nttdata.api.typeoperation.repository;

import com.nttdata.api.typeoperation.document.TypeOperation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOperationRepository extends ReactiveMongoRepository<TypeOperation, Integer> {
}
