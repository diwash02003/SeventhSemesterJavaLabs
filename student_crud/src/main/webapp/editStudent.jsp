<%@ page import="com.texas.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>
<h2>Edit Student</h2>

<form action="studentServlet?action=update" method="post">
    <input type="hidden" name="id" value="<%= ((Student) request.getAttribute("student")).getId() %>"/>

    Roll No: <input type="text" name="roll_no" value="<%= ((Student) request.getAttribute("student")).getRollNo() %>" required/><br><br>
    Name: <input type="text" name="name" value="<%= ((Student) request.getAttribute("student")).getName() %>" required/><br><br>
    Email: <input type="email" name="email" value="<%= ((Student) request.getAttribute("student")).getEmail() %>" required/><br><br>
    Program: <input type="text" name="program" value="<%= ((Student) request.getAttribute("student")).getProgram() %>" required/><br><br>
    <input type="submit" value="Update Student"/>
</form>

<br>
<a href="studentServlet?action=view">Back to Student List</a>
</body>
</html>