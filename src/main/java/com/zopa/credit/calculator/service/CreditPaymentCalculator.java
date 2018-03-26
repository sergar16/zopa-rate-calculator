package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.math.BigDecimal.valueOf;

@Service
public class CreditPaymentCalculator {

    @Autowired
    AnnualPercentageRatePaymentCalculator paymentCalculator;

    public BigDecimal calculateCreditPayment(List<Loan> lendersMoney, int monthCounts) {
        BigDecimal sum = BigDecimal.ZERO;
        sum.setScale(2, RoundingMode.UP);

        for (Loan loan : lendersMoney) {
            sum = sum.add(paymentCalculator.calculateCreditPayment(
                    valueOf(loan.getAmount()),
                    loan.getLender().getRate(),
                    monthCounts));
        }
        return sum;
    }

//    public BigDecimal calculateMonthly(BigDecimal amount, int monthCount) {
//        amount.setScale(2, RoundingMode.HALF_UP)
//                .divide(valueOf(monthCount),)
//    }
}
