package com.texas;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created On:8/29/25 3:28 PM
 * Author: Sumit Kumar Shrestha
 * Description:
 **/
@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        // The container calls this method once, when the servlet is first loaded.
        // It's the best place to initialize the DAO.
        try {
            studentDAO = new StudentDAO();
        } catch (RuntimeException e) {
            // A critical error occurred during initialization (e.g., DB connection failed).
            // Re-throw as ServletException to signal the container that the servlet cannot be initialized.
            throw new ServletException("Initialization failed: Could not connect to the database.", e);
        }
    }

    // Handle all CRUD operations based on the HTTP request method
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("insert".equals(action)) {
            insertStudent(request, response);
        } else if ("update".equals(action)) {
            updateStudent(request, response);
        }

        // After a POST operation, redirect to the view action to display the updated list.
        response.sendRedirect(request.getContextPath() + "/studentServlet?action=view");
    }

    // Handle viewing and deleting of students (GET requests)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || "view".equals(action)) {
            viewStudents(request, response);
        } else if ("edit".equals(action)) {
            editStudent(request, response);
        } else if ("delete".equals(action)) {
            deleteStudent(request, response);
        } else {
            // Default to view if action is not recognized
            viewStudents(request, response);
        }
    }

    // Insert Student
    private void insertStudent(HttpServletRequest request, HttpServletResponse response) {
        String rollNo = request.getParameter("roll_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String program = request.getParameter("program");

        Student student = new Student();
        student.setRollNo(rollNo);
        student.setName(name);
        student.setEmail(email);
        student.setProgram(program);

        studentDAO.insertStudent(student);
    }

    // Update Student
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String rollNo = request.getParameter("roll_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String program = request.getParameter("program");

        Student student = new Student();
        student.setId(id);
        student.setRollNo(rollNo);
        student.setName(name);
        student.setEmail(email);
        student.setProgram(program);

        studentDAO.updateStudent(student);
    }

    // View all students
    private void viewStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    // Edit a student
    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editStudent.jsp");
        dispatcher.forward(request, response);
    }

    // Delete student
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect(request.getContextPath() + "/studentServlet?action=view");
    }
}