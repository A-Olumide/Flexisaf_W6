package com.A_Olumide.Implementation.of.Service.Classes.serv;

import com.A_Olumide.Implementation.of.Service.Classes.dto.AccountsDto;
import com.A_Olumide.Implementation.of.Service.Classes.dto.CustomerDto;

public interface IService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String email);

    boolean updateAccountDetails(CustomerDto customerDto);

    boolean deleteAccount(String email);

   Long checkAccountNumber(String email);



}
