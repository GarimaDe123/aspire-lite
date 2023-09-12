package com.aspire.aspirelite.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RepaymentSchedule {

    private LocalDate paymentDueDate;
    private LoanAmount loanAmount;
    private PaymentState state;
}
