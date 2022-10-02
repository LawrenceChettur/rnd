package com.law.leet.code.romantointeger;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RomanNumber {

    private String romanNumber;
    private Map<String, Integer> romanNumberToIntegerMap;

    public RomanNumber() {
        if (romanNumberToIntegerMap == null) {
            romanNumberToIntegerMap = new HashMap<>();
            romanNumberToIntegerMap.put("I", 1);
            romanNumberToIntegerMap.put("V", 5);
            romanNumberToIntegerMap.put("X", 10);
            romanNumberToIntegerMap.put("L", 50);
            romanNumberToIntegerMap.put("C", 100);
            romanNumberToIntegerMap.put("D", 500);
            romanNumberToIntegerMap.put("M", 1000);
        }
    }

    public RomanNumber(String romanNumber) {
        this();
        this.romanNumber = romanNumber;
    }

    public Integer toInteger() {
//        System.out.println(romanNumber);
        Integer romanNumeralAsInteger = null;
        if (isRomanNumeralValid(romanNumber)) {
//            System.out.println("done!");
            romanNumeralAsInteger = identifyCompleteNumber(romanNumber);
            System.out.println("Given Roman Numeral is: " + romanNumber + " - coverted to integer is :" +
                    romanNumeralAsInteger);
        }
        return romanNumeralAsInteger;
    }

    private boolean isRomanNumeralValid(String romanNumeral) {
        if (isRomanNumeralLengthValid(romanNumeral)) {
            for (int i = 0; i < romanNumeral.length(); i++) {
                String numeral = romanNumeral.substring(i, i + 1);
//                System.out.print(numeral);
                if (isNumeralValidRomanSyntax(numeral)) {
//                    System.out.println(" - valid");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isRomanNumeralLengthValid(String romanNumber) {
        if (StringUtils.isNotBlank(romanNumber) && romanNumber.length() > 1 && romanNumber.length() <= 15) {
            return true;
        }
        return false;
    }

    private boolean isNumeralValidRomanSyntax(String romanNumber) {
        return romanNumberToIntegerMap.containsKey(String.valueOf(romanNumber));
    }

    private Integer identifyCompleteNumber(String numeral) {
        Integer result = null;
        Integer previousNumeral = null;
        for (int i = numeral.length(); i >= 0; i--) {
            if ((i - 1) >= 0) {
                String romanLiteral = numeral.substring(i - 1, i);
                Integer currentNumeral = romanNumberToIntegerMap.get(romanLiteral);
//                System.out.println(previousNumeral + "::" + currentNumeral);
                if (previousNumeral == null) {
                    previousNumeral = Integer.valueOf(currentNumeral);
                    if (result == null) {
                        result = 0;
                    }
                    result += currentNumeral;
                } else {
                    if (currentNumeral < previousNumeral) {
                        result -= currentNumeral;
                    } else {
                        result += currentNumeral;
                    }
                    previousNumeral = Integer.valueOf(currentNumeral);
                }
//                System.out.println("result is: " + result + " for romanLiteral " + romanLiteral + " with Index: " + (i - 1) + "::" + i);
            }
        }
        return result;
    }
}
