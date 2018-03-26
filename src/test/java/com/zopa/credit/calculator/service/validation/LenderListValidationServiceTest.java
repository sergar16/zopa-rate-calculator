package com.zopa.credit.calculator.service.validation;

import com.zopa.credit.calculator.model.Lender;
import com.zopa.credit.calculator.util.TestDataProvider;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        Assert.assertTrue(isValid);
    }

    @Test
    void validateNotUniqueByNameLendersList() {
        //given
        List<Lender> invalidLendersNonUniqueList = testDataProvider.getLendersInvalidNonUniqeuTestList();

        //when
        boolean isValid = lenderListValidationService.validateAllLendersAreUniqueByName(invalidLendersNonUniqueList);

        //then
        Assert.assertFalse(isValid);
    }


}