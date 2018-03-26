package com.zopa.credit.calculator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertTrue;

public class TestUtil {
   //todo : add loggind instead sout
    public static void compareBigDecimalRounded(BigDecimal actual, String expected) {
        int scale = expected.substring(expected.indexOf(".") + 1).length();
        actual = actual.setScale(scale, RoundingMode.HALF_UP);

        System.out.println("actial ="+ actual);
        System.out.println("expected ="+ expected);

        assertTrue(actual.compareTo(new BigDecimal(expected)) == 0);
    }
}
