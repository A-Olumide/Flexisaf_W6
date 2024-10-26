package com.A_Olumide.Implementation.of.Service.Classes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {
    @NotBlank(message = "Name is mandatory")
    @Size(min=5, max=50, message = "The length of the customer name should be between 5 and 50")
    private String name;

    @NotBlank(message = "Email address cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
