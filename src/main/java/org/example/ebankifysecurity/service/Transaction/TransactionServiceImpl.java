package org.example.ebankifysecurity.service.Transaction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.ebankifysecurity.dto.TransactionDTO;
import org.example.ebankifysecurity.entity.Account;
import org.example.ebankifysecurity.entity.Transaction;
import org.example.ebankifysecurity.enums.TransactionType;
import org.example.ebankifysecurity.mapper.TransactionMapper;
import org.example.ebankifysecurity.repository.AccountRepository;
import org.example.ebankifysecurity.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Compte non trouvé."));
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setAccount(account);
        transaction.setTimestamp(LocalDateTime.now());

        if (transactionDTO.getAmount() <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0.");
        }

        if (transactionDTO.getType() == TransactionType.WITHDRAWAL && account.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Solde insuffisant.");
        }

        // Mise à jour du solde du compte
        if (transactionDTO.getType() == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + transactionDTO.getAmount());
        } else if (transactionDTO.getType() == TransactionType.WITHDRAWAL) {
            account.setBalance(account.getBalance() - transactionDTO.getAmount());
        }

        accountRepository.save(account);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
        return transactionMapper.toDTOList(transactions);
    }
}
