<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<center><html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Inventory</h1>
</head>

<title>Inventory Page</title>

<form action="/" class="home">
    <input type = "submit" value ="Home">
</form>

<h2>Filters</h2>
<table>
    <tr>
        <th>Item</th>
        <th>Stock</th>
        <th>Price</th>
    </tr>

    <tr>
        <form method="get" action="/filterinventory">
            <th><input type="text" name="filteritem"></th>
            <th><input type="text" name="filterstock"></th>
            <th><input type="text" name="filterprice"></th>
            <th><input type="submit" value="Filter"></th>
        </form>
    </tr>
</table>

<h2>Inventory</h2>
<table class="main">
    <tr>
        <th>Item Name</th>
        <th>Stock</th>
        <th>Price</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

    <c:forEach var="item" items="${inventoryList}">
        <tr>
            <td>${item.getItemName()}</td>
            <td>${item.getStock()}</td>
            <td>${item.getPrice()}</td>
            <td>
                <form action="/deleteinventory">
                    <input type="hidden" name = "id" value = "${item.getItemID()}">
                    <input type="submit" value="Delete">
                </form>
            </td>
            <td>
                <form action="/editinventory">
                    <input type="hidden" name = "id" value = "${item.getItemID()}">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add New Item</h2>
<table>
    <tr>
        <th>Name</th>
        <th>Stock</th>
        <th>Price</th>
    </tr>

    <tr>
        <form method="post" action="/newinventory">
            <th><input type="text" name="name"/></th>
            <th><input type="text" name="stock"/></th>
            <th><input type="text" name="price"/></th>
            <th><input type = "submit" value ="Add Item"></th>
        </form>
    </tr>
</table>

${errormessage}

</html></center>