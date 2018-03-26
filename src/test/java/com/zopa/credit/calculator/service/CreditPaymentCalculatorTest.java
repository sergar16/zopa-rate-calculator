package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.config.MathConfiguration;
import com.zopa.credit.calculator.config.PropertiesConfig;
import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.model.Loan;
import com.zopa.credit.calculator.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.math.BigDecimal.valueOf;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CreditPaymentCalculator.class,
        AnnualPercentageRatePaymentCalculator.class,
        MathConfiguration.class,
        PropertiesConfig.class})
@ActiveProfiles("test")
class CreditPaymentCalculatorTest {

    @Autowired
    private CreditPaymentCalculator creditPaymentCalculator;

    @Test
    void test_TEST_FROM_DESCRIPTION_calculate_credit_payment() {
        //given
        ArrayList<Loan> borrows = new ArrayList<>();

        borrows.add(new Loan(new Lender("Jane", valueOf(0.069), 480), 480));
        borrows.add(new Loan(new Lender("Fred", valueOf(0.071), 520), 520));

        //when
        BigDecimal result = creditPaymentCalculator.calculateCreditPayment(borrows, 36);

        //then
        TestUtil.compareBigDecimalRounded(result, "1107.98");
    }


    @Test
    void test_calculate_credit_payment() {
        //given
        ArrayList<Loan> borrows = new ArrayList<>();

        borrows.add(new Loan(new Lender("Jane", valueOf(0.069), 300), 300));
        borrows.add(new Loan(new Lender("Fred", valueOf(0.071), 300), 300));
        borrows.add(new Loan(new Lender("Fredy", valueOf(0.11), 400), 400));

        //when
        BigDecimal result = creditPaymentCalculator.calculateCreditPayment(borrows, 36);

        //then
        TestUtil.compareBigDecimalRounded(result, "1132.58");
    }
}