package com.aspire.aspirelite.controller;

import com.aspire.aspirelite.model.AdminLoanApprovalResponse;
import com.aspire.aspirelite.service.AdminLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/api/v1/admin/loan")
public class AdminLoanController {

    @Autowired
    private AdminLoanService adminLoanService;

    @PutMapping("/approve/{loanId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public AdminLoanApprovalResponse loanApprove(@PathVariable BigDecimal loanId){
        return adminLoanService.approveCustomerLoan(loanId);
    }

}
