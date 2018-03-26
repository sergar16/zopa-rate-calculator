package com.zopa.credit.calculator.view;

import com.zopa.credit.calculator.config.PropertiesConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PropertiesConfig.class,
        CommandLineResultRenderer.class})
@ActiveProfiles("test")
class CommandLineResultRendererTest {

    @Autowired
    CommandLineResultRenderer commandLineResultRenderer;

    @Test
    void shouldRenderValidResult() {
        //given
        CalculationResult calculationResult = new CalculationResult(
                100,
                valueOf(0.8),
                valueOf(1300.902),
                valueOf(49.9)
        );

        //when
        String renderResult = commandLineResultRenderer.render(calculationResult);

        //expected
        String expected = "Requested amount : £100\n" +
                "Rate: 0.80%\n" +
                "Monthly repayment: £1300.91\n" +
                "Total repayment: £49.90";

        //then
        assertEquals(renderResult, expected);
    }

    @Test
    void shouldThrowExceptionInValidResult() {
        //given
        CalculationResult calculationResult = new CalculationResult(
                100,
                null,
                valueOf(1300.902),
                valueOf(49.9)
        );

        //when
        String renderResult = commandLineResultRenderer.render(calculationResult);

        //then   //when
        //        Executable validateInputNotDigit = () -> inputValidationService.validateAmountToBorrow("123k34");
        //
        //        //then
        //        assertThrows(InvalidInputException.class, validateInputNotDigit);
    }

    @Test
    void round() {
    }
}