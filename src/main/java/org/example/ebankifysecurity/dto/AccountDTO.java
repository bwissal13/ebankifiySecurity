package org.example.ebankifysecurity.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ebankifysecurity.enums.AccountStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private Long id;

    @NotBlank(message = "Le numéro de compte est obligatoire.")
    private String accountNumber;

    @NotNull(message = "Le solde est obligatoire.")
    @PositiveOrZero(message = "Le solde ne peut pas être négatif.")
    private double balance;

    @NotNull(message = "Le statut du compte est obligatoire.")
    private AccountStatus status;

    private Long userId;
}
