package org.example.ebankifysecurity.service.Transaction;

import org.example.ebankifysecurity.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getTransactionsByAccountId(Long accountId);
}

