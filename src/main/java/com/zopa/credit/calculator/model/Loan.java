package com.zopa.credit.calculator.model;

import java.util.Objects;

public class Loan {
    private Lender lender;
    private int amount;

    public Loan(Lender lender, int amount) {
        this.lender = lender;
        this.amount = amount;
    }

    public Loan(Lender lender) {
        this.lender = lender;
        this.amount = lender.getAmount();
    }

    public Lender getLender() {
        return lender;
    }

    public void setLender(Lender lender) {
        this.lender = lender;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return amount == loan.amount &&
                Objects.equals(lender, loan.lender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lender, amount);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "lender=" + lender +
                ", amount=" + amount +
                '}';
    }
}
