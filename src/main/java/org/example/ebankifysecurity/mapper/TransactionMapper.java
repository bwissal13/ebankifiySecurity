package org.example.ebankifysecurity.mapper;

import org.example.ebankifysecurity.dto.TransactionDTO;
import org.example.ebankifysecurity.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toDTO(Transaction transaction);
    Transaction toEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> toDTOList(List<Transaction> transactions);
    List<Transaction> toEntityList(List<TransactionDTO> transactionDTOs);
}
