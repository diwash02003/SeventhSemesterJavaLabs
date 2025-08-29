package com.example.functions;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class PalindromeCheck {

    public static boolean isPalindrome(String str) {
//        using string builder
//        String reversed = new StringBuilder(str).reverse().toString();
//        //Check if original and reversed are equal
//        return str.equals(reversed);

        str = str.replaceAll("\\s+", "").toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String str = sc.nextLine();

        if (isPalindrome(str)) {
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }
    }
}
