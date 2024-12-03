package org.example.ebankifysecurity.controller;

import jakarta.validation.Valid;
import org.example.ebankifysecurity.dto.AccountDTO;
import org.example.ebankifysecurity.service.Account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO) {
        AccountDTO updatedAccount = accountService.updateAccount(id, accountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
