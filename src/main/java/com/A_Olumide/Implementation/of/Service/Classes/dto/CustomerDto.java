package com.A_Olumide.Implementation.of.Service.Classes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5, max=50, message = "The length of the customer name should be between 5 and 50")
    private String name;

    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}