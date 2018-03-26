package com.zopa.credit.calculator.dao;

import com.zopa.credit.calculator.dao.entity.LenderEntity;
import com.zopa.credit.calculator.exception.CSVFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
 class LenderDaoTest {

    private LenderDao lenderDao=new LenderDao();

    private ClassLoader classLoader = getClass().getClassLoader();

    @Test
     void whenValidFileShouldLoad() {

        //given
        String pathValidFile = classLoader.getResource("valid.csv").getFile();

        //when
        List<LenderEntity> result = lenderDao.getLenders(pathValidFile);

        //then
        LenderEntity lenderEntity = new LenderEntity("Bob", "0.075", 640);

        assertThat(result.get(0), is(lenderEntity));

        assertThat(result, hasItems(new LenderEntity("John", "0.081", 320)));

        assertThat(result.size(), is(7));
    }

    @Test
     void whenInvalidFileShouldThrowException() {
        //given
        String pathInvalidFile = classLoader.getResource("invalid.csv").getFile();

        //when
        Executable loadInvalidFile = () -> lenderDao.getLenders(pathInvalidFile);

        //then
        assertThrows(CSVFileException.class, loadInvalidFile);
    }

    @Test
     void whenInvalidFilePathShouldThrowException() {
        //given
        String invalidPath = "invalid/resources/valid.csv";

        //when
        Executable loadInvalidFile = () -> lenderDao.getLenders(invalidPath);

        //then
        assertThrows(CSVFileException.class, loadInvalidFile);
    }

    //todo:add BigDecimalValidation


}