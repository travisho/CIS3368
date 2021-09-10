<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<center><html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Customer Orders</h1>
</head>

<title>Customer Order Page</title>

<form action="/" class="home">
    <input type = "submit" value ="Home">
</form>

<h2>Filters</h2>
<table>
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Customer's Email</th>
        <th>Employee's Email</th>
    </tr>

    <tr>
        <form method="get" action="/filterorder">

            <!--Dropdown list for inventory items.-->
            <th>
                <select name="item">
                    <option value = ""></option>
                    <c:forEach var="inventoryitem" items="${inventoryList}">
                        <option value = "${inventoryitem.getItemID()}">${inventoryitem.getItemName()}</option>
                    </c:forEach>
                </select>
            </th>

            <!--Quantity.-->
            <th>
                <input type="text" name="quantity"/>
            </th>

            <!--Dropdown list for customer emails.-->
            <th>
                <select name="customerid">
                    <option value = ""></option>
                    <c:forEach var="customeritem" items="${customerList}">
                        <option value = "${customeritem.getCustomerID()}">${customeritem.getEmail()}</option>
                    </c:forEach>
                </select>
            </th>

            <!--Dropdown list for employee emails.-->
            <th>
                <select name="employeeid">
                    <option value = ""></option>
                    <c:forEach var="employeeitem" items="${employeeList}">
                        <option value = "${employeeitem.getEmployeeID()}">${employeeitem.getEmail()}</option>
                    </c:forEach>
                </select>
            </th>

            <th><input type = "submit" value ="Filter"></th>
        </form>
    </tr>
</table>

<h2>Orders List</h2>
<table class="main">
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Customer's Email</th>
        <th>Employee's Email</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

    <c:forEach var="item" items="${orderList}">
        <tr>

            <!--This is getting the item's name.-->
            <c:forEach var="inventoryitem" items="${inventoryList}">
                <c:if test="${item.getItemID() == inventoryitem.getItemID()}">
                    <td>${inventoryitem.getItemName()}</td>
                </c:if>
            </c:forEach>

            <td>${item.getQuantity()}</td>

            <!--This is getting the customer's email.-->
            <c:forEach var="customeritem" items="${customerList}">
                <c:if test="${item.getCustomerID() == customeritem.getCustomerID()}">
                    <td>${customeritem.getEmail()}</td>
                </c:if>
            </c:forEach>

            <!--This is getting the employee's email.-->
            <c:forEach var="employeeitem" items="${employeeList}">
                <c:if test="${item.getEmployeeID() == employeeitem.getEmployeeID()}">
                    <td>${employeeitem.getEmail()}</td>
                </c:if>
            </c:forEach>

            <td>
                <form action="/deleteorder">
                    <input type="hidden" name ="id" value = "${item.getOrderID()}">
                    <input type="submit" value="Delete">
                </form>
            </td>

            <td>
                <form action="/editorder">
                    <input type="hidden" name = "id" value = "${item.getOrderID()}">
                    <input type="submit" value="Edit">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>

<h2>Add New Order</h2>
<table>
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Customer's Email</th>
        <th>Employee's Email</th>
    </tr>

    <tr>
        <form method="post" action="/neworder">

            <!--Dropdown list for inventory items.-->
            <th>
                <select name="item">
                    <option value = ""></option>
                    <c:forEach var="inventoryitem" items="${inventoryList}">
                        <option value = "${inventoryitem.getItemID()}">${inventoryitem.getItemName()}</option>
                    </c:forEach>
                </select>
            </th>

            <!--Quantity.-->
            <th>
                <input type="text" name="quantity"/>
            </th>

            <!--Dropdown list for customer emails.-->
            <th>
                <select name="customerid">
                    <option value = ""></option>
                    <c:forEach var="customeritem" items="${customerList}">
                        <option value = "${customeritem.getCustomerID()}">${customeritem.getEmail()}</option>
                    </c:forEach>
                </select>
            </th>

            <!--Dropdown list for employee emails.-->
            <th>
                <select name="employeeid">
                    <option value = ""></option>
                    <c:forEach var="employeeitem" items="${employeeList}">
                        <option value = "${employeeitem.getEmployeeID()}">${employeeitem.getEmail()}</option>
                    </c:forEach>
                </select>
            </th>


            <th><input type = "submit" value ="Create Order"></th>
        </form>
    </tr>
</table>

${errormessage}

</html></center>