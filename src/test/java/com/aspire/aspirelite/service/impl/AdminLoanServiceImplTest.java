package com.aspire.aspirelite.service.impl;

import com.aspire.aspirelite.model.entity.Loan;
import com.aspire.aspirelite.repository.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminLoanServiceImplTest {

    @InjectMocks
    AdminLoanServiceImpl adminLoanService;

    @Mock
    LoanRepository loanRepository;

    @Test
    public void approveCustomerLoan() {

        Loan l = new Loan();
        l.setCurrency("SGD");
        l.setTerm(3);
        l.setPaymentValue(BigDecimal.valueOf(5));
        l.setLoanId( BigDecimal.valueOf(1));
        l.setCreatedBy("Tester");
        l.setModifiedBy("Garima");
        l.setCustomerUsername("Garima");
        l.setPaymentState("APPROVED");
        l.setCreatedTime(LocalDateTime.now());
        l.setModifiedTime(LocalDateTime.now().plusDays(1));

        Mockito.when(loanRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(loanRepository.findById(Mockito.any())).thenReturn(Optional.of(l));

        adminLoanService.approveCustomerLoan(BigDecimal.valueOf(1));
        assertEquals(1,l.getLoanId().intValue());
    }
}