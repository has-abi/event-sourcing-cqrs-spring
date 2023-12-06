package com.ab.cqrs.query.repository;

import com.ab.cqrs.query.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
