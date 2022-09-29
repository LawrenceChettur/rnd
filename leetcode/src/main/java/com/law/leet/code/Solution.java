package com.law.leet.code;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private Map<String, Integer> romanNumberToIntegerMap;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.convertRomanNumeralToAnInteger("MCMXCIV");
    }

    public int convertRomanNumeralToAnInteger(String romanNumber) {
        System.out.println(romanNumber);
        if (isRomanNumeralValid(romanNumber)) {
            System.out.println("done!");
        }
        return 0;
    }

    private boolean isRomanNumeralValid(String romanNumber) {
        List<String> wholeRomanNumbers = new ArrayList<>();
        int currentIndex = 0;
        boolean indexCaptured = false;
        if (isRomanNumberLengthValid(romanNumber)) {
            char[] romanNumbers = romanNumber.toCharArray();
            for (int i = 0; i < romanNumbers.length; i++) {
                String currentNumber = String.valueOf(romanNumbers[i]);
                String nextNumber = String.valueOf(romanNumbers[i + 1]);
                if (isRomanNumeralSyntaxValid(currentNumber)) {
                    if (moveToNextNumber(nextNumber, currentNumber)) {
                        wholeRomanNumbers.add(i, currentNumber);
                        indexCaptured = false;
                        continue;
                    } else {
                        if (!indexCaptured)
                            currentIndex = i;
                        indexCaptured = true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isRomanNumeralSyntaxValid(String romanNumber) {
        Map<String, Integer> romanNumberToIntegerMap = getRomanNumberToIntegerMap();
        return romanNumberToIntegerMap.containsKey(String.valueOf(romanNumber));
    }

    private boolean moveToNextNumber(String nextNumber, String currentNumber) {
        Map<String, Integer> romanNumberToIntegerMap = getRomanNumberToIntegerMap();
        if (Integer.valueOf(romanNumberToIntegerMap.get(currentNumber)) > Integer.valueOf(romanNumberToIntegerMap.get(nextNumber))) {
            return true;
        }
        return false;
    }

    private Map<String, Integer> getRomanNumberToIntegerMap() {
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
        return romanNumberToIntegerMap;
    }

    private boolean isRomanNumberLengthValid(String romanNumber) {
        if (StringUtils.isNotBlank(romanNumber) && romanNumber.length() > 1 && romanNumber.length() <= 15) {
            return true;
        }
        return false;
    }
}
