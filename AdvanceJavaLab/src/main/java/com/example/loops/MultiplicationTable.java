package com.example.loops;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter any number for multiplication table:");
        int n = sc.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " * " + i + " = " + (n * i));
        }
        sc.close();
    }
}
