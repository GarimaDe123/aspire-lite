package com.aspire.aspirelite.model;

import com.aspire.aspirelite.model.entity.Loan;
import lombok.Data;

import java.util.List;

@Data
public class CustomerLoanViewResponse {

    List<Loan> loans;
}
