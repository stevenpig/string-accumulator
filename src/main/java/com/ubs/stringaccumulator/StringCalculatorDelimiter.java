package com.ubs.stringaccumulator;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Steven on 2/28/2018.
 */
public class StringCalculatorDelimiter {

    private static final String DEFAULT_DELIMITER = ",|\n";

    private static final String SPECIFIC_DELIMITER = "//(\\D+)\n.*$";

    private static final Pattern specificDelimterPattern = Pattern.compile(SPECIFIC_DELIMITER);

    public List<String> getNumberList(String input) {

        if (isSpecificDelimiter(input)) {
            Matcher matcher = specificDelimterPattern.matcher(input);
            matcher.find();
            String delimiter = matcher.group(1);
            return Arrays.stream(StringUtils.split(specificPatternString(input), delimiter)).collect(Collectors.toList());
        } else {
            return Arrays.stream(StringUtils.split(input, DEFAULT_DELIMITER)).collect(Collectors.toList());
        }
    }

    public String specificPatternString(String input) {
        return input.substring(input.lastIndexOf("\n") + 1);
    }

    private boolean isSpecificDelimiter(String input) {
        return specificDelimterPattern.matcher(input).matches();
    }

}
