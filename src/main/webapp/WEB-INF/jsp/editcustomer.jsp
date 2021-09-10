<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Edit Customer Information</h1>
</head>

<title>Editing Customer Information</title>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>

    <tr>
        <c:forEach var="item" items="${customerList}">
            <c:if test="${customerID == item.getCustomerID()}">
                <form method = "put" action="/editedcustomer">

                    <th><input type="text" name="first" value= "${item.getCustomerfirst()}")/></th>
                    <th><input type="text" name="last" value= "${item.getCustomerlast()}"/></th>
                    <th><input type="text" name="phone" value= "${item.getPhone()}"/></th>
                    <th><input type="text" name="email" value= "${item.getEmail()}"/></th>
                    <th><input type="hidden" name="id" value="${item.getCustomerID()}"/></th>
                    <th><input type="submit" value="Edit"></th>
                </form>
            </c:if>
        </c:forEach>
    </tr>
</table>


</html>