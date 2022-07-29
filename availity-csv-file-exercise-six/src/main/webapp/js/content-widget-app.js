/*################################################################################
  Reference: AngularJS v1.2.18
  
  Pao Sorn Im
  http://pao-profile.appspot.com/

  Normally like to break AngularJS apps into the following folder structure
  at a minimum:

  /webapp
      /css
      /fonts
      /view
      /js
      	/controllers
      	/directives
      	/services
############################################################################################*/
var contentWidgetApp = angular.module("ContentWidgetApp", [ "ngRoute", "ui.bootstrap", "csvWidgetServices"]);


contentWidgetApp.filter('paginationFilter', function() {
	return function(input, start) {
		var newInput = input || [];

		start = +start;
		return newInput.slice(start);
	};
});
