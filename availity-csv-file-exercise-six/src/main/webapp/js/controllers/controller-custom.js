/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
//Create Custom Content Controller
contentWidgetApp.controller("CustomContentController", function($scope, $route, $timeout, pageService, customContentService, inputFileService) {
	var newPage = {
		isDetailPage : false,
		isUploadExcelFile : true,//flag to show or hide upload button
		title : "Modify Contents",
		uploadLabel : "Click to upload CSV File"
	},
	loadCustomContentList = function() {
		//Show Animation
		$scope.$emit('LOADPAGE');

		//Pagination configure
		$scope.curPage = 0;
		$scope.pageSize = 200;
		$scope.customContentList = [];
		$scope.numberOfPages = function() {
			return 1;
		};

		customContentService.loadCustomContents(function(customContentList, message) {
			$scope.customContentList = customContentList;
			$scope.numberOfPages = function() {
				return Math.ceil($scope.customContentList.length / $scope.pageSize);
			};

			//Hide Animation
			$scope.$emit('UNLOADPAGE');
		});
	};

	//Init Data
	loadCustomContentList();

	//Upload Excel file
	var doNewAction = function() {
		//$timeout(function() {
			var fileSelector = inputFileService.getSelectorById("fileElement");
			inputFileService.loadFileDialog(fileSelector);

			inputFileService.addFileSelectedListener(fileSelector, function() {
				var files = this.files;

				if (files && files.length > 0) {
					var file = this.files[0],
					fileName = file.name,
					fileSize = parseInt(file.size / 1024),
					requestData = {
						fileId : 0,
						fileRequest : file,
						fileName : fileName,
						fileSize : file.size
					};
					console.log("Upload file's size: " + fileSize + "KB");

					if (fileName.indexOf("availity") > -1) {
						//Show Animation
						$scope.$emit('LOADPAGE');

						//Pagination configure
						$scope.curPage = 0;
						$scope.pageSize = 200;
						$scope.customContentList = [];
						$scope.numberOfPages = function() {
							return 1;
						};

						customContentService.uploadContents(requestData, function(newCustomContentList, message) {
							$scope.customContentList = newCustomContentList;
							$scope.numberOfPages = function() {
								return Math.ceil($scope.customContentList.length / $scope.pageSize);
							};
							$route.reload();//Reload Page
							window.location.reload();

							//Hide Animation
							$scope.$emit('UNLOADPAGE');
						});
					} else {
						alert("Your file name must be availity.csv!");
					}
				}

				//clear input file after loading file dialog
				inputFileService.clearFileInput(this);
			});
		//}, 0, false);
	};

	$scope.downloadCSV = function(index) {
		var customContent = customContentService.getCustomContentByIndex(index);
		customContentService.downloadCSV(customContent.insuranceCompany, function(customContentList, message) {
			alert(message);
		});
	};

	pageService.setPage(newPage);
	pageService.setClick(doNewAction);
});
