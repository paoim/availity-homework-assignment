/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
var csvWidgetServices = angular.module("csvWidgetServices", ["genericDataService", "inputFileModule", "genericUtilService", "genericDatePickerService"]);

//Create Page Service
csvWidgetServices.factory("pageService", function($rootScope){
	//private variable
	var page = {
		title : "N/A",
		uploadLabel : "N/A",
		isReportPage : false,
		isDetailPage : false,
		isUploadExcelFile : false
	},

	//private method
	doAction = function(){
		//Will implement on detail page
	};

	return {
		//public functions
		getPage : function(){
			return page;
		},
		setPage : function(newPage){
			page = newPage;
		},
		doClick : function(){
			doAction();
		},
		setClick : function(doNewAction){
			doAction = doNewAction;
		}
	};
});
