<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // 🔒 ADMIN SECURITY CHECK
    String role = (String) session.getAttribute("role");
    if (role == null || !"ADMIN".equals(role)) {
        response.sendRedirect("../login.html");
        return;
    }

    // ✅ restaurantId from URL
    String rid = request.getParameter("restaurantId");
    if (rid == null || rid.isEmpty()) {
        rid = "1";   // default restaurant id (temporary)
    }
%>

<!DOCTYPE html>
<html>
<head>
<title>Add Menu</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body{
    font-family:Segoe UI,sans-serif;
    background:#f5f6f7;
    display:flex;
    justify-content:center;
    align-items:center;
    min-height:100vh;
}
.form-box{
    background:#fff;
    padding:30px;
    width:400px;
    border-radius:15px;
    box-shadow:0 15px 35px rgba(0,0,0,.15);
}
h2{text-align:center;margin-bottom:20px}
input,textarea,button{
    width:100%;
    padding:12px;
    margin-bottom:12px;
    border-radius:8px;
    border:1px solid #ccc;
}
button{
    background:#ff6a00;
    color:#fff;
    border:none;
    font-size:16px;
    font-weight:600;
    cursor:pointer;
}
</style>
</head>

<body>

<div class="form-box">
<h2>Add Menu Item</h2>

<!-- ✅ ADMIN SERVLET PATH -->
<form action="addMenu" method="post">

    <input type="hidden" name="restaurantId" value="<%= rid %>">

    <input type="text" name="name" placeholder="Dish Name" required>

    <textarea name="description" placeholder="Description"></textarea>

    <input type="number" step="0.01"
           name="price" placeholder="Price" required>

    <input type="number" step="0.1"
           name="rating" placeholder="Rating">

    <input type="text"
           name="imageurl" placeholder="Image URL">

    <button type="submit">Add Menu</button>

</form>
</div>

</body>
</html>
