package com.zopa.credit.calculator.view;

import java.math.BigDecimal;
import java.util.Objects;

public final class CalculationResult {

    private final int requestedAmount;
    private final BigDecimal rate;
    private final BigDecimal creditPayment;
    private final BigDecimal monthlyPayment;

    public CalculationResult(int requestedAmount, BigDecimal rate, BigDecimal creditPayment, BigDecimal monthlyPayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.creditPayment = creditPayment;
        this.monthlyPayment = monthlyPayment;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getCreditPayment() {
        return creditPayment;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResult that = (CalculationResult) o;
        return requestedAmount == that.requestedAmount &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(creditPayment, that.creditPayment) &&
                Objects.equals(monthlyPayment, that.monthlyPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, creditPayment, monthlyPayment);
    }
}
