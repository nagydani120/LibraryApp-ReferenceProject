<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="styles/indexStyle.css" />
<script type="text/javascript" src="scripts/index.js"></script>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login</title>
</head>
<body>
	<h1 class="white-colored">Welcome in the Library!</h1>

	<p class="white-colored">This site is created to login to the
		library to look for the borrowed books and deadlines.</p>

	<div class="mother-div">
		<div class="form-div">


			<form method="POST" action="ValidateLogin">
				<p>Email address:</p>
				<input type="text" name="email_address" value="${emailValue}" /><br />
				<p>Password:</p>
				<input type="password" name="password" /><br /> <input
					type="submit" value="Login" id="login-button" /> <br /> <br /> <a
					href="javascript:methodAlert()">I forgot my password</a>
				<p class="error" id="error">${errorMsg}</p>
			</form>


			<div class="account-address">
				<b>*Need an account? Come and visit us personally!</b><br /> <b>Our
					address:
					<address>Bratislava 987 14, Blatni­cka 17</address> <a
					href="https://www.google.com/maps/place/48%C2%B009'45.9%22N+17%C2%B007'28.2%22E/@48.1627537,17.1244945,17z/data=!3m1!4b1!4m4!3m3!8m2!3d48.1627537!4d17.1244945"
					id="map-loc" target="_blank"><img src="images/mapIcon.png"
						width="20px" height="20px">Our location</a>
				</b>
			</div>
		</div>
	</div>

	<p class="white-colored">*To register, please come personally to
		the library, we create you a member account and you are able to borrow
		book immediately.</p>

</body>
</html>
