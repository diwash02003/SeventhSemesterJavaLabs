package com.example.arrays;

import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains, A Java program to find the smallest and largest elements in an array entered by the user.
 */
public class FindLargestAndSmallestElement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int smallest = arr[0];
        int largest = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        // Output results
        System.out.println("Smallest element: " + smallest);
        System.out.println("Largest element: " + largest);

    }
}
