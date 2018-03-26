package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.model.Loan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PlanBuilder.class)
@ActiveProfiles("test")
@DisplayName("Building a plan testing")
public class PlanBuilderTest {

    @Autowired
    private PlanBuilder planBuilder;

    @Test
    void test_BASE_TEST_FROM_DESCRIPTION_buildPlan() {

        //given
        ArrayList<Lender> marketData = new ArrayList<>();

        marketData.add(new Lender("Bob", BigDecimal.valueOf(0.075), 640));
        Lender jane = new Lender("Jane", BigDecimal.valueOf(0.069), 480);
        marketData.add(jane);
        Lender fred = new Lender("Fred", BigDecimal.valueOf(0.071), 520);
        marketData.add(fred);
        marketData.add(new Lender("Mary", BigDecimal.valueOf(0.104), 170));
        marketData.add(new Lender("John", BigDecimal.valueOf(0.081), 320));
        marketData.add(new Lender("Dave", BigDecimal.valueOf(0.074), 140));
        marketData.add(new Lender("Angela", BigDecimal.valueOf(0.071), 60));

        //when
        List<Loan> result = planBuilder.buildPlan(marketData, 1000);

        //expected
        List<Loan> expected = Arrays.asList(new Loan(jane, 480), new Loan(fred, 520));


        //then
        assertThat(result, is(expected));

        assertThat(result, hasItems(new Loan(fred, 520)));

        assertThat(result.size(), is(2));

    }

    void test_lenders_choosing_algorithm() {
        //given
        ArrayList<Lender> marketData = new ArrayList<>();

        Lender bob = new Lender("Bob", BigDecimal.valueOf(0.069), 640);
        Lender jane = new Lender("Jane", BigDecimal.valueOf(0.069), 160);
        Lender fred = new Lender("Fred", BigDecimal.valueOf(0.071), 520);
        Lender mary = new Lender("Mary", BigDecimal.valueOf(0.069), 250);

        marketData.add(bob);
        marketData.add(jane);
        marketData.add(fred);
        marketData.add(mary);

        //when
        List<Loan> result = planBuilder.buildPlan(marketData, 1000);

        //expected
        List<Loan> expected = Arrays.asList(new Loan(bob, 640),
                new Loan(mary, 520),
                new Loan(jane, 250),
                new Loan(jane, 110)
        );

        //then
        assertThat(result.get(0), is(expected));

        assertThat(result, hasItems(new Loan(mary, 520)));

        assertThat(result.size(), is(3));

    }
}