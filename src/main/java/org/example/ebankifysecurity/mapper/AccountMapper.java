package org.example.ebankifysecurity.mapper;

import org.example.ebankifysecurity.dto.AccountDTO;
import org.example.ebankifysecurity.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);
    Account toEntity(AccountDTO accountDTO);

    List<AccountDTO> toDTOList(List<Account> accounts);
    List<Account> toEntityList(List<AccountDTO> accountDTOs);
}

