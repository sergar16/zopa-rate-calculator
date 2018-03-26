package com.zopa.credit.calculator.dao;

import com.zopa.credit.calculator.dao.entity.LenderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LenderDao {

    private CSVLoader<LenderEntity> csvDataLoader = new CSVLoader<>();

    public List<LenderEntity> getLenders(String lendersFile) {
        return csvDataLoader.loadObjectList(LenderEntity.class, lendersFile);
    }

}
