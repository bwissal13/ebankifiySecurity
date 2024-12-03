package org.example.ebankifysecurity.repository;

import org.example.ebankifysecurity.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, Long> {
}
