package com.aspire.aspirelite.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanAmount {

    private BigDecimal value;

    private String currency;
}
