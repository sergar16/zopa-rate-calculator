package com.zopa.credit.calculator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.MathContext;
import java.math.RoundingMode;

@Configuration
public class MathConfiguration {
    //todo : replace with MyBigDecimal
    //set scale and round diff
    @Value("${zopa.rate.calculator.default.calculation.round}")
    private Integer round;

    @Bean
    public MathContext getMathContext() {
        return new MathContext(round, RoundingMode.UP);
    }

}
