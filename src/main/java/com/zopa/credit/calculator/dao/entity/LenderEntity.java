package com.zopa.credit.calculator.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonPropertyOrder({"Lender","Rate", "Available"})
public class LenderEntity {
    @JsonProperty("Lender")
    private String name;

    @JsonProperty("Rate")
    private String rate;

    @JsonProperty("Available")
    private Integer amount;

    LenderEntity(){}

    public LenderEntity(String name, String rate, Integer amount) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LenderEntity lender = (LenderEntity) o;
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
