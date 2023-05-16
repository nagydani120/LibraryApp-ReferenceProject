const req = new XMLHttpRequest();

function methodAlert() {
	let msg = prompt("Please give your email-address:");

	const emailAddress = "emailAddress=" + encodeURIComponent(msg);


	req.onload = responseToSite;


	req.open('POST', '/LibraryApp/ForgotPassword', true);
	req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	req.send(emailAddress);
};

function responseToSite() {
	if (req.status == 200) {
		var response = req.responseText;
		// regex test (true if after parsing is true(case insensitive))
		if ((/true/).test(response)) {
			alert("New password has been sent! Please check your e-mail.");
		} else if (/false/.test(response)) {
			alert("This email address doasn't exist in database! ");
		} else if (/invalid/.test(response)) {
			alert("Invalid e-mail address!");
		} else {
			alert("Something wrong! Please contact us.");
		}
	}
}

