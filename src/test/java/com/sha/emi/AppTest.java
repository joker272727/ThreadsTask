package com.sha.emi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    public void testNumberToString() {
        String output = "three thousand seven hundred eighty five";
        int input = 3785;


        assertEquals(output, App.numberToString(input));

}

public void testStringToNumber() {
        int output = 4633;
        String input = "four thousand six hundred thirty three";

        assertEquals(output, App.stringToNumber(input));
}
}
