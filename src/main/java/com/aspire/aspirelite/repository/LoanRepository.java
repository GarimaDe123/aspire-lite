package com.aspire.aspirelite.repository;

import com.aspire.aspirelite.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, BigDecimal> {
    List<Loan> findByCustomerUsername(String username);
}
