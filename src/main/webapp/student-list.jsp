<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="az.bdc.coursereporterjsp.domain.Student" %>
<%--<%@ page import="static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: tural
  Date: 10/25/2023
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Reporter</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        td {
            padding: 8px;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1>Student Management</h1>
    <h2>
        <a href="/new">Add New Student</a>
        <a href="/list">List All Students</a>
    </h2>
</div>
<div style="display:flex; justify-content:center; align-items: center">
    <table style="padding: 1px; border: 1px black">
        <caption><h2>List of Students</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone number</th>
            <th>Update date</th>
        </tr>
        <% List studentList = (ArrayList) request.getAttribute("studentList");
            for (int i = 0; i < studentList.size(); i++){%>

        <tr>
            <td><%= ((Student)studentList.get(i)).getId() %></td>
            <td><%= ((Student)studentList.get(i)).getFullName() %></td>
            <td><%= ((Student)studentList.get(i)).getPhoneNumber() %></td>
            <td><%= ((Student)studentList.get(i)).getUpdateDate() %></td>
            <td>
                <a href="/edit?id=<%= ((Student)studentList.get(i)).getId() %>">Edit</a>
                <a href="/delete?id=<%= ((Student)studentList.get(i)).getId() %>">Delete</a>
            </td>
        </tr>

        <%}%>
    </table>
</div>
</body>
</html>