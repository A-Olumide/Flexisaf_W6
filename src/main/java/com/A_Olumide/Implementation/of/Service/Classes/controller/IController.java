package com.A_Olumide.Implementation.of.Service.Classes.controller;

import com.A_Olumide.Implementation.of.Service.Classes.constants.AccountsConstant;
import com.A_Olumide.Implementation.of.Service.Classes.dto.CustomerDto;
import com.A_Olumide.Implementation.of.Service.Classes.dto.ResponseDto;
import com.A_Olumide.Implementation.of.Service.Classes.serv.IService;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class IController {

    private final IService iService;

    private IController(IService iService) {
        this.iService = iService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        iService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Email(message = "Email address should be a valid value")
                                                           String email) {
        CustomerDto customerDto = iService.fetchAccount(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);

    }
}
