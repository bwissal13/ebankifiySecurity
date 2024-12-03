package org.example.ebankifysecurity.repository;

import org.example.ebankifysecurity.dto.LoginRequestDTO;
import org.example.ebankifysecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
