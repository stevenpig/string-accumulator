package com.ubs.stringaccmulator;

import com.ubs.stringaccumulator.StringCalculator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.regex.Pattern;

/**
 * Created by Steven on 2/28/2018.
 */

@RunWith(JUnit4.class)
public class StringCalculatorTest {

    private StringCalculator sc = null;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        sc = new StringCalculator();
    }

    @Test
    public void testAdd_EmtpyString() {
        int res = sc.add("");
        assertEquals(0, res);
    }

    @Test
    public void testAdd_SingleNumber() {
        int res = sc.add("0");
        assertEquals(0, res);

        res = sc.add("1");
        assertEquals(1, res);

        res = sc.add("2");
        assertEquals(2, res);

        res = sc.add("123");
        assertEquals(123, res);
    }

    @Test
    public void testAdd_WithComma() {
        int res = sc.add("1,2");
        assertEquals(3, res);
    }

    @Test
    public void testAdd_NewLine() {
        int res = sc.add("1\n2");
        assertEquals(3, res);
    }

    @Test
    public void testAdd_CommaWithNewLine() {
        int res = sc.add("1\n2,3");
        assertEquals(6, res);
    }

    @Test
    public void testAdd_SingleDelimiter() {

        int res = sc.add("//;\n1");
        assertEquals(1, res);

        res = sc.add("//;\n1;2");
        assertEquals(3, res);
    }

    @Test
    public void testAdd_NegativeNumber() {

        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("negatives not allowed [-1]");
        sc.add("-1,2\n3");
    }

    @Test
    public void testAdd_NegativeNumberList() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("negatives not allowed [-1,-3]");
        sc.add("-1,2\n-3");
    }

    @Test
    public void testAdd_BiggerThan1000() {

        int res = sc.add("2,1001");
        assertEquals(2, res);

        res = sc.add("1001");
        assertEquals(0, res);
    }

    @Test
    public void testAdd_AnyLengthDelimiter() {
        int res = sc.add("//***\n1***2***3");
        assertEquals(6, res);
    }

    @Test
    public void testAdd_MultipleDelimiter() {
        int res = sc.add("//*|%\n1*2%3");
        assertEquals(6, res);
    }

    @Test
    public void testAdd_MulitpleDelimiterLengthMoreThanOne() {
        int res = sc.add("//aaa|bbb\n1aaa2bbb3");
        assertEquals(6, res);

        res = sc.add("//aaa|bb\n4aaa5bb6");
        assertEquals(15, res);
    }
}
