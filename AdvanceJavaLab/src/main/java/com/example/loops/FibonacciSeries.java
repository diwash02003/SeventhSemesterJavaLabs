package com.example.loops;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of fibonacci series: ");
        int n = sc.nextInt();

        int first = 0, second = 1;

        for (int i = 1; i <= n; i++) {
            System.out.print(first + " ");
            //calculate next number
            int next = first + second;
            first = second;
            second = next;
        }
        sc.close();
    }
}
