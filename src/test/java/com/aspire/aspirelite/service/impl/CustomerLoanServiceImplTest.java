package com.aspire.aspirelite.service.impl;

import com.aspire.aspirelite.model.*;
import com.aspire.aspirelite.model.entity.Loan;
import com.aspire.aspirelite.model.entity.LoanRepayment;
import com.aspire.aspirelite.repository.LoanRepaymentRepository;
import com.aspire.aspirelite.repository.LoanRepository;
import com.sun.security.auth.UserPrincipal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerLoanServiceImplTest {

    @InjectMocks
    private CustomerLoanServiceImpl customerLoanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanRepaymentRepository loanRepaymentRepository;

    @Test
    public void createCustomerLoan() {

        Loan l = new Loan();
        l.setCurrency("SGD");
        l.setTerm(3);
        l.setPaymentValue(BigDecimal.valueOf(5));
        l.setLoanId( BigDecimal.valueOf(121));
        l.setCreatedBy("Tester");
        l.setModifiedBy("Garima");
        l.setCustomerUsername("Garima");
        l.setPaymentState("APPROVED");
        l.setCreatedTime(LocalDateTime.now());
        l.setModifiedTime(LocalDateTime.now().plusDays(1));

        List<LoanRepayment> loanRepayments = new ArrayList<>();

        LoanRepayment loanRepayment = new LoanRepayment();
        loanRepayment.setLoanRepaymentId(BigDecimal.valueOf(1));
        loanRepayment.setLoan(l);
        loanRepayment.setCurrency("SGD");
        loanRepayment.setCreatedBy("Tester");
        loanRepayment.setCreatedTime(LocalDateTime.now().plusMinutes(10));
        loanRepayment.setModifiedBy("Garima");
        loanRepayments.add(loanRepayment);
        when(loanRepository.existsById(Mockito.any())).thenReturn(true);


        when(loanRepository.save(Mockito.any())).thenReturn(l);
        when(loanRepaymentRepository.save(Mockito.any())).thenReturn(loanRepayment);
        when(loanRepaymentRepository.findByLoanLoanId(Mockito.any())).thenReturn(loanRepayments);
        when( loanRepository.findById(Mockito.any())).thenReturn(Optional.of(l));

        CustomerLoanCreationRequest customerLoanCreationRequest = new CustomerLoanCreationRequest();
        customerLoanCreationRequest.setTerm(3);
        LoanAmount loanAmount = new LoanAmount();
        loanAmount.setCurrency("SGD");
        loanAmount.setValue(BigDecimal.valueOf(10.00));
        customerLoanCreationRequest.setLoanAmount(loanAmount);
        CustomerLoanCreationResponse customerLoan = customerLoanService.createCustomerLoan(customerLoanCreationRequest, "test");

//        assertEquals(customerLoan.(), "payment received" );
        }
    

    @Test
    public void viewCustomerLoan() {

        Loan l = new Loan();
        l.setCurrency("SGD");
        l.setTerm(3);
        l.setPaymentValue(BigDecimal.valueOf(5));
        l.setLoanId( BigDecimal.valueOf(121));
        l.setCreatedBy("Tester");
        l.setModifiedBy("Garima");
        l.setCustomerUsername("Garima");
        l.setPaymentState("APPROVED");
        l.setCreatedTime(LocalDateTime.now());
        l.setModifiedTime(LocalDateTime.now().plusDays(1));

        Loan l2 = new Loan();
        l.setCurrency("SGD");
        l.setTerm(3);
        l.setPaymentValue(BigDecimal.valueOf(5));
        l.setLoanId( BigDecimal.valueOf(122));
        l.setCreatedBy("Tester");
        l.setModifiedBy("Garima");
        l.setCustomerUsername("Garima");
        l.setPaymentState("APPROVED");
        l.setCreatedTime(LocalDateTime.now());
        l.setModifiedTime(LocalDateTime.now().plusDays(1));

        List<Loan> loans = new ArrayList<>();
        loans.add(l);
        loans.add(l2);
        when(loanRepository.findByCustomerUsername(Mockito.any())).thenReturn(loans);
        customerLoanService.viewCustomerLoan("test");
        assertEquals(2, loans.size());
    }

    @Test
    public void payCustomerLoan() {
        CustomerLoanPayRequest customerLoanPayRequest = new CustomerLoanPayRequest();
        customerLoanPayRequest.setLoanId(BigDecimal.valueOf(1));
        Principal principal = new UserPrincipal("test");
        customerLoanService.payCustomerLoan(customerLoanPayRequest,principal);
    }
    
    @Test
    public void payCustomerLoanFailure(){
        when(loanRepository.existsById(Mockito.any())).thenReturn(false);
        Principal principal = new UserPrincipal("test");
        CustomerLoanPayResponse customerLoanPayResponse = customerLoanService.payCustomerLoan(new CustomerLoanPayRequest(), principal);
        assertEquals(customerLoanPayResponse.getPaymentMessage(), "payment failed" );
    }
}