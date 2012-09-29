
function GetDate(objDate) {
	if (!objDate) {
		objDate = new Date();
	}
	
	return {
		year: objDate.getYear()+1900,
		month: objDate.getMonth()+1,
		day: objDate.getDate()
	};
}

function MinusMonth(objDate) {
	if (objDate.month > 1) {
		objDate.month--;
	} else {
		objDate.year--;
		objDate.month = 12;
	}
	
	return objDate;
}

function MinusDay(objDate) {
	objDate.day--;
	
	var objCalc = new Date(objDate.year, objDate.month-1, objDate.day);
	
	return GetDate(objCalc);
}
