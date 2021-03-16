function clearFilter() {
	window.location = '/employees';
}

$(document).ready(function() {

	$('.table .delBtn').on('click', function(event) {
		event.preventDefault();

		var href = $(this).attr('href');

		//Code to display the product detail on the delete Modal 

		var table = document.getElementById("table"), rIndex, cIndex;
		for (i = 0; i < table.rows.length; i++)
			for (j = 0; j < table.rows[i].cells.length; j++) {
				table.rows[i].cells[j].onclick = function() {
					alert("function triggered");
					rIndex = this.parentElement.rowIndex;
					var temp = table.rows[rIndex].cells[1].innerHTML;
					var divmain = document.getElementById("main");
					divmain.innerHTML = "do you want to delete the employee: " + temp + " ?";
					alert("innerhtml value: " + divmain.innerHTML);
				}
			}

		$('#deleteModal #delRef').attr('href', href);
		alert("this is the href: " + href);
		$('#deleteModal').modal('show');
	});
});

$(document).ready(function() {
	$(".dropdown-toggle").dropdown();
});


