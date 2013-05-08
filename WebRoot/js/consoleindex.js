$(function() {
	var values = 1;
	$('#usermsg').click(function() {

		if (values == 1) {
			$('#usermsgdet').show();
			values = 0;
		} else {
			$('#usermsgdet').hide();
			values = 1;
		}

	});
	$('#problemmsg').click(function() {

		if (values == 1) {
			$('#problemmsgdet').show();
			values = 0;
		} else {
			$('#problemmsgdet').hide();
			values = 1;
		}

	});
});