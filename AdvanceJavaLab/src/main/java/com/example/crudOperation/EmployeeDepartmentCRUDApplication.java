package com.example.crudOperation;

import java.sql.*;
import java.util.Scanner;

/**
 * @author diwash
 * @date 8/29/25
 * @description This file contains...
 */
public class EmployeeDepartmentCRUDApplication {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/labjava";
    private static final String USER = "postgres";
    private static final String PASS = "Geforce@2003";
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n==== MENU ====");
                System.out.println("1. Insert Department");
                System.out.println("2. Insert Employee");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. View All Employees");
                System.out.println("6. View Employees by Department");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        insertDepartment(scanner);
                        break;
                    case 2:
                        insertEmployee(scanner);
                        break;
                    case 3:
                        updateEmployee(scanner);
                        break;
                    case 4:
                        deleteEmployee(scanner);
                        break;
                    case 5:
                        viewAllEmployees();
                        break;
                    case 6:
                        viewEmployeesByDepartment(scanner);
                        break;
                    case 7:
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertDepartment(Scanner scanner) throws SQLException {
        System.out.println("Enter Department id: ");
        String deptId = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();

        String sql = "INSERT INTO Department (dept_id, dept_name) VALUES (?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, deptId);
            statement.setString(2, deptName);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Department Inserted Successfully!");
            } else {
                System.out.println("Department Insert Failed!");
            }
        }

    }

    private static void insertEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String empName = scanner.nextLine();
        System.out.print("Enter Department ID: ");
        String deptId = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO Employee (emp_id, emp_name, dept_id, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, empId);
            statement.setString(2, empName);
            statement.setString(3, deptId);
            statement.setDouble(4, salary);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Employee Inserted Successfully!");

            } else {
                System.out.println("Employee Insert Failed!");
            }
        }
    }

    private static void updateEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        String empId = scanner.nextLine();

        System.out.print("Enter New Name: ");
        String empName = scanner.nextLine();
        System.out.print("Enter New Department ID: ");
        String deptId = scanner.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE Employee SET emp_name = ?, dept_id = ?, salary = ? WHERE emp_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, empName);
            statement.setString(2, deptId);
            statement.setDouble(3, salary);
            statement.setString(4, empId);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Employee Updated Successfully!");
            } else {
                System.out.println("Employee Update Failed!");
            }
        }
    }

    private static void deleteEmployee(Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        String empId = scanner.nextLine();
        String sql = "DELETE FROM Employee WHERE emp_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, empId);
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Employee Deleted Successfully!");

            } else {
                System.out.println("Employee Delete Failed!");
            }
        }
    }

    private static void viewAllEmployees() throws SQLException {
        String sql = "SELECT e.emp_id, e.emp_name, d.dept_name, e.salary FROM Employee e " +
                "JOIN Department d ON e.dept_id = d.dept_id";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nEmployee Details:");
            while (rs.next()) {
                System.out.printf("ID: %s | Name: %s | Department: %s | Salary: %.2f\n",
                        rs.getString("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("dept_name"),
                        rs.getDouble("salary"));
            }
        }
    }


    private static void viewEmployeesByDepartment(Scanner scanner) throws SQLException {
        System.out.print("Enter Department ID to filter by: ");
        String deptId = scanner.nextLine();

        // Step 1: Check if department exists
        String checkDeptSql = "SELECT dept_name FROM Department WHERE dept_id = ?";
        try (PreparedStatement checkDeptStmt = conn.prepareStatement(checkDeptSql)) {
            checkDeptStmt.setString(1, deptId);
            try (ResultSet deptRs = checkDeptStmt.executeQuery()) {
                if (!deptRs.next()) {
                    System.out.println("Department with ID '" + deptId + "' does not exist.");
                    return;
                } else {
                    String deptName = deptRs.getString("dept_name");
                    System.out.println("\n✅ Department found: " + deptName);
                }
            }
        }

        // Step 2: Fetch employees in that department
        String sql = "SELECT emp_id, emp_name, salary FROM Employee WHERE dept_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, deptId);
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean hasEmployees = false;
                System.out.println("\nEmployees in Department " + deptId + ":");
                while (rs.next()) {
                    hasEmployees = true;
                    System.out.printf("ID: %s | Name: %s | Salary: %.2f\n",
                            rs.getString("emp_id"),
                            rs.getString("emp_name"),
                            rs.getDouble("salary"));
                }
                if (!hasEmployees) {
                    System.out.println("⚠️ No employees found in this department.");
                }
            }
        }
    }
}

