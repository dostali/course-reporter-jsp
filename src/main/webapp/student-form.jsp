<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Reporter</title>
</head>
<body>
<div style="display: flex; justify-content: center; align-items: center; flex-direction: column">
    <h1>Books Management</h1>
    <h2>
        <a href="/students/list">List All Books</a>
    </h2>
</div>
<div style="align-items: center">

    <%-- we can use as many script tags as we want, the scope of variables is global
    and shared between the tags within the JSP file --%>
    <%
        String path = request.getServletPath();
        String action = request.getAttribute("action").toString();
    %>

    <form action="/students/<%=action%>" method="post">
        <table style="border: 1px; padding: 5px">
            <caption>
                <h2> <%= action.equals("update") ? "Edit Student" : "Add New Student" %> </h2>
            </caption>
            <input type="hidden" name="id" value=${requestScope.student.getId()}>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" size="45" value=${requestScope.student.getName()}>
                </td>
            </tr>
            <tr>
                <th>Surname:</th>
                <td>
                    <input type="text" name="surname" size="45" value=${requestScope.student.getSurname()}>
                </td>
            </tr>
            <tr>
                <th>Phone number:</th>
                <td>
                    <input type="text" name="phone_number" size="5" value=${requestScope.student.getPhoneNumber()}>
                </td>
            </tr>
            <tr>
                <th>Birth date:</th>
                <td>
                    <input type="date" name="birthdate" size="5" value=${requestScope.student.getBirthDate()}>
                </td>
            </tr>
            <tr>
                <th>Pin code:</th>
                <td>
                    <input type="text" name="pincode" size="5" value=${requestScope.student.getPinCode()}>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="align-items: center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>