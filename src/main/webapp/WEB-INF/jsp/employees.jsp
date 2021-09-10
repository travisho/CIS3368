<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<center><html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Employees</h1>
</head>

<title>Employees Page</title>

<form action="/" class="home">
    <input type = "submit" value ="Home">
</form>

<h2>Filters</h2>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>

    <tr>
        <form method="get" action="/filteremployee">
            <th><input type="text" name="filterfirst"></th>
            <th><input type="text" name="filterlast"></th>
            <th><input type="text" name="filterphone"></th>
            <th><input type="text" name="filteremail"></th>
            <th><input type="submit" value="Filter"></th>
        </form>
    </tr>
</table>

<h2>Employees List</h2>
<table class="main">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

    <c:forEach var="item" items="${employeeList}">
        <tr>
            <td>${item.getFirstName()}</td>
            <td>${item.getLastName()}</td>
            <td>${item.getPhone()}</td>
            <td>${item.getEmail()}</td>
            <td>
                <form action="/deleteemployee">
                    <input type="hidden" name = "id" value = "${item.getEmployeeID()}">
                    <input type="submit" value="Delete">
                </form>
            </td>
            <td>
                <form action="/editemployee">
                    <input type="hidden" name = "id" value = "${item.getEmployeeID()}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add New Employee</h2>
<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>

    <tr>
        <form method="post" action="/newemployee">
            <th><input type="text" name="first"/></th>
            <th><input type="text" name="last"/></th>
            <th><input type="text" name="phone"/></th>
            <th><input type="text" name="email"/></th>
            <th><input type = "submit" value ="Create Employee"></th>
        </form>
    </tr>
</table>

${errormessage}

</html></center>