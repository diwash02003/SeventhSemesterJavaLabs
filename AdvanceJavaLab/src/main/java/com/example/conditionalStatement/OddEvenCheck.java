package com.example.conditionalStatement;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class OddEvenCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to check odd or even: ");
        int num = sc.nextByte();

        if (num % 2 == 0) {
            System.out.println("The Number is Even");
        } else {
            System.out.println("The Number is Odd");
        }
        sc.close();
    }
}
