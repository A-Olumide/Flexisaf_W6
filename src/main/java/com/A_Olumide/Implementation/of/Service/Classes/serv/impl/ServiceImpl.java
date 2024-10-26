package com.A_Olumide.Implementation.of.Service.Classes.serv.impl;

import com.A_Olumide.Implementation.of.Service.Classes.constants.AccountsConstants;
import com.A_Olumide.Implementation.of.Service.Classes.dto.AccountsDto;
import com.A_Olumide.Implementation.of.Service.Classes.dto.CustomerDto;
import com.A_Olumide.Implementation.of.Service.Classes.entity.Accounts;
import com.A_Olumide.Implementation.of.Service.Classes.entity.Customer;
import com.A_Olumide.Implementation.of.Service.Classes.exception.CustomerAlreadyExistsException;
import com.A_Olumide.Implementation.of.Service.Classes.exception.ResourceNotFoundException;
import com.A_Olumide.Implementation.of.Service.Classes.repository.AccountsRepository;
import com.A_Olumide.Implementation.of.Service.Classes.repository.CustomerRepository;
import com.A_Olumide.Implementation.of.Service.Classes.serv.IService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {

    private CustomerRepository customerRepository;

    private AccountsRepository accountsRepository;
    private ModelMapper modelMapper;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = modelMapper.map(customerDto, Customer.class);
        Optional<Customer> optionalCustomer = customerRepository.findByEmailIgnoreCase(customer.getEmail());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given email "+customerDto.getEmail());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createAccountNumber(savedCustomer));
    }
    private Accounts createAccountNumber(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String email) {
        Customer customer = customerRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setAccountsDto(modelMapper.map(accounts, AccountsDto.class));
        return customerDto;
    }

    @Override
    public boolean updateAccountDetails(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            modelMapper.map(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            modelMapper.map(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String email) {
        Customer customer = customerRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    @Override
    public Long checkAccountNumber(String email) {
        Customer customer = customerRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        AccountsDto accountsDto = modelMapper.map(account, AccountsDto.class);
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setAccountsDto(accountsDto);

        return accountsDto.getAccountNumber();
    }
}
