package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal bda = new BigDecimal(a);
        BigDecimal bdb = new BigDecimal(b);

        return bda.divide(bdb, range, BigDecimal.ROUND_DOWN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int count = 0;
        int currentValue = 2;
        while (count != range) {
            boolean isSimpleNumber = true;
            currentValue++;
            for (int i = 2; i < currentValue; i++) {
                if (currentValue % i == 0) {
                    isSimpleNumber = false;
                    break;
                }
            }
            if (isSimpleNumber) {
                count++;
            }
        }
        return BigInteger.valueOf(currentValue);
    }
}
