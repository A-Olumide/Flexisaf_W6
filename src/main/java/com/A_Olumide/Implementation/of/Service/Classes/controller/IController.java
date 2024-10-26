package com.A_Olumide.Implementation.of.Service.Classes.controller;

import com.A_Olumide.Implementation.of.Service.Classes.constants.AccountsConstants;
import com.A_Olumide.Implementation.of.Service.Classes.dto.CustomerDto;
import com.A_Olumide.Implementation.of.Service.Classes.dto.ResponseDto;
import com.A_Olumide.Implementation.of.Service.Classes.serv.IService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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

    @PostMapping("/customers")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/accounts/details")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Email(message = "Email address should be a valid value")
                                                           String email) {
        CustomerDto customerDto = iService.fetchAccount(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);

    }
@GetMapping("/accounts/number")
    public ResponseEntity<Long> checkAccountNumber(@RequestParam
                                                          @Email(message = "Email address should be a valid value")
                                                          String email){
       Long accountNumber = iService.checkAccountNumber(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountNumber);
    }

    @PutMapping("/accounts/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iService.updateAccountDetails(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @DeleteMapping("/accounts/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Email(message = "Email address should be a valid value")
                                                                String email) {
        boolean isDeleted = iService.deleteAccount(email);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
