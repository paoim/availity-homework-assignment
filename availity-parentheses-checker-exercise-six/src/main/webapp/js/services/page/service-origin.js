/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
// Create Original Content Service
csvWidgetServices.factory("originalContentService", function($rootScope, dataService) {
	// Private Methods
	var getAllOriginalContents = function(callbackHandler) {
		dataService.getEntities("content/origin", function(data) {
			callbackHandler(data, "Load All Original Contents Successfully...");
		},function(error) {
			callbackHandler([], "Cannot Load All Original Contents - " + error.message);
		}, true);
	};

	// public methods
	return {
		getAllOriginalContents : getAllOriginalContents
	};
});
