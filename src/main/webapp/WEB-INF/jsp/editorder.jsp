<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Edit Order Information</h1>
</head>

<title>Editing Order Inforamtion</title>

<table class="main">
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Customer's Email</th>
        <th>Employee's Email</th>
    </tr>

    <tr>
        <form method="get" action="/editedorder">



            <!--Dropdown list for inventory items.-->
            <th>
                <select name="item">
                    <option value = ""></option>
                    <c:forEach var="inventoryitem" items="${inventoryList}">
                        <c:if test="${orderID.getItemID() == inventoryitem.getItemID()}">
                            <option value = "${inventoryitem.getItemID()}" selected>${inventoryitem.getItemName()}</option>
                        </c:if>
                        <c:if test="${orderID.getItemID() != inventoryitem.getItemID()}">
                            <option value = "${inventoryitem.getItemID()}">${inventoryitem.getItemName()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </th>

            <!--Quantity.-->
            <th>
                <input type="text" name="quantity" value = "${orderID.getQuantity()}"/>
            </th>

            <!--Dropdown list for customer emails.-->
            <th>
                <select name="customerid">
                    <option value = ""></option>
                    <c:forEach var="customeritem" items="${customerList}">
                        <c:if test="${orderID.getCustomerID() == customeritem.getCustomerID()}">
                            <option value = "${customeritem.getCustomerID()}" selected>${customeritem.getEmail()}</option>
                        </c:if>
                        <c:if test="${orderID.getCustomerID() != customeritem.getCustomerID()}">
                            <option value = "${customeritem.getCustomerID()}">${customeritem.getEmail()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </th>

            <!--Dropdown list for employee emails.-->
            <th>
                <select name="employeeid">
                    <option value = ""></option>
                    <c:forEach var="employeeitem" items="${employeeList}">
                        <c:if test="${orderID.getEmployeeID() == employeeitem.getEmployeeID()}">
                            <option value = "${employeeitem.getEmployeeID()}" selected>${employeeitem.getEmail()}</option>
                        </c:if>
                        <c:if test="${orderID.getEmployeeID() != employeeitem.getEmployeeID()}">
                            <option value = "${employeeitem.getEmployeeID()}">${employeeitem.getEmail()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </th>

            <!--Other.-->
            <th>
                <input type="hidden" name="other" value = "${orderID.getOrderID()}"/>
            </th>

            <th><input type = "submit" value ="Edit Order"></th>
        </form>
    </tr>
</table>


</html>