<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.tap.dto.Restaurant" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home | FoodExpress</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
:root{
  --primary:#ff6a00;
  --bg:#f6f7f9;
  --dark:#111;
  --muted:#777;
}

*{
  margin:0;
  padding:0;
  box-sizing:border-box;
  font-family:"Segoe UI",sans-serif;
}

body{ background:var(--bg); }

/* HEADER */
header{
  background:#fff;
  padding:16px 32px;
  display:flex;
  justify-content:space-between;
  align-items:center;
  box-shadow:0 4px 12px rgba(0,0,0,.08);
}

.logo{
  font-size:26px;
  font-weight:800;
  color:var(--primary);
}

header a{
  text-decoration:none;
  color:#333;
  font-weight:600;
  margin-left:20px;
}

/* SEARCH */
.search{
  max-width:900px;
  margin:24px auto;
  padding:0 20px;
}

.search input{
  width:100%;
  padding:14px 18px;
  border-radius:14px;
  border:1px solid #ddd;
  font-size:16px;
}

/* CONTENT */
.container{
  max-width:1200px;
  margin:30px auto;
  padding:0 20px;
}

h2{
  margin-bottom:22px;
  font-size:28px;
}

/* GRID */
.grid{
  display:grid;
  grid-template-columns:repeat(auto-fit,minmax(260px,1fr));
  gap:24px;
}

.card{
  background:#fff;
  border-radius:20px;
  overflow:hidden;
  box-shadow:0 12px 28px rgba(0,0,0,.1);
  transition:.3s;
}

.card:hover{ transform:translateY(-6px); }

.card img{
  width:100%;
  height:180px;
  object-fit:cover;
}

.content{
  padding:18px;
}

.content h3{
  font-size:20px;
  margin-bottom:6px;
}

.meta{
  font-size:14px;
  color:var(--muted);
  margin-bottom:10px;
}

.badge{
  display:inline-block;
  background:var(--primary);
  color:#fff;
  padding:4px 12px;
  border-radius:12px;
  font-size:12px;
}

.btn{
  display:inline-block;
  margin-top:12px;
  padding:10px 18px;
  background:var(--primary);
  color:#fff;
  border-radius:14px;
  text-decoration:none;
  font-weight:700;
}

/* NO RESULT */
.no-result{
  display:none;
  text-align:center;
  color:var(--muted);
  padding:40px;
  font-size:18px;
}
</style>
</head>

<body>

<header>
  <div class="logo">🍕 FoodExpress</div>
  <div>
    <a href="cart.jsp">Cart</a>
    <a href="logout">Logout</a>
  </div>
</header>

<!-- SEARCH -->
<div class="search">
  <input type="text" id="searchInput"
         placeholder="Search restaurant or food type (Pizza, Veg, Burger)">
</div>

<!-- RESTAURANTS -->
<div class="container">
  <h2>Restaurants Near You</h2>

<%
@SuppressWarnings("unchecked")
List<Restaurant> restaurants =
    (List<Restaurant>) request.getAttribute("restaurantList");
%>

<div id="noResult" class="no-result">
  ❌ No restaurant found
</div>

<div class="grid" id="restaurantGrid">

<%
if (restaurants != null) {
  for (Restaurant r : restaurants) {
%>
  <div class="card"
       data-name="<%= r.getName().toLowerCase() %>"
       data-food="<%= r.getFoodType().toLowerCase() %>">

    <img src="<%= (r.getImageUrl() != null && !r.getImageUrl().isEmpty())
        ? r.getImageUrl()
        : "https://images.unsplash.com/photo-1555396273-367ea4eb4db5" %>">

    <div class="content">
      <h3><%= r.getName() %></h3>

      <div class="meta">
        ⭐ <%= r.getRating() %> •
        ⏱ <%= r.getEstimatedTime() %> mins
      </div>

      <span class="badge"><%= r.getFoodType() %></span><br>

      <a class="btn"
         href="menu?restaurantId=<%= r.getRestaurantId() %>">
         View Menu
      </a>
    </div>
  </div>
<%
  }
}
%>

</div>
</div>

<!-- SEARCH SCRIPT -->
<script>
const searchInput = document.getElementById("searchInput");
const cards = document.querySelectorAll(".card");
const noResult = document.getElementById("noResult");

searchInput.addEventListener("keyup", function () {
  const value = this.value.toLowerCase().trim();
  let found = false;

  cards.forEach(card => {
    const name = card.dataset.name;
    const food = card.dataset.food;

    if (name.includes(value) || food.includes(value)) {
      card.style.display = "block";
      found = true;
    } else {
      card.style.display = "none";
    }
  });

  noResult.style.display = found ? "none" : "block";
});
</script>

</body>
</html>

