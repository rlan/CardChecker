package com.rlan.cardchecker;

/**
 * Algorithm description
 * https://en.wikipedia.org/wiki/Luhn_algorithm
 *
 * Implementation code stolen from
 * http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers#Java
 * Available under GNU Free Documentation License 1.2 unless otherwise noted.
 * License: http://www.gnu.org/licenses/fdl-1.2.html
 */

public class LuhnAlgorithm {
    public static void main(String[] args) {
        System.out.println(luhnTest("49927398716"));
        System.out.println(luhnTest("49927398717"));
        System.out.println(luhnTest("1234567812345678"));
        System.out.println(luhnTest("1234567812345670"));
    }

    public static boolean luhnTest(String number){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
}