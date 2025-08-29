package com.example.objectOrientedProgramming;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class PartTimeEmployee extends Employee {
    private final int hoursWorked;
    private final double hourlyRate;

    public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void calculateSalary() {
        salary = hoursWorked * hourlyRate;
    }
}

