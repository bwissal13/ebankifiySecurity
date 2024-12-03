package org.example.ebankifysecurity.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class LoginRequestDTO implements Serializable {
    private String email;
    private String password;
}