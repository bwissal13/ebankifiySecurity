package org.example.ebankifysecurity.repository;

import org.example.ebankifysecurity.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   List<Transaction> findByAccountId(Long accountId);

}
