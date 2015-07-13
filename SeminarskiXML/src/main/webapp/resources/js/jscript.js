function check() {
	var arrayName = [ 'nacinPrevoza', 'posete' ];
	var arrayParam = [ 'drugi', 'broj' ];
	var arrayNumber = [ '4', '1' ];

	for (var i = 0; i < arrayName.length; i++) {
		var radioButton = document.getElementById('form:' + arrayName[i] + ':'
				+ arrayNumber[i]);
		if (radioButton.checked) {
			if (document.getElementById('form:' + arrayParam[i]).value == "") {
				document.getElementById('form:' + arrayParam[i]).style.border = "red 2px solid"
				return false;
			}
		}
		document.getElementById('form:' + arrayParam[i]).style.border = "";
	}

	// preuzmi sliku potpisa, funkcijja se nalazi u signature.js
	getData();

	return true;
}
