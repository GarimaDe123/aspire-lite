package com.aspire.aspirelite.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "loan")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal loanId;

    private long term;

    @Column(scale = 5)
    private BigDecimal paymentValue;

    private String currency;

    private String paymentState;

    private String customerUsername;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String createdBy;

    private String modifiedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanRepayment> loanRepayment;
}
