package com.example.objectOrientedProgramming;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class PayrollSystem {
    public static void main(String[] args) {
        FullTimeEmployee employee1 = new FullTimeEmployee(101, "John Smith", 20000);
        employee1.calculateSalary();

        PartTimeEmployee employee2 = new PartTimeEmployee(102, "Bob", 80, 20.0);
        employee2.calculateSalary();

        Employee[] employees = {employee1, employee2};

        for (Employee emp : employees) {
            System.out.println("\n--- Employee Details ---");
            emp.displayDetails();
        }
    }
}
