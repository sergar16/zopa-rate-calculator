package com.zopa.credit.calculator.dao;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.zopa.credit.calculator.exception.CSVFileException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVLoader<T> {

    public List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();

            CsvMapper mapper = new CsvMapper();;

            File file = new File(fileName);

            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);

            return readValues.readAll();
        } catch (IOException e) {
            throw new CSVFileException(e);
        }
    }
}
