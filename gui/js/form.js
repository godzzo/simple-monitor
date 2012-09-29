
function LoadSelectOptions(strId, arrData) {
	var strData = "";
	
	for (var i=0; i<arrData.length; i++) {
		var objItem = arrData[i];
		strData += '<option value="'+objItem.value+'">'+objItem.caption+'</option>';
	}
	
	$("#"+strId)[0].innerHTML = strData;
}
