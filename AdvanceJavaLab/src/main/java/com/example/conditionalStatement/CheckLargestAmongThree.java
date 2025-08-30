package com.example.conditionalStatement;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains, A Java program to find the largest among three numbers using if-else-if.
 */
public class CheckLargestAmongThree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number");
        int firstNumber = sc.nextInt();
        System.out.println("Enter second number");
        int secondNumber = sc.nextInt();
        System.out.println("Enter third number");
        int thirdNumber = sc.nextInt();

        if (firstNumber >= secondNumber && firstNumber >= thirdNumber) {
            System.out.println("The largest number is: " + firstNumber);
        } else if (secondNumber >= firstNumber && secondNumber >= thirdNumber) {
            System.out.println("The largest number is: " + secondNumber);
        } else {
            System.out.println("The largest number is: " + thirdNumber);
        }
        sc.close();
    }
}
