package com.ab.cqrs.query.repository;

import com.ab.cqrs.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
