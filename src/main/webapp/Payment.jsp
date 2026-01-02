<!DOCTYPE html>
<html>
<head>
<title>Payment</title>
</head>

<body>

<h2>Select Payment Method</h2>

<form action="payment" method="post">

    <input type="radio" name="paymentMethod" value="COD" required>
    Cash on Delivery <br><br>

    <input type="radio" name="paymentMethod" value="PhonePe">
    PhonePe <br><br>

    <input type="radio" name="paymentMethod" value="GPay">
    Google Pay <br><br>

    <input type="radio" name="paymentMethod" value="UPI">
    UPI <br><br>

    <button type="submit">Pay Now</button>

</form>

</body>
</html>
