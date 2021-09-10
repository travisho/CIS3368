<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <link href="styles.css" rel="stylesheet">
    <h1>Edit Item Information</h1>
</head>

<title>Editing Item Information</title>

<table class="main">
    <tr>
        <th>Name</th>
        <th>Stock</th>
        <th>Price</th>
    </tr>

    <tr>
        <c:forEach var="item" items="${inventoryList}">
            <c:if test="${inventoryID == item.getItemID()}">
                <form method = "put" action="/editedinventory">
                    <th><input type="text" name="name" value= "${item.getItemName()}")/></th>
                    <th><input type="text" name="stock" value= "${item.getStock()}"/></th>
                    <th><input type="text" name="price" value= "${item.getPrice()}"/></th>
                    <th><input type="hidden" name="id" value="${item.getItemID()}"/></th>
                    <th><input type="submit" value="Edit"></th>
                </form>
            </c:if>
        </c:forEach>
    </tr>
</table>


</html>