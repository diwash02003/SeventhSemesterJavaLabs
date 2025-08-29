package com.example.objectOrientedProgramming;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class Employee {
    private final int id;
    private final String name;
    protected double salary;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void calculateSalary() {
        salary = 0.0;
    }

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: Rs" + salary);
    }
}
