/********************************************
** Author: Pao Sorn Im
** Email: paosornim@gmail.com
*********************************************/
contentWidgetApp.config(function($routeProvider, $locationProvider){
	$routeProvider.
	when("/origin",
	{
		controller : "OriginalContentController",
		templateUrl : "view/origin.html"
	}).
	when("/custom",
	{
		controller : "CustomContentController",
		templateUrl : "view/custom.html"
	}).
	otherwise({
		redirectTo : "/origin"
	});
	
	// configure html5 to get links working on jsfiddle
	//$locationProvider.html5Mode(true);
});
