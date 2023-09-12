package com.aspire.aspirelite.model;

import lombok.Data;

@Data
public class CustomerLoanCreationRequest {

    private long term;

    LoanAmount loanAmount;

}
