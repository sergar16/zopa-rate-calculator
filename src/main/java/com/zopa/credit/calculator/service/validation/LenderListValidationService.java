package com.zopa.credit.calculator.service.validation;

import com.zopa.credit.calculator.model.Lender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class LenderListValidationService {

    private static  <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public boolean validateAllLendersAreUniqueByName(List<Lender> lenders) {
        return lenders.stream().allMatch(distinctByKey(Lender::getName));
    }
}
