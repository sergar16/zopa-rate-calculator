package com.zopa.credit.calculator.util;

import com.zopa.credit.calculator.model.Lender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TestDataProvider {

    public List<Lender> getLendersValidUniqueTestList() {
        return Arrays.asList(new Lender("Bob", BigDecimal.valueOf(0.075), 640),
                new Lender("Jane", BigDecimal.valueOf(0.069), 480),
                new Lender("Fred", BigDecimal.valueOf(0.071), 520),
                new Lender("Mary", BigDecimal.valueOf(0.104), 170),
                new Lender("John", BigDecimal.valueOf(0.081), 320),
                new Lender("Dave", BigDecimal.valueOf(0.074), 140),
                new Lender("Angela", BigDecimal.valueOf(0.071), 60)
        );

    }

    public List<Lender> getLendersInvalidNonUniqeuTestList() {
        List<Lender> validLendersUniqList= getLendersValidUniqueTestList();
        List<Lender> invalidLendersNonUniqList =new ArrayList<>(validLendersUniqList);
        invalidLendersNonUniqList.add(validLendersUniqList.get(0));
        return invalidLendersNonUniqList;
    }
}
