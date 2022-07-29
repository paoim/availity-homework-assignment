/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
//Create Original Content Controller
contentWidgetApp.controller("OriginalContentController", function($scope, $route, $timeout, pageService, originalContentService, inputFileService) {
	var newPage = {
			isReportPage: true,
			isDetailPage : false,
			isUploadExcelFile : false,//flag to show or hide upload button
			title : "Original Contents",
			uploadLabel : "N/A"
	};

	(function() {
		//Show Animation
		$scope.$emit('LOADPAGE');

		//Pagination configure
		$scope.curPage = 0;
		$scope.pageSize = 50;
		$scope.originalContents = [];
		$scope.numberOfPages = function() {
			return 1;
		};

		originalContentService.getAllOriginalContents(function(data, message) {
			$scope.originalContents = data;
			$scope.numberOfPages = function() {
				return Math.ceil($scope.originalContents.length / $scope.pageSize);
			};
			
			//Hide Animation
			$scope.$emit('UNLOADPAGE');
		});
	})();

	var doNewAction = function() {
		//NA
	};

	pageService.setPage(newPage);
	pageService.setClick(doNewAction);
});
