package com.zopa.credit.calculator.exception;

public class RateCalculatorAppException extends RuntimeException {

    public RateCalculatorAppException(Throwable cause) {
        super(cause);
    }

    public RateCalculatorAppException(String message) {
        super(message);
    }

}
