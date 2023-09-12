package com.aspire.aspirelite.service;

import com.aspire.aspirelite.model.AdminLoanApprovalResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public interface AdminLoanService {

    public AdminLoanApprovalResponse approveCustomerLoan(BigDecimal loanId);
}
