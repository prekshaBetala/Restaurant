<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
    String role = (String) session.getAttribute("role");

    // 🔒 Block non-admin users
    if (role == null || !role.equals("ADMIN")) {
        response.sendRedirect("../login.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

<style>
    body{
        font-family: Arial, Helvetica, sans-serif;
        background:#f4f6f8;
        margin:0;
        padding:0;
    }
    .container{
        max-width:900px;
        margin:40px auto;
        background:#fff;
        padding:30px;
        border-radius:12px;
        box-shadow:0 10px 30px rgba(0,0,0,0.08);
    }
    h2{
        margin-bottom:20px;
    }
    .card{
        display:flex;
        gap:20px;
        margin-top:20px;
    }
    a{
        text-decoration:none;
        padding:18px 22px;
        background:#ff6a00;
        color:#fff;
        border-radius:10px;
        font-weight:600;
        transition:0.3s;
    }
    a:hover{
        background:#e65c00;
    }
    .logout{
        background:#444;
    }
</style>

</head>
<body>

<div class="container">
    <h2>👑 AdminDashboard</h2>
    <p>Welcome, <b><%= session.getAttribute("userEmail") %></b></p>

    <div class="card">
        <a href="addRestaurant.jsp">➕ Add Restaurant</a>
    
        
        <a href="addMenu.jsp">➕ Add Menu</a>
        <a href="../logout" class="logout">🚪 Logout</a>
    </div>
</div>

</body>
</html>
