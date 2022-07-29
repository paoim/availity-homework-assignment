/*******************************************************************************
 * Author: Pao Sorn Im
 * Email: paosornim@gmail.com
 ******************************************************************************/
// Create Directive for set focus on input field
contentWidgetApp.directive('focus', function() {
	return {
		link : function(scope, element, attrs) {
			element[0].focus();
		}
	};
});

// Create Directive for Drag and Drop Image
contentWidgetApp.directive('fileDragDrop', function() {
	return {
		restrict : 'A',
		scope : {
			file : '=',
			fileName : '='
		},
		link : function(scope, element, attrs) {
			var checkSize, isTypeValid, processDragOverOrEnter, validMimeTypes, readFile;

			processDragOverOrEnter = function(event) {
				if (event != null) {
					event.preventDefault();
				}
				event.dataTransfer.effectAllowed = 'copy';
				return false;
			};

			validMimeTypes = attrs.fileDragDrop;
			checkSize = function(size) {
				var _ref;
				if (((_ref = attrs.maxFileSize) === (void 0) || _ref === '') || (size / 1024) / 1024 < attrs.maxFileSize) {
					return true;
				} else {
					alert("File must be smaller than " + attrs.maxFileSize + " MB");
					return false;
				}
			};

			isTypeValid = function(type) {
				if ((validMimeTypes === (void 0) || validMimeTypes === '') || validMimeTypes.indexOf(type) > -1) {
					return true;
				} else {
					alert("Invalid file type.  File must be one of following types " + validMimeTypes);
					return false;
				}
			};

			readFile = function(event){
				var file, name, reader, size, type;
				if (event != null) {
					event.preventDefault();
				}

				reader = new FileReader();
				reader.onload = function(evt) {
					if (checkSize(size) && isTypeValid(type)) {
						return scope.$apply(function() {
							scope.file = evt.target.result;
							if (angular.isString(scope.fileName)) {
								return scope.fileName = name;
							}
						});
					}
				};

				file = event.dataTransfer.files[0];
				name = file.name;
				type = file.type;
				size = file.size;
				reader.readAsDataURL(file);
			};

			console.log(scope.file);
			console.log(scope.fileName);
			
			element.bind('dragover', processDragOverOrEnter);
			element.bind('dragenter', processDragOverOrEnter);
			
			return element.bind('drop', function(event) {
				readFile(event);

				return false;
			});
		}
	};
});

//Create Attribute Directive input file
contentWidgetApp.directive('fileUpload', function() {
	return {
		scope : {
			fileUpload : "=",
			fileName : "="
		},
		link : function(scope, element, attributes) {
			element.bind("change", function(changeEvent) {
				var files = event.target.files,
				file = files[0],
				reader = new FileReader(),
				name = file ? file.name : undefined;

				reader.onload = function(loadEvent) {
					scope.$apply(function() {
						scope.fileUpload = loadEvent.target.result;
						scope.fileName = name;
					});
				}
				reader.readAsDataURL(changeEvent.target.files[0]);
			});
		}
	};
});
