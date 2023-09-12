package com.aspire.aspirelite.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_repayment")
@Data
public class LoanRepayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal loanRepaymentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loanReferenceId", referencedColumnName = "loanId")
    private Loan loan;

    private LocalDate paymentDueDate;

    @Column(scale = 5)
    private BigDecimal paymentValue;

    private String currency;

    private String paymentState;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String createdBy;

    private String modifiedBy;
}
