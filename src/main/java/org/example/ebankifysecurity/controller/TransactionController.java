package org.example.ebankifysecurity.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.ebankifysecurity.dto.TransactionDTO;
import org.example.ebankifysecurity.service.Transaction.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {


    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        TransactionDTO savedTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }
}
