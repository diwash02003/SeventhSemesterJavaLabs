<%@ page import="java.util.List" %>
<%@ page import="com.texas.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<h2>Student List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Roll No</th>
        <th>Name</th>
        <th>Email</th>
        <th>Program</th>
        <th>Actions</th>
    </tr>

    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null) {
            for (Student student : students) {
    %>
    <tr>
        <td><%= student.getId() %></td>
        <td><%= student.getRollNo() %></td>
        <td><%= student.getName() %></td>
        <td><%= student.getEmail() %></td>
        <td><%= student.getProgram() %></td>
        <td>
            <a href="studentServlet?action=edit&id=<%= student.getId() %>">Edit</a> |
            <a href="studentServlet?action=delete&id=<%= student.getId() %>">Delete</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<br>
<a href="addStudent.jsp">Add New Student</a>
</body>
</html>