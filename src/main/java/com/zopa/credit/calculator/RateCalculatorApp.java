package com.zopa.credit.calculator;

import com.zopa.credit.calculator.service.FlowService;
import com.zopa.credit.calculator.service.validation.InputValidationService;
import com.zopa.credit.calculator.view.CalculationResult;
import com.zopa.credit.calculator.view.CommandLineResultRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RateCalculatorApp implements CommandLineRunner {

    @Autowired
    private InputValidationService inputValidationService;

    @Autowired
    private CommandLineResultRenderer commandLineResultRenderer;

    @Autowired
    private FlowService flowService;

    @Value("${zopa.rate.calculator.default.monthCount}")
    private Integer defaultMonthCount;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(RateCalculatorApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String fileName = args[0];
        String amount = args[1];

        if (inputValidationService.validateAmountToBorrow(amount)) {

            int integerAmount = Integer.parseInt(amount);

            CalculationResult result = flowService.process(fileName, integerAmount, defaultMonthCount);
            System.out.println(commandLineResultRenderer.render(result));
        } else {
            System.out.println("Input is not valid");
        }
    }
}