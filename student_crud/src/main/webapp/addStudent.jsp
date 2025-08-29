<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<h2>Add New Student</h2>

<form action="studentServlet?action=insert" method="post">
    Roll No: <input type="text" name="roll_no" required/><br><br>
    Name: <input type="text" name="name" required/><br><br>
    Email: <input type="email" name="email" required/><br><br>
    Program: <input type="text" name="program" required/><br><br>
    <input type="submit" value="Add Student"/>
</form>

<br>
<a href="studentServlet?action=view">Back to Student List</a>
</body>
</html>