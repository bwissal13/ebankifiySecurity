package org.example.ebankifysecurity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ebankifysecurity.enums.TransactionType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private Long id;

    @NotNull(message = "Le montant est obligatoire.")
    @Positive(message = "Le montant doit être supérieur à 0.")
    private double amount;

    @NotNull(message = "Le type de transaction est obligatoire.")
    private TransactionType type;

    @NotNull(message = "L'ID du compte est obligatoire.")
    private Long accountId;

    private Long approvedBy;

    private LocalDateTime timestamp;


}
