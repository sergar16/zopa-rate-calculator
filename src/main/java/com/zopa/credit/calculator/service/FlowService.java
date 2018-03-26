package com.zopa.credit.calculator.service;

import com.zopa.credit.calculator.dao.LenderDao;
import com.zopa.credit.calculator.dao.entity.LenderEntity;
import com.zopa.credit.calculator.exception.InvalidLendersDataException;
import com.zopa.credit.calculator.mapper.LenderMapper;
import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.model.Loan;
import com.zopa.credit.calculator.service.validation.LenderListValidationService;
import com.zopa.credit.calculator.view.CalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static java.math.BigDecimal.ROUND_UP;
import static java.math.BigDecimal.valueOf;

@Service
public class FlowService {

    @Autowired
    LenderDao lenderDao;

    @Autowired
    LenderMapper lenderMapper;

    @Autowired
    LenderListValidationService lenderListValidationService;

    @Autowired
    PlanBuilder planBuilder;

    @Autowired
    AnnualPercentageRatePaymentCalculator annualPercentageRatePaymentCalculator;

    @Autowired
    FixedPricePercentageRatePaymentCalculator fixedPricePercentageRatePaymentCalculator;

    @Autowired
    CreditPaymentCalculator creditPaymentCalculator;

    //todo : remove
    @Autowired
    MathContext mc;

    public CalculationResult process(String filePath, int requestedAmount, int monthCount) {
        List<LenderEntity> lenderEntities = lenderDao.getLenders(filePath);

        List<Lender> lenders = lenderMapper.mapList(lenderEntities);

        if (!lenderListValidationService.validateAllLendersAreUniqueByName(lenders)) throw new InvalidLendersDataException("not uniq");

        List<Loan> loans = planBuilder.buildPlan(lenders, requestedAmount);

        //todo: get bigd out from here change it for int
        BigDecimal calculatedCreditPayment = creditPaymentCalculator.calculateCreditPayment(loans, monthCount);

        BigDecimal monthlyPayment = calculatedCreditPayment.divide(valueOf(monthCount), mc);

        BigDecimal rate = fixedPricePercentageRatePaymentCalculator.calculateRate(calculatedCreditPayment, valueOf(requestedAmount), monthCount);

        monthlyPayment.setScale(2,ROUND_UP);

        BigDecimal creditPayment = monthlyPayment.multiply(valueOf(monthCount));

        return new CalculationResult(
                requestedAmount,
                rate,
                monthlyPayment,
                creditPayment);
    }
}
