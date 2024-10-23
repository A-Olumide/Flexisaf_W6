package com.A_Olumide.Implementation.of.Service.Classes.serv.impl;

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
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(customer.getEmail());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given email "+customerDto.getEmail());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));


    }
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType("SAVINGS");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setAccountsDto(modelMapper.map(accounts, AccountsDto.class));

        return customerDto;

    }
}
