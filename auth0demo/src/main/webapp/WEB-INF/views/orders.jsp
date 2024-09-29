
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Orders</title>
</head>
<body>
  <h1>Your Orders</h1>
  <!-- List all user orders here -->
</body>
<script>
	window.history.pushState(null, "", window.location.href);
	window.onpopstate = function () {
	    window.history.pushState(null, "", window.location.href);
	};

</script>
</html>
