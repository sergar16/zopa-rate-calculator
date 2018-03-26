package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.config.MathConfiguration;
import com.zopa.credit.calculator.config.PropertiesConfig;
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
@ContextConfiguration(classes = {FixedPricePercentageRatePaymentCalculator.class,
        MathConfiguration.class,
        PropertiesConfig.class})
@ActiveProfiles("test")
class FixedPricePercentageRatePaymentCalculatorTest {

    @Autowired
    private FixedPricePercentageRatePaymentCalculator fixedPricePercentageRatePaymentCalculator;

    @Test
    void test_BASE_TEST_FROM_DESCRIPTION_credit_rate_calculator() {
        //given
        BigDecimal amountToBorrow = valueOf(1000);
        BigDecimal paymentWithPercents = valueOf(1107.92);
        int monthCount = 36;

        //when
        BigDecimal result = fixedPricePercentageRatePaymentCalculator.calculateRate(paymentWithPercents, amountToBorrow, monthCount);

        //then
        TestUtil.compareBigDecimalRounded(result, "0.07");
    }

}