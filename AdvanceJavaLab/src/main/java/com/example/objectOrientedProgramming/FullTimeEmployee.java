package com.example.objectOrientedProgramming;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class FullTimeEmployee extends Employee {
    private final double monthlySalary;

    public FullTimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public void calculateSalary() {
        salary = monthlySalary;
    }
}

