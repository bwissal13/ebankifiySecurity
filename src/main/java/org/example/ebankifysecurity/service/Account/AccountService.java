package org.example.ebankifysecurity.service.Account;

import org.example.ebankifysecurity.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountById(Long id);
    List<AccountDTO> getAllAccounts();
    AccountDTO updateAccount(Long id, AccountDTO accountDTO);
    void deleteAccount(Long id);
}
