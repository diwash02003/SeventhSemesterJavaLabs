package com.example.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class FrequencyInArray {
    public static void main(String[] args) {
//        using leda function
//        int[] arr = {1, 2, 2, 3, 1, 4, 2, 3, 5, 1};
//        List<Integer> list = Arrays.stream(arr).boxed().toList();
//        Map<Integer, Long> frequencyMap = list.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        frequencyMap.forEach((key, value) ->
//                System.out.println("Element: " + key + ", Frequency: " + value)
//        );

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int[] freq = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            freq[i] = -1;
        }

        // Count frequencies
        for (int i = 0; i < n; i++) {
            int count = 1;
            if (freq[i] == 0) continue;

            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    freq[j] = 0;
                }
            }
            freq[i] = count;
        }
        System.out.println("Frequency of each element:");
        System.out.println("Element : Frequency");
        for (int i = 0; i < n; i++) {
            if (freq[i] != 0) {
                System.out.println("{ " + arr[i] + " : " + freq[i] + " }");
            }
        }
        sc.close();
    }
}
