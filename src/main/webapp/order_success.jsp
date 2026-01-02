<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
String paymentMethod = (String) session.getAttribute("paymentMethod");
if(paymentMethod == null){
    paymentMethod = "Not Available";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmed | Food App</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
:root{
  --primary:#ff6a00;
  --bg:#f6f7f9;
  --card:#ffffff;
  --text:#222;
  --muted:#777;
}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",sans-serif}
body{
  min-height:100vh;
  background:linear-gradient(135deg,#ff6a00,#ff9f45);
  display:flex;align-items:center;justify-content:center;
}

.card{
  width:min(520px,92%);
  background:rgba(255,255,255,.22);
  backdrop-filter:blur(16px);
  border:1px solid rgba(255,255,255,.35);
  border-radius:26px;
  padding:44px;
  text-align:center;
  color:#fff;
  box-shadow:0 30px 60px rgba(0,0,0,.25);
  animation:pop .8s ease;
}

@keyframes pop{
  from{opacity:0;transform:scale(.9)}
  to{opacity:1;transform:scale(1)}
}

.icon{
  width:90px;height:90px;
  border-radius:50%;
  background:#fff;
  color:var(--primary);
  display:flex;align-items:center;justify-content:center;
  font-size:46px;
  margin:0 auto 18px;
  box-shadow:0 12px 28px rgba(0,0,0,.25);
}

h2{font-size:28px;margin-bottom:10px}
p{opacity:.95;margin-bottom:22px}

.info{
  background:#fff;
  color:var(--text);
  border-radius:18px;
  padding:16px;
  margin:18px 0;
}
.info div{
  display:flex;justify-content:space-between;
  margin:6px 0;color:#444;
}

.actions{
  display:flex;gap:12px;flex-wrap:wrap;justify-content:center;
}
.btn{
  padding:12px 18px;
  border-radius:14px;
  border:none;
  font-size:15px;
  font-weight:700;
  cursor:pointer;
  transition:.25s;
}
.primary{background:#fff;color:var(--primary)}
.secondary{background:rgba(255,255,255,.85);color:#333}
.btn:hover{transform:translateY(-2px);box-shadow:0 12px 28px rgba(0,0,0,.25)}

.footer{
  margin-top:18px;font-size:13px;opacity:.85
}
</style>
</head>

<body>

<div class="card">
  <div class="icon">✓</div>

  <h2>Order Placed Successfully!</h2>
  <p>Your delicious food is on the way 🍽️</p>

  <div class="info">
    <div><span>Status</span><strong>Confirmed</strong></div>
    <div><span>Payment Method</span><strong><%= paymentMethod %></strong></div>
    <div><span>Estimated Time</span><strong>25–35 mins</strong></div>
  </div>

  <div class="actions">
    <a href="home"><button class="btn primary">Back to Home</button></a>
    <a href="menu"><button class="btn secondary">Order More</button></a>
  </div>

  <div class="footer">
    Thank you for ordering with us ❤️
  </div>
</div>

</body>
</html>
