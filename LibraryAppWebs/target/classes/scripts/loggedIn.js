function send(personId, bookId) {
	var req = new XMLHttpRequest();

	var sure = confirm("Are you sure to extend this books deadline 10 days?");

	if (sure) {

		req.onload = function() {
			location.reload();
		}

		var personId = encodeURIComponent(personId);
		var bookId = encodeURIComponent(bookId);
		req.open("POST", "/LibraryApp/ExtendDeadline");
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.send("personId=" + personId + "&bookId=" + bookId);
	}
}

function logout(){
	localStorage.clear();
	location.reload();
	window.location.replace("/LibraryApp");
}