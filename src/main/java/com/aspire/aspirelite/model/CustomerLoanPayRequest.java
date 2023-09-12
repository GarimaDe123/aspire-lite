package com.aspire.aspirelite.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerLoanPayRequest {

    private BigDecimal loanId;

    private LoanAmount amount;
}
