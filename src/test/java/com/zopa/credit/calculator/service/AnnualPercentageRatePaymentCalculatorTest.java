package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.config.MathConfiguration;
import com.zopa.credit.calculator.config.PropertiesTestConfig;
import com.zopa.credit.calculator.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AnnualPercentageRatePaymentCalculator.class,
        MathConfiguration.class, PropertiesTestConfig.class})
@ActiveProfiles("test")
class AnnualPercentageRatePaymentCalculatorTest {

    @Autowired
    private AnnualPercentageRatePaymentCalculator paymentCalculator;

    @Test
    void test_FRED_DATA_credit_payment_calculator() {
        //given
        BigDecimal totalAmount = valueOf(520);
        BigDecimal rate = valueOf(0.071);
        int monthCount = 36;

        //when
        BigDecimal result = paymentCalculator.calculateCreditPayment(totalAmount, rate, monthCount);

        //then
        TestUtil.compareBigDecimalRounded(result, "576.92");
    }


    @Test
    void test_JANE_DATA_credit_payment_calculator() {
        //given
        BigDecimal totalAmount = valueOf(480);
        BigDecimal rate = valueOf(0.069);
        int monthCount = 36;

        //when
        BigDecimal result = paymentCalculator.calculateCreditPayment(totalAmount, rate, monthCount);

        //then
        TestUtil.compareBigDecimalRounded(result, "531.06");
    }

}