<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.math.BigDecimal,com.tap.dto.OrderItem" %>

<%
@SuppressWarnings("unchecked")
Map<Integer, OrderItem> cart =
    (Map<Integer, OrderItem>) session.getAttribute("cart");

if (cart == null || cart.isEmpty()) {
    response.sendRedirect("cart.jsp");
    return;
}

BigDecimal itemTotal = BigDecimal.ZERO;
for (OrderItem item : cart.values()) {
    itemTotal = itemTotal.add(item.getTotalPrice());
}

BigDecimal discount = new BigDecimal("20");
BigDecimal grandTotal = itemTotal.subtract(discount);
if (grandTotal.compareTo(BigDecimal.ZERO) < 0) {
    grandTotal = BigDecimal.ZERO;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout | 404HUNGRY</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body{margin:0;font-family:Segoe UI;background:#f4f5f7}
.card{
 background:#fff;padding:16px;
 margin:14px;border-radius:14px;
 box-shadow:0 4px 10px rgba(0,0,0,.08)
}
.title{font-weight:800;margin-bottom:10px}
.row{display:flex;justify-content:space-between;margin:8px 0}
input,textarea{
 width:100%;padding:10px;
 border-radius:10px;border:1px solid #ccc;
 margin-top:6px;font-size:14px
}
textarea{resize:none}
.footer{
 position:fixed;bottom:0;left:0;right:0;
 background:#fff;border-top:1px solid #ddd;
 padding:14px 18px;display:flex;
 justify-content:space-between;align-items:center
}
.btn{
 background:#0a7d45;color:#fff;
 padding:14px 24px;border:none;
 border-radius:14px;font-size:16px;
 font-weight:800;cursor:pointer
}
.small{color:#777;font-size:13px}
.total{font-size:20px;font-weight:800}
</style>
</head>

<body>

<form action="placeOrder" method="post">

<!-- DELIVERY FLEET -->
<div class="card">
  <div class="title">Delivery by Standard Fleet</div>
  <p>Our standard food delivery experience</p>
</div>

<!-- ADDRESS INPUT -->
<div class="card">
  <div class="title">Delivery Address</div>
  <textarea name="address" rows="3" required
    placeholder="House no, Street, Area, City"></textarea>
</div>

<!-- PHONE INPUT -->
<div class="card">
  <div class="title">Contact Number</div>
  <input type="tel" name="phone" required
         placeholder="Enter phone number">
</div>

<!-- ORDER SUMMARY -->
<div class="card">
  <div class="title">Order Summary</div>
  <%
  for (OrderItem item : cart.values()) {
  %>
    <div class="row">
      <span><%= item.getItemName() %> × <%= item.getQuantity() %></span>
      <span>₹<%= item.getTotalPrice() %></span>
    </div>
  <%
  }
  %>
</div>

<!-- BILL DETAILS -->
<div class="card">
  <div class="row">
    <span>Item Total</span>
    <span>₹<%= itemTotal %></span>
  </div>
  <div class="row">
    <span>Delivery Fee</span>
    <span>FREE</span>
  </div>
  <div class="row">
    <span>Discount</span>
    <span>- ₹20</span>
  </div>
  <div class="row" style="color:#0a7d45;font-weight:700">
    You saved ₹20 on this order
  </div>
  <hr>
  <div class="row total">
    <span>Total Bill</span>
    <span>₹<%= grandTotal %></span>
  </div>
</div>

<!-- FOOTER -->
<div class="footer">
  <div>
    <div class="total">₹<%= grandTotal %></div>
    <div class="small">Include taxes & charges</div>
  </div>

  <input type="hidden" name="paymentMode" value="COD">
  <input type="hidden" name="amount" value="<%= grandTotal %>">

  <button class="btn">Place Order</button>
</div>

</form>

</body>
</html>
