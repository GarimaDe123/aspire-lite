package com.aspire.aspirelite.service.impl;

import com.aspire.aspirelite.model.AdminLoanApprovalResponse;
import com.aspire.aspirelite.model.PaymentState;
import com.aspire.aspirelite.model.Role;
import com.aspire.aspirelite.model.entity.Loan;
import com.aspire.aspirelite.repository.LoanRepository;
import com.aspire.aspirelite.service.AdminLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AdminLoanServiceImpl implements AdminLoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public AdminLoanApprovalResponse approveCustomerLoan(BigDecimal loanId) {
        if(updateLoanStatus(loanId)){
           return adminLoanApprovalResponse("0");
        }
        return adminLoanApprovalResponse("1");
    }

    private AdminLoanApprovalResponse adminLoanApprovalResponse(String code) {
        AdminLoanApprovalResponse adminLoanApprovalResponse = new AdminLoanApprovalResponse();
        if("0".equalsIgnoreCase(code)) {
            adminLoanApprovalResponse.setResponseCode("0");
            adminLoanApprovalResponse.setResponseMessage("Loan successfully approved");
        }else{
            adminLoanApprovalResponse.setResponseCode("1");
            adminLoanApprovalResponse.setResponseMessage(
                    "Either loan does not exist or not in pending state");
        }
        return adminLoanApprovalResponse;
    }

    private boolean updateLoanStatus(BigDecimal loanId) {
        if(loanRepository.existsById(loanId)){
            Optional<Loan> loan = loanRepository.findById(loanId);
            if(loan.isPresent() && PaymentState.PENDING.toString().equalsIgnoreCase(
                    loan.get().getPaymentState())){
                loan.get().setPaymentState(PaymentState.APPROVED.toString());
                loan.get().setModifiedBy(Role.ADMIN.toString());
                loan.get().setModifiedTime(LocalDateTime.now());
                loanRepository.save(loan.get());
                return true;
            }
        }
        return false;
    }
}
