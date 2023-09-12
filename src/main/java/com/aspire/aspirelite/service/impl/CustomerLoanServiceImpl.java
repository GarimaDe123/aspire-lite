package com.aspire.aspirelite.service.impl;

import com.aspire.aspirelite.model.*;
import com.aspire.aspirelite.model.entity.Loan;
import com.aspire.aspirelite.model.entity.LoanRepayment;
import com.aspire.aspirelite.repository.LoanRepaymentRepository;
import com.aspire.aspirelite.repository.LoanRepository;
import com.aspire.aspirelite.service.CustomerLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerLoanServiceImpl implements CustomerLoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanRepaymentRepository loanRepaymentRepository;

    @Override
    public CustomerLoanCreationResponse createCustomerLoan(CustomerLoanCreationRequest customerLoanRequest, String username) {
        CustomerLoanCreationResponse customerLoanResponse = new CustomerLoanCreationResponse();
        customerLoanResponse.setRepaymentSchedule(createRepaymentSchedule(customerLoanRequest));
        saveLoanToDB(customerLoanRequest, customerLoanResponse, username);
        return customerLoanResponse;
    }

    @Override
    public CustomerLoanViewResponse viewCustomerLoan(String username) {
        CustomerLoanViewResponse customerLoanViewResponse = new CustomerLoanViewResponse();
        List<Loan> loans = loanRepository.findByCustomerUsername(username);
        customerLoanViewResponse.setLoans(loans);
        return customerLoanViewResponse;
    }

    @Override
    public CustomerLoanPayResponse payCustomerLoan(CustomerLoanPayRequest customerLoanPayRequest, Principal principal) {
        CustomerLoanPayResponse customerLoanPayResponse = new CustomerLoanPayResponse();
        if(updateLoanDetails(customerLoanPayRequest)){
            customerLoanPayResponse.setPaymentMessage("payment received");
        }else
            customerLoanPayResponse.setPaymentMessage("payment failed");
        return customerLoanPayResponse;
    }

    private boolean updateLoanDetails(CustomerLoanPayRequest customerLoanPayRequest) {
        if(loanRepository.existsById(customerLoanPayRequest.getLoanId())){
            Optional<Loan> loan = loanRepository.findById(customerLoanPayRequest.getLoanId());
            if(loan.isPresent() &&
                    PaymentState.APPROVED.toString().equalsIgnoreCase(loan.get().getPaymentState())){

                List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByLoanLoanId(loan.get().getLoanId())
                        .stream()
                        .filter(l -> PaymentState.PENDING.toString().equalsIgnoreCase(l.getPaymentState()))
                        .sorted(Comparator.comparing(LoanRepayment::getPaymentDueDate))
                        .collect(Collectors.toList());

                if(!loanRepayments.isEmpty()) {
                    loanRepayments.stream().findFirst().get().setPaymentState(PaymentState.PAID.toString());
                    loanRepaymentRepository.save(loanRepayments.stream().findFirst().get());
                }
                if(loanRepayments.size()==1) {
                    loan.get().setPaymentState(PaymentState.PAID.toString());
                    loanRepository.save(loan.get());
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void saveLoanToDB(CustomerLoanCreationRequest customerLoanRequest, CustomerLoanCreationResponse customerLoanResponse, String username) {
        Loan loan = new Loan();
        loan.setPaymentState(PaymentState.PENDING.toString());
        loan.setCurrency(customerLoanRequest.getLoanAmount().getCurrency());
        loan.setPaymentValue(customerLoanRequest.getLoanAmount().getValue());
        loan.setTerm(customerLoanRequest.getTerm());
        loan.setCustomerUsername(username);
        loan.setCreatedBy(Role.CUSTOMER.toString());
        loan.setModifiedBy(Role.CUSTOMER.toString());
        loan.setCreatedTime(LocalDateTime.now());
        loan.setModifiedTime(LocalDateTime.now());
        loanRepository.save(loan);

        LoanRepayment loanRepayment;
        for(RepaymentSchedule repaymentSchedule : customerLoanResponse.getRepaymentSchedule()){
            loanRepayment = new LoanRepayment();
            loanRepayment.setLoan(loan);
            loanRepayment.setCurrency(repaymentSchedule.getLoanAmount().getCurrency());
            loanRepayment.setPaymentDueDate(repaymentSchedule.getPaymentDueDate());
            loanRepayment.setPaymentValue(repaymentSchedule.getLoanAmount().getValue());
            loanRepayment.setPaymentState(PaymentState.PENDING.toString());
            loanRepayment.setCreatedBy(Role.SYSTEM.toString());
            loanRepayment.setModifiedBy(Role.SYSTEM.toString());
            loanRepayment.setCreatedTime(LocalDateTime.now());
            loanRepayment.setModifiedTime(LocalDateTime.now());
            loanRepaymentRepository.save(loanRepayment);
        }
    }

    private List<RepaymentSchedule> createRepaymentSchedule(CustomerLoanCreationRequest customerLoanRequest) {
        List<RepaymentSchedule> repaymentSchedules = new ArrayList<>();
        long tenure = customerLoanRequest.getTerm();
        LoanAmount loanAmount = getLoanAmount(customerLoanRequest);
        RepaymentSchedule repaymentSchedule;
        while (tenure > 0) {
            tenure--;
            repaymentSchedule = new RepaymentSchedule();
            repaymentSchedule.setPaymentDueDate(
                    LocalDate.now().plusWeeks(customerLoanRequest.getTerm()-tenure));
            repaymentSchedule.setLoanAmount(loanAmount);
            repaymentSchedule.setState(PaymentState.PENDING);
            repaymentSchedules.add(repaymentSchedule);
        }

        return repaymentSchedules;
    }

    private static LoanAmount getLoanAmount(CustomerLoanCreationRequest customerLoanRequest) {
        LoanAmount loanAmount = new LoanAmount();
        loanAmount.setCurrency(
                customerLoanRequest.getLoanAmount().getCurrency());
        loanAmount.setValue(
                customerLoanRequest.getLoanAmount().getValue()
                        .divide(BigDecimal.valueOf(
                                customerLoanRequest.getTerm()),5, RoundingMode.HALF_UP));
        return loanAmount;
    }
}
