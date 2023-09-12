package com.aspire.aspirelite.controller;

import com.aspire.aspirelite.model.*;
import com.aspire.aspirelite.service.CustomerLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/api/v1/customer/loan")
public class CustomerLoanController {

    @Autowired
    private CustomerLoanService customerLoanService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerLoanCreationResponse createLoan(@RequestBody CustomerLoanCreationRequest customerLoanRequest, Principal principal){
        return customerLoanService.createCustomerLoan(customerLoanRequest, principal.getName());
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerLoanViewResponse viewLoans(Principal principal){
        return customerLoanService.viewCustomerLoan(principal.getName());
    }

    @PostMapping("/pay")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CustomerLoanPayResponse payLoan(@RequestBody CustomerLoanPayRequest customerLoanPayRequest, Principal principal){
        return customerLoanService.payCustomerLoan(customerLoanPayRequest, principal);
    }
}
