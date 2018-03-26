package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.model.Loan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlanBuilder {
    private final static Comparator<Lender> lenders_choosing_algorithm = (l1, l2) -> {
        int compareRate = l1.getRate().compareTo(l2.getRate());

        if (compareRate == 0) {
            return l2.getAmount().compareTo(l1.getAmount());
        } else return compareRate;
    };


    public List<Loan> buildPlan(List<Lender> marketData, final int amount) {
        List<Loan> plan = new ArrayList<>();
        int sum = 0;

        marketData.sort(lenders_choosing_algorithm);

        for (Lender lender : marketData) {
            int currenSum = sum + lender.getAmount();
            if (currenSum >= amount) {
                Loan loan = new Loan(lender, lender.getAmount() - (amount - currenSum));
                plan.add(loan);

                break;
            } else {
                Loan loan = new Loan(lender);
                plan.add(loan);
                sum += lender.getAmount();
            }
        }

        return plan;
    }
}
