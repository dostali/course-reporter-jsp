<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="az.bdc.coursereporterjsp.domain.Teacher" %>
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
    <h1>Teacher Management</h1>
    <h2>
        <a href="/teachers/new">Add New Teacher</a>
    </h2>
</div>
<div style="display:flex; justify-content:center; align-items: center">
    <table style="padding: 1px; border: 1px black">
        <caption><h2>List of Teachers</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone number</th>
            <th>Update date</th>
            <th>Actions</th>
        </tr>
        <% List teacherList = (ArrayList) request.getAttribute("teachers");
            for (int i = 0; i < teacherList.size(); i++){%>

        <tr>
            <td><%= ((Teacher)teacherList.get(i)).getId() %></td>
            <td><%= ((Teacher)teacherList.get(i)).getFullName() %></td>
            <td><%= ((Teacher)teacherList.get(i)).getPhoneNumber() %></td>
            <td><%= ((Teacher)teacherList.get(i)).getUpdateDate() %></td>
            <td>
                <a href="/teachers/edit?id=<%= ((Teacher)teacherList.get(i)).getId() %>">Edit</a>
                <a href="/teachers/delete?id=<%= ((Teacher)teacherList.get(i)).getId() %>">Delete</a>
            </td>
        </tr>

        <%}%>
    </table>
</div>
</body>
</html>