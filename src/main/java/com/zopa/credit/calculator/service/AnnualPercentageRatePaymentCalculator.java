package com.zopa.credit.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.valueOf;

@Service
public class AnnualPercentageRatePaymentCalculator {

    private static BigDecimal MONTH_IN_YEAR = valueOf(12);

    @Autowired
    MathContext mc;

    public BigDecimal calculateCreditPayment(BigDecimal total, BigDecimal rate, int monthCount) {
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 1; i <= monthCount; i++) {
            BigDecimal monthlyPayment = calculateMonthPayment(total, rate, monthCount, i);
            sum = sum.add(monthlyPayment);
        }

        return sum;
    }

    private strictfp BigDecimal calculateMonthPayment(BigDecimal total, BigDecimal rate, int monthCount, int currentMonth) {
        BigDecimal totalMainBodyPerMonth = total.divide(valueOf(monthCount), mc);
        BigDecimal ratePerMonth = rate.divide(MONTH_IN_YEAR, mc);

        BigDecimal totalInterestPerMonth = total.multiply(valueOf(monthCount - currentMonth + 1)).multiply(ratePerMonth, mc).divide(valueOf(monthCount), mc);

        return totalInterestPerMonth.add(totalMainBodyPerMonth);
    }


}
