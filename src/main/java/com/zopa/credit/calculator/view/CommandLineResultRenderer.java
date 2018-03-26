package com.zopa.credit.calculator.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CommandLineResultRenderer {

//    private static Map<Predicate<Consumer<Object>>> validationsList= Arrays
//            .asList();
//    Predicate<Consumer<Object>> predicate = (CalculationResult::getRate) -> {}

    @Value("${zopa.rate.calculator.default.output.round}")
    private Integer round;

    public String render(CalculationResult calculationResult) {
        int requestedAmountString = calculationResult.getRequestedAmount();
        BigDecimal rateString = round(calculationResult.getRate());
        BigDecimal creditPaymentString = round(calculationResult.getCreditPayment());
        BigDecimal monthlyPaymentString = round(calculationResult.getMonthlyPayment());

//        ObjectUtils()
        return "Requested amount : £" + requestedAmountString
                + "\nRate: " + rateString + "%"
                + "\nMonthly repayment: £" + creditPaymentString
                + "\nTotal repayment: £" + monthlyPaymentString;
    }

    private MathContext getMathContext() {
        return new MathContext(round, RoundingMode.UP);
    }

    //todo : rewrite functional + change rounding
    public BigDecimal round(BigDecimal bigDecimal) {
        return bigDecimal.setScale(round, RoundingMode.CEILING);
    }

    private static boolean validateData(CalculationResult calculationResult){
        return true;
    }


}
