package com.zopa.credit.calculator.service.validation;

import com.zopa.credit.calculator.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Service
public class InputValidationService {

    @Value("${zopa.rate.calculator.default.amount.high}")
    private Integer highRange;

    @Value("${zopa.rate.calculator.default.amount.low}")
    private Integer lowRange;

    @Value("${zopa.rate.calculator.default.amount.step}")
    private Integer step;

    public boolean validateAmountToBorrow(String amountToBorrowInputString) {

        if (StringUtils.isEmpty(amountToBorrowInputString)
                || !isDigit.apply(amountToBorrowInputString)) throw new InvalidInputException("Wrong input - should be digit");

        int amountToBorrow = Integer.parseInt(amountToBorrowInputString);

        return amountToBorrow >= lowRange
                && amountToBorrow <= highRange
                && amountToBorrow % step == 0;
    }

    private static Function<String, Boolean> isDigit = string -> string.chars().allMatch(Character::isDigit);

}
