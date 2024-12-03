package org.example.ebankifysecurity.service.Account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.ebankifysecurity.dto.AccountDTO;
import org.example.ebankifysecurity.entity.Account;
import org.example.ebankifysecurity.entity.User;
import org.example.ebankifysecurity.mapper.AccountMapper;
import org.example.ebankifysecurity.repository.AccountRepository;
import org.example.ebankifysecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private AccountMapper accountMapper;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        User user = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé."));
        Account account = accountMapper.toEntity(accountDTO);
        account.setUser(user);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé."));
        return accountMapper.toDTO(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.toDTOList(accounts);
    }

    @Override
    public AccountDTO updateAccount(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé."));
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setStatus(accountDTO.getStatus());
        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Compte non trouvé.");
        }
        accountRepository.deleteById(id);
    }
}
