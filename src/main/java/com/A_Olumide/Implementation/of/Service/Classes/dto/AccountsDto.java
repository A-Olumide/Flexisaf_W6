package com.A_Olumide.Implementation.of.Service.Classes.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account Number must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number must be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account Type cannot be null or empty")
    private String accountType;

}
