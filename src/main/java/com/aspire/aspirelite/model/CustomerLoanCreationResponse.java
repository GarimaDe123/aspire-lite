package com.aspire.aspirelite.model;

import lombok.Data;
import java.util.List;

@Data
public class CustomerLoanCreationResponse {

    private List<RepaymentSchedule> repaymentSchedule;
}
