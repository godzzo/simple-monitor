
function RequestXml(strUrl, fncHandler) {
	$.ajax({
		type: "GET",
		url: strUrl,
		
		success: function (strText) {
			var xmlDoc = ParseXml(strText);
			
			fncHandler(xmlDoc);
		},
		
		statusCode: {
			404: function() {
				alert("Date range not found!");
			}
		}
	});
}

function ParseXml(strText) {
	console.debug("strText", strText);
	
	strText = strText.substring(strText.indexOf("<html>"))
	
	var xmlDoc = $.parseXML(strText);
	
	console.debug("xmlDoc", xmlDoc);
	
	return xmlDoc;
}

function ScanItems(arrItems, fncTagHandler) {
	for (var i=0; i<arrItems.length; i++) {
		var tagItem = arrItems[i];
		
		fncTagHandler($(tagItem));
	}
}
