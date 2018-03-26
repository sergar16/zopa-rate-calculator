package com.zopa.credit.calculator.service.validation;

import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.util.TestDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LenderListValidationService.class,
                                 TestDataProvider.class})
@ActiveProfiles("test")
class LenderListValidationServiceTest {

    @Autowired
    TestDataProvider testDataProvider;

    @Autowired
    LenderListValidationService lenderListValidationService;

    @Test
    void validateUniqueByNameLendersList() {
        //given
        List<Lender> validLendersUniqueList = testDataProvider.getLendersValidUniqueTestList();

        //when
        boolean isValid = lenderListValidationService.validateAllLendersAreUniqueByName(validLendersUniqueList);

        //then
        assertTrue(isValid);
    }

    @Test
    void validateNotUniqueByNameLendersList() {
        //given
        List<Lender> invalidLendersNonUniqueList = testDataProvider.getLendersInvalidNonUniqeuTestList();

        //when
        boolean isValid = lenderListValidationService.validateAllLendersAreUniqueByName(invalidLendersNonUniqueList);

        //then
        assertFalse(isValid);
    }

    @Test
    void validateEnoughAmountOnMarket(){
        //given
        List<Lender> invalidLendersNonUniqueList = testDataProvider.getLendersValidUniqueTestList();

        //when
        boolean isValid = lenderListValidationService.validateIsEnoughMoneyOnMarket(invalidLendersNonUniqueList,1000);

        //then
        assertTrue(isValid);
    }

    @Test
    void validateNotEnoughAmountOnMarket(){
        //given
        List<Lender> invalidLendersNonUniqueList = testDataProvider.getLendersValidUniqueTestList();

        //when
        boolean isValid = lenderListValidationService.validateIsEnoughMoneyOnMarket(invalidLendersNonUniqueList,20000);

        //then
        assertFalse(isValid);
    }


}