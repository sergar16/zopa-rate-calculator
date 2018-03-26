package com.zopa.credit.calculator.service.validation;

import com.zopa.credit.calculator.config.PropertiesTestConfig;
import com.zopa.credit.calculator.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//todo: replace with test props
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InputValidationService.class,
        PropertiesTestConfig.class})
@ActiveProfiles("test")
class InputValidationServiceTest {

    @Autowired
    InputValidationService inputValidationService;

    private static Stream<Arguments> createAmountsWithExpectedValidationResult() {
        return Stream.of(
                Arguments.of("1000", true),
                Arguments.of("15000", true),
                Arguments.of("5000", true),
                Arguments.of("1100", true),
                Arguments.of("900", false),
                Arguments.of("15100", false),
                Arguments.of("1050", false),
                Arguments.of("1460", false));
    }
    @ParameterizedTest
    @MethodSource("createAmountsWithExpectedValidationResult")
    //todo : rename all test to "should convention"
    void test_validateAmountToBorrow(String amountToBorrow, boolean expected) {

        boolean result = inputValidationService.validateAmountToBorrow(amountToBorrow);

        assertThat(result, is(expected));
    }

    @Test
    void shouldThrowExceptionWhenInvalidInputNull(){
        //when
        Executable validateInputNull = () -> inputValidationService.validateAmountToBorrow(null);

        //then
        assertThrows(InvalidInputException.class, validateInputNull);
    }

    @Test
    void shouldThrowExceptionWhenInvalidInputBlank(){
        //when
        Executable validateInputBlank = () -> inputValidationService.validateAmountToBorrow("");

        //then
        assertThrows(InvalidInputException.class, validateInputBlank);
    }

    @Test
    void shouldThrowExceptionWhenInvalidInputNotADigit(){
        //when
        Executable validateInputNotDigit = () -> inputValidationService.validateAmountToBorrow("123k34");

        //then
        assertThrows(InvalidInputException.class, validateInputNotDigit);
    }
}