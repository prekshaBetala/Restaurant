<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.math.BigDecimal,com.tap.dto.OrderItem" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart | 404HUNGRY</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
:root{
  --primary:#ff6a00;
  --bg:#f4f5f7;
  --card:#fff;
  --text:#222;
  --muted:#777;
  --success:#3bd16f;
}

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
  font-family:"Segoe UI",sans-serif;
}

body{
  background:var(--bg);
  color:var(--text);
}

/* HEADER */
header{
  background:#fff;
  padding:16px 24px;
  display:flex;
  justify-content:space-between;
  align-items:center;
  box-shadow:0 4px 12px rgba(0,0,0,.08);
  position:sticky;
  top:0;
  z-index:10;
}

.logo{
  font-size:24px;
  font-weight:800;
  color:var(--primary);
}

header a{
  text-decoration:none;
  color:#333;
  font-weight:600;
  margin-left:18px;
}

/* LAYOUT */
.container{
  max-width:900px;
  margin:24px auto;
  padding:0 16px;
  padding-bottom:120px;
}

/* CARD */
.card{
  background:var(--card);
  border-radius:16px;
  padding:16px;
  margin-bottom:16px;
  box-shadow:0 8px 24px rgba(0,0,0,.08);
}

/* ITEM */
.item{
  display:flex;
  justify-content:space-between;
  align-items:center;
  padding:12px 0;
  border-bottom:1px solid #eee;
}

.item:last-child{border-bottom:none}

.item-name{
  font-weight:700;
  font-size:16px;
}

.price{
  color:var(--muted);
  font-size:14px;
  margin-top:4px;
}

/* QTY BOX */
.qty-box{
  display:flex;
  align-items:center;
  background:#f1f1f1;
  border-radius:10px;
  overflow:hidden;
}

.qty-box form{margin:0}

.qty-btn{
  background:#fff;
  border:none;
  padding:6px 14px;
  font-size:18px;
  font-weight:700;
  cursor:pointer;
}

.qty-count{
  padding:6px 14px;
  font-weight:800;
}

/* BILL */
.bill-row{
  display:flex;
  justify-content:space-between;
  margin:10px 0;
  color:#444;
  font-size:15px;
}

.bill-total{
  font-size:20px;
  font-weight:800;
}

/* EMPTY CART */
.empty{
  text-align:center;
  padding:80px 20px;
  color:var(--muted);
}

.empty a{
  display:inline-block;
  margin-top:14px;
  background:var(--primary);
  color:#fff;
  padding:10px 18px;
  border-radius:14px;
  text-decoration:none;
  font-weight:700;
}

/* FOOTER */
.footer{
  position:fixed;
  bottom:0;
  left:0;
  right:0;
  background:#fff;
  border-top:1px solid #ddd;
  padding:14px 18px;
  display:flex;
  justify-content:space-between;
  align-items:center;
}

.pay-btn{
  background:var(--success);
  color:#000;
  border:none;
  padding:14px 24px;
  font-size:16px;
  font-weight:800;
  border-radius:14px;
  cursor:pointer;
}
</style>
</head>

<body>

<header>
  <div class="logo">🛒 Your Cart</div>
  <div>
    <a href="home">Home</a>
    <a href="logout">Logout</a>
  </div>
</header>

<%
@SuppressWarnings("unchecked")
Map<Integer, OrderItem> cart =
    (Map<Integer, OrderItem>) session.getAttribute("cart");

if (cart == null || cart.isEmpty()) {
%>
  <div class="empty">
    <h2>Your cart is empty</h2>
    <p>Add items from restaurants to start ordering</p>
    <a href="home">Browse Restaurants</a>
  </div>
</body></html>
<%
  return;
}

BigDecimal itemTotal = BigDecimal.ZERO;
for (OrderItem i : cart.values()) {
    itemTotal = itemTotal.add(i.getTotalPrice());
}

BigDecimal discount = new BigDecimal("20");
BigDecimal grandTotal = itemTotal.subtract(discount);
%>

<div class="container">

<!-- ITEMS -->
<div class="card">
<%
for (OrderItem item : cart.values()) {
%>
  <div class="item">
    <div>
      <div class="item-name"><%= item.getItemName() %></div>
      <div class="price">₹ <%= item.getTotalPrice() %></div>
    </div>

    <div class="qty-box">
      <form action="updateCartQty" method="post">
        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
        <input type="hidden" name="action" value="decrease">
        <button class="qty-btn">−</button>
      </form>

      <div class="qty-count"><%= item.getQuantity() %></div>

      <form action="updateCartQty" method="post">
        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
        <input type="hidden" name="action" value="increase">
        <button class="qty-btn">+</button>
      </form>
    </div>
  </div>
<%
}
%>
</div>

<!-- BILL -->
<div class="card">
  <div class="bill-row">
    <span>Item Total</span>
    <span>₹<%= itemTotal %></span>
  </div>
  <div class="bill-row">
    <span>Delivery Fee</span>
    <span>FREE</span>
  </div>
  <div class="bill-row">
    <span>Discount</span>
    <span>- ₹20</span>
  </div>
  <hr>
  <div class="bill-row bill-total">
    <span>To Pay</span>
    <span>₹<%= grandTotal %></span>
  </div>
</div>

</div>

<!-- FOOTER -->
<form action="checkout.jsp" method="get">
<div class="footer">
  <div>
    <div class="bill-total">₹<%= grandTotal %></div>
    <small>Inclusive of all taxes</small>
  </div>

  <input type="hidden" name="paymentMode" value="COD">
  <input type="hidden" name="address" value="Default Address">
<input type="hidden" name="amount" value="<%= grandTotal %>">

  <button class="pay-btn">Proceed to Pay</button>
</div>
</form>

</body>
</html>

