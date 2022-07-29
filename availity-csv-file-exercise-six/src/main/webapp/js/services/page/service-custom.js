/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
// Create Custom Content Service
csvWidgetServices.factory("customContentService", function($rootScope, dataService) {
	var customContents = [],

	setCustomContents = function(newCustomContents) {
		customContents = [];
		customContents = newCustomContents;
	},
	getCustomContents = function() {
		return customContents;
	},
	getCustomContentByIndex = function(index) {
		return customContents[index];
	},
	getCustomContentIndex = function(id) {
		var index;
		for (var i = 0; i < customContents.length; i++) {
			if (customContents[i].id === id) {
				index = i;
				break;
			}
		}
		return index;
	},
	getCustomContent = function(id) {
		var customContent = {};
		for (var i = 0; i < customContents.length; i++) {
			if (customContents[i].id === id) {
				customContent = customContents[i];
				break;
			}
		}
		return customContent;
	},
	addCustomContent = function(newCustomContent) {
		customContents.push(newCustomContent);
	},
	removeCustomContent = function(index) {
		customContents.splice(index, 1);
	},
	loadCustomContents = function(callbackHandler) {
		dataService.getEntities("content/custom", function(data) {
			var newCustomContents = customContents;
			if (data) {
				setCustomContents(data);
				newCustomContents = data;
			}
			callbackHandler(newCustomContents, "Load Custom Contents Successfully...");
		},function(error) {
			callbackHandler([], "Cannot Load Custom Contents - " + error.message);
		}, true);
	},
	uploadContents = function(requestData, callbackHandler) {
		dataService.doUploadFilePost("content/upload", requestData, function(data) {
			var newCustomContents = customContents;
			if (data && data.target && 4 === data.target.readyState && data.target.response) {
				setCustomContents(data.target.response);
				newCustomContents = data.target.response;
			}
			callbackHandler(newCustomContents, "Upload Contents Successfully...");
		},
		function(error) {
			callbackHandler([], "Cannot upload Contents - " + error.message);
		});
	},
	downloadCSV = function(insuranceCompany, callbackHandler) {
		dataService.downloadCSV("content", insuranceCompany, function(data) {
			callbackHandler(data, "Download " + insuranceCompany + " Successfully...");
		},function(error) {
			callbackHandler([], "Cannot Download " + insuranceCompany + " - " + error.message);
		});
	};


	return {
		downloadCSV : downloadCSV,
		uploadContents : uploadContents,
		getCustomContent : getCustomContent,
		addCustomContent : addCustomContent,
		setCustomContents : setCustomContents,
		loadCustomContents : loadCustomContents,
		getCustomContents : getCustomContents,
		removeCustomContent : removeCustomContent,
		getCustomContentIndex : getCustomContentIndex,
		getCustomContentByIndex : getCustomContentByIndex
	};
});
