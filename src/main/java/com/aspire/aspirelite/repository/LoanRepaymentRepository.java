package com.aspire.aspirelite.repository;

import com.aspire.aspirelite.model.entity.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, BigDecimal> {
    List<LoanRepayment> findByLoanLoanId(BigDecimal loanId);
}
