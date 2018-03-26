package com.zopa.credit.calculator.mapper;

import com.zopa.credit.calculator.dao.entity.LenderEntity;
import com.zopa.credit.calculator.model.Lender;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LenderMapper {

    private static Function<LenderEntity, Lender> lendersMapping = lenderEntity ->
            new Lender(
                    lenderEntity.getName(),
                    new BigDecimal(lenderEntity.getRate()),
                    lenderEntity.getAmount());


    public Lender map(LenderEntity lenderEntity) {
        return lendersMapping.apply(lenderEntity);
    }

    public List<Lender> mapList(List<LenderEntity> lenderEntities) {
        return lenderEntities.stream()
                .map(lendersMapping)
                .collect(Collectors.toList());
    }
}
