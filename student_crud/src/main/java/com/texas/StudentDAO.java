package com.texas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created On:8/29/25 3:23 PM
 * Author: Sumit Kumar Shrestha
 * Description:
 **/
public class StudentDAO {
    private Connection connection;

    public StudentDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Failed to establish a database connection. Check database server status, URL, username, and password.");
            e.printStackTrace();
            // Re-throw the exception to signal a critical failure
            throw new RuntimeException("Could not initialize StudentDAO due to database connection failure.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Insert a new student
    public void insertStudent(Student student) {
        String query = "INSERT INTO students (roll_no, name, email, program) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getRollNo());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getProgram());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a student
    public void updateStudent(Student student) {
        String query = "UPDATE students SET roll_no = ?, name = ?, email = ?, program = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getRollNo());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getProgram());
            stmt.setInt(5, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a student
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNo(rs.getString("roll_no"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setProgram(rs.getString("program"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Get student by id
    public Student getStudentById(int id) {
        Student student = null;
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setRollNo(rs.getString("roll_no"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setProgram(rs.getString("program"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}