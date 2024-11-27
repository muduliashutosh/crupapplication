<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="../update/${employee.id}" method="post">
        <label>Name:</label>
        <input type="text" name="name" value="${employee.name}" required><br>
        <label>Email:</label>
        <input type="email" name="email" value="${employee.email}" required><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
