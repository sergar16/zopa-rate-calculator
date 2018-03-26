package com.zopa.credit.calculator.mapper;

import com.zopa.credit.calculator.dao.entity.LenderEntity;
import com.zopa.credit.calculator.model.Lender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LenderMapper.class)
@ActiveProfiles("test")
class LenderMapperTest {

    @Autowired
    private LenderMapper lenderMapper;

    @Test
    void test_map() {
        //given
        LenderEntity lenderEntity = new LenderEntity("Bob", "0.075", 640);

        //when
        Lender result = lenderMapper.map(lenderEntity);

        //expected
        Lender expected = new Lender("Bob", BigDecimal.valueOf(0.075), 640);

        //then
        assertThat(result, is(expected));
    }

    @Test
    void test_mapList() {
    }
}