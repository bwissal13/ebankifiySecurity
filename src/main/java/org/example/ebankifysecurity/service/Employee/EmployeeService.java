package org.example.ebankifysecurity.service.Employee;


import org.springframework.stereotype.Service;
import org.example.ebankifysecurity.repository.AccountRepository;
import org.example.ebankifysecurity.repository.TransactionRepository;
import org.example.ebankifysecurity.entity.Account;
import org.example.ebankifysecurity.entity.Transaction;

import java.util.List;

@Service
public class EmployeeService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public EmployeeService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Transaction approveTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        // Implement transaction approval logic
        return transactionRepository.save(transaction);
    }
}