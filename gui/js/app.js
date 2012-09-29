
var SimMon = {
	Gui : {
		date: null
	}
};

function InitTypes() {
	RequestXml("/simmon/", function (xmlDoc) {
		var arrItems = $(xmlDoc).find("li");
		var arrOptions = [];
		
		ScanItems(arrItems, function ($tag) {
			var href = $tag.find("a").attr("href");
			
			if (href.length > 1) {
				arrOptions.push({value: href, caption: href});
			}
		});
		
		LoadSelectOptions("selType", arrOptions);
	});
}

function SelectType(strType) {
	var objNow = GetDate();
	
	SimMon.Gui.date = objNow;
	
	LoadHourImages(strType, objNow);
	LoadDayImages(strType, objNow);
}

function LoadPreviousMonth() {
	var strType = $("#selType").val();
	SimMon.Gui.date = MinusMonth(SimMon.Gui.date);
	
	console.debug("SimMon.Gui.date", SimMon.Gui.date);
	
	LoadDayImages(strType, SimMon.Gui.date);
}

function LoadDayImages(strType, objNow) {
	var strMonthPath = MakeMonthPath(strType, objNow) + "/";
	var arrImages = [];
	
	RequestXml(strMonthPath, function (xmlDoc) {
		var arrItems = $(xmlDoc).find("li");
		
		ScanItems(arrItems, function ($tag) {
			var href = $tag.find("a").attr("href");
			
			if (href.indexOf(".png") > -1) {
				arrImages.push({pos: parseInt(href), href: strMonthPath+href});
			}
		});
		
		arrImages.sort(function (prev, next) { return prev.pos-next.pos; });
		
		ShowDayImages("tdDay", arrImages);
	});
}

function ShowDayImages(strId, arrImages) {
	var strData = "";
	
	for (var i=0; i<arrImages.length; i++) {
		strData += '<center><strong>'+arrImages[i].pos+'.</strong></center>'
			+ '<img src="'+arrImages[i].href+'"/>';
	}
	
	$("#"+strId)[0].innerHTML = strData;
}

function LoadHourImages(strType, objNow) {
	var strDayPath = MakeDayPath(strType, objNow) + "/";
	var arrImages = [];
	
	RequestXml(strDayPath, function (xmlDoc) {
		var arrItems = $(xmlDoc).find("li");
		
		ScanItems(arrItems, function ($tag) {
			var href = $tag.find("a").attr("href");
			
			if (href.indexOf(".png") > -1) {
				arrImages.push({pos: parseInt(href), href: strDayPath+href});
			}
		});
		
		arrImages.sort(function (prev, next) { return prev.pos-next.pos; });
		
		ShowHourImages("tdHour", arrImages);
	});
}

function ShowHourImages(strId, arrImages) {
	var strData = "";
	
	for (var i=0; i<arrImages.length; i++) {
		strData += '<img src="'+arrImages[i].href+'"/>';
	}
	
	$("#"+strId)[0].innerHTML = strData;
}

function MakeImagePath(strType, objDate) {
	return MakeDayPath(strType, objDate)+".png";
}

function MakeDayPath(strType, objDate) {
	return "/simmon/"+strType+"/"+objDate.year+"/"+objDate.month+"/"+objDate.day;
}

function MakeMonthPath(strType, objDate) {
	return "/simmon/"+strType+"/"+objDate.year+"/"+objDate.month;
}
