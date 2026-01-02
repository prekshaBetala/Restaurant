<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.tap.dto.Menu" %>

<!DOCTYPE html>
<html>
<head>
<title>Menu</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body{
    font-family:Segoe UI, sans-serif;
    background:#f5f6f7;
    margin:0;
    padding:20px;
}
h2{text-align:center;margin-bottom:20px}

.add-menu{
    text-align:right;
    margin-bottom:15px;
}
.add-menu a{
    background:#ff6a00;
    color:#fff;
    padding:10px 15px;
    border-radius:8px;
    text-decoration:none;
    font-weight:600;
}

.menu-grid{
    display:grid;
    grid-template-columns:repeat(auto-fit,minmax(250px,1fr));
    gap:20px;
}
.menu-card{
    background:#fff;
    padding:15px;
    border-radius:15px;
    box-shadow:0 10px 25px rgba(0,0,0,.1);
}
.menu-card img{
    width:100%;
    height:160px;
    object-fit:cover;
    border-radius:12px;
}
.menu-name{font-weight:700;margin-top:10px}
.menu-desc{color:#555;font-size:14px;margin:5px 0}
.menu-rating{color:#ff9800}
.menu-price{font-weight:600;margin:5px 0}

.cart-form{
    display:flex;
    gap:8px;
    margin-top:10px;
}
.cart-form input{
    width:60px;
    padding:5px;
}
.cart-form button{
    flex:1;
    background:#111;
    color:#fff;
    border:none;
    border-radius:6px;
    cursor:pointer;
}

.empty{
    text-align:center;
    color:#888;
    margin-top:50px;
}
</style>
</head>

<body>

<h2>🍽 Menu Items</h2>

<%
String role = (session != null) ? (String) session.getAttribute("role") : null;
String restaurantId = request.getParameter("restaurantId");

if (role != null && role.toUpperCase().contains("ADMIN") && restaurantId != null) {
%>
    <div class="add-menu">
        <a href="addMenu.jsp?restaurantId=<%= restaurantId %>">
            ➕ Add Menu
        </a>
    </div>
<%
}
%>

<%
@SuppressWarnings("unchecked")
List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");

if (menuList == null || menuList.isEmpty()) {
%>
    <p class="empty">No menu items available.</p>
<%
} else {
%>

<div class="menu-grid">

<%
for (Menu m : menuList) {
%>

<div class="menu-card">

    <img src="<%= (m.getImageurl() == null || m.getImageurl().isEmpty())
            ? "images/default-food.jpg"
            : m.getImageurl() %>"
         alt="<%= m.getName() %>">

    <div class="menu-name"><%= m.getName() %></div>
    <div class="menu-desc"><%= m.getDescription() %></div>
    <div class="menu-rating">⭐ <%= m.getRating() %></div>
    <div class="menu-price">₹ <%= m.getPrice() %></div>

    <!-- ✅ FIXED ADD TO CART FORM -->
    <form class="cart-form" action="addToCart" method="post">
        <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
        <input type="hidden" name="name" value="<%= m.getName() %>">
        <input type="hidden" name="price" value="<%= m.getPrice() %>">

        <input type="number" name="quantity" value="1" min="1">
        <button type="submit">Add</button>
    </form>

</div>

<%
}
%>

</div>

<%
}
%>

</body>
</html>
