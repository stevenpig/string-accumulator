package com.ubs.stringaccumulator;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Steven on 2/28/2018.
 */
public class StringCalculator {


    public int add(String input) {

        if (StringUtils.isEmpty(input)) {
            return 0;
        }

        if (input.length() == 1)
            return Integer.valueOf(input);

        StringCalculatorDelimiter d = new StringCalculatorDelimiter();
        List<String> nums = d.getNumberList(input);

        hasNegativeNumbers(nums);

        return sumUp(nums);
    }

    private int sumUp(List<String> nums) {
        return nums.stream().filter(num -> Integer.parseInt(num) <= 1000).mapToInt(Integer::valueOf).sum();
    }

    private void  hasNegativeNumbers(List<String> nums) throws IllegalArgumentException{

        String negNumlist = nums.stream().filter(n -> n.contains("-")).collect(Collectors.joining(","));

        if (StringUtils.isNotEmpty(negNumlist))
            throw new IllegalArgumentException(String.format("negatives not allowed [%s]", negNumlist));
    }

}
