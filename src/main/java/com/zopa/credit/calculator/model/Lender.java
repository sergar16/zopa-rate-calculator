package com.zopa.credit.calculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Lender {
    private final String name;
    private final BigDecimal rate;
    private final Integer amount;

    //todo : replace to static creation methods
    public Lender(String name, BigDecimal rate, Integer amount) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Integer getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lender lender = (Lender) o;
        return Objects.equals(name, lender.name) &&
                Objects.equals(rate, lender.rate) &&
                Objects.equals(amount, lender.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, amount);
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", amount=" + amount +
                '}';
    }
}
