package org.example.ebankifysecurity.service.Admin;


import org.springframework.stereotype.Service;
import org.example.ebankifysecurity.repository.UserRepository;
import org.example.ebankifysecurity.entity.User;
import org.example.ebankifysecurity.dto.UserDTO;
import org.example.ebankifysecurity.mapper.UserMapper;
import org.example.ebankifysecurity.entity.Account;
import org.example.ebankifysecurity.enums.AccountStatus;
import org.example.ebankifysecurity.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final UserMapper userMapper;

    public AdminService(UserRepository userRepository, AccountRepository accountRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Account updateAccountStatus(Long accountId, AccountStatus status) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(status);
        return accountRepository.save(account);
    }
}