<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    // 🔒 ADMIN SECURITY CHECK
    String role = (String) session.getAttribute("role");
    if (role == null || !"ADMIN".equals(role)) {
        response.sendRedirect("../login.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add Restaurant | Admin</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
:root{
  --primary:#ff6a00;
  --glass:rgba(255,255,255,.18);
  --border:rgba(255,255,255,.35);
}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",sans-serif}

body{
  min-height:100vh;
  background:linear-gradient(135deg,#ff6a00,#ff9f45);
  display:flex;align-items:center;justify-content:center;
}

.wrapper{
  width:min(1100px,94%);
  display:grid;
  grid-template-columns:1.2fr 1fr;
  border-radius:28px;
  overflow:hidden;
  box-shadow:0 30px 60px rgba(0,0,0,.25);
}

.image{
  background:
    linear-gradient(180deg,rgba(0,0,0,.35),rgba(0,0,0,.6)),
    url("https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&w=1200&q=80");
  background-size:cover;
  background-position:center;
  color:#fff;
  padding:48px;
  display:flex;
  flex-direction:column;
  justify-content:flex-end;
}
.image h1{font-size:40px;margin-bottom:8px}
.image p{opacity:.9}

.card{
  background:var(--glass);
  backdrop-filter:blur(16px);
  border-left:1px solid var(--border);
  padding:44px;
  color:#fff;
}

.card h2{text-align:center;margin-bottom:22px}

.grid{
  display:grid;
  grid-template-columns:1fr 1fr;
  gap:14px;
}

.field{margin-bottom:14px}
.field label{font-size:14px;margin-bottom:6px;display:block}
.field input,.field select{
  width:100%;
  padding:12px 14px;
  border-radius:12px;
  border:none;
  outline:none;
}

.full{grid-column:1 / -1}

.preview{
  margin-top:10px;
  width:100%;
  height:180px;
  object-fit:cover;
  border-radius:12px;
  display:none;
}

button{
  width:100%;
  padding:14px;
  margin-top:10px;
  border-radius:14px;
  border:none;
  font-size:16px;
  font-weight:700;
  background:#fff;
  color:var(--primary);
  cursor:pointer;
  transition:.25s;
}
button:hover{
  transform:translateY(-2px);
  box-shadow:0 12px 28px rgba(0,0,0,.25)
}

@media (max-width:900px){
  .wrapper{grid-template-columns:1fr}
  .image{min-height:260px}
}
</style>
</head>

<body>

<div class="wrapper">

  <!-- LEFT IMAGE -->
  <div class="image">
    <h1>Onboard a Restaurant 🍴</h1>
    <p>Add partners, manage menus, and start receiving orders.</p>
  </div>

  <!-- RIGHT FORM -->
  <div class="card">
    <h2>Add Restaurant</h2>

    <!-- ✅ ADMIN SERVLET -->
    <form action="addRestaurant" method="post">
      <div class="grid">

        <div class="field">
          <label>Restaurant Name</label>
          <input type="text" name="name" required>
        </div>

        <div class="field">
          <label>Food Type</label>
          <select name="foodType" required>
            <option value="">Select</option>
            <option value="VEG">VEG</option>
            <option value="NON_VEG">NON-VEG</option>
            <option value="BOTH">BOTH</option>
          </select>
        </div>

        <div class="field full">
          <label>Address</label>
          <input type="text" name="address" required>
        </div>

        <div class="field">
          <label>Rating</label>
          <input type="number" step="0.1" name="rating" value="0.0">
        </div>

        <div class="field">
          <label>Estimated Time (mins)</label>
          <input type="number" name="estimatedTime" value="30">
        </div>

        <div class="field full">
          <label>Image URL</label>
          <input type="text" name="imageUrl" id="imageUrl"
                 placeholder="https://images.unsplash.com/...">
          <img id="preview" class="preview">
        </div>

      </div>

      <button type="submit">Add Restaurant</button>
    </form>
  </div>
</div>

<script>
const imgInput = document.getElementById("imageUrl");
const preview = document.getElementById("preview");

imgInput.addEventListener("input", () => {
  if (imgInput.value.trim() !== "") {
    preview.src = imgInput.value;
    preview.style.display = "block";
  } else {
    preview.style.display = "none";
  }
});
</script>

</body>
</html>
