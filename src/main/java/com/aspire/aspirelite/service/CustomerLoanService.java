package com.aspire.aspirelite.service;

import com.aspire.aspirelite.model.*;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public interface CustomerLoanService {

    CustomerLoanCreationResponse createCustomerLoan(CustomerLoanCreationRequest customerLoanRequest, String username);

    CustomerLoanViewResponse viewCustomerLoan(String username);

    CustomerLoanPayResponse payCustomerLoan(CustomerLoanPayRequest customerLoanPayRequest, Principal principal);
}
