package com.zopa.credit.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.valueOf;

@Service
public class FixedPricePercentageRatePaymentCalculator {

    public static BigDecimal MONTH_IN_YEAR = valueOf(12);

    @Autowired
    MathContext mc;

    public BigDecimal calculateRate(BigDecimal totalWithPercents, BigDecimal total, int monthCount) {

        BigDecimal paymentForCredit = totalWithPercents.subtract(total);

        return paymentForCredit.multiply(valueOf(2), mc)
                .divide(total.
                        multiply(valueOf(monthCount + 1), mc), mc)
                .multiply(MONTH_IN_YEAR);
    }

}
