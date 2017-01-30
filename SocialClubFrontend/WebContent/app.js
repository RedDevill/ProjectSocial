//var app = angular.module('myApp', [ 'ngRoute','ngCookies','ngFileUpload' ]);
var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider


	.when('/',{
		templateUrl : 'S_common/body2.html',
		
		
	})

	/*
	 * 
	 * Admin Mapping*/
/*
	.when('/manage_users',{
	templateUrl : 'S_admin/manage_users.html',
	controller : 'UserController'
	})
*/
	/*
	 * 
	 * User Mapping*/


	.when('/login',{
	templateUrl : 'S_user/login.html',
	controller : 'UserController'
	})

	.when('/logout',{
	templateUrl : '/',
	controller : 'UserController'
	})

	.when('/register',{
	templateUrl : 'S_user/register.html',
	controller : 'UserController'
	})

	.when('/myprofile',{
	templateUrl : 'S_user/my_profile.html',
	controller : 'UserController'
	})
	
});


app.controller('CarouselDemoCtrl',['$scope',function($scope){
			
		$scope.myInterval=50000;
		$scope.slides=[
		               { image1:'lib/images/Basketball3.jpg'},
		               { image1:'lib/images/cricket3.jpg'},
		               { image1:'lib/images/football4.jpg'},
		               ];
		
	
}]);


app.run( function ($rootScope, $location,$cookieStore, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		 //http://localhost:8080/Collaboration/addjob
	        // redirect to login page if not logged in and trying to access a restricted page
	        var restrictedPage = $.inArray($location.path(), ['','/','/search_job','/view_blog','/login', '/register','/list_blog']) === -1;
		 console.log("Navigating to page :" + $location.path())
	        console.log("restrictedPage:" +restrictedPage)
	        console.log("currentUser:" +$rootScope.currentUser)
	        var loggedIn = $rootScope.currentUser.id;
	        
	        console.log("loggedIn:" +loggedIn)
	        
	        if(!loggedIn)
	        	{
	        	
	        	 if (restrictedPage) {
		        	  console.log("Navigating to login page:")
		        	

						            $location.path('/login');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 var userRestrictedPage = $.inArray($location.path(), ["/post_job"]) == 0;
				 
				 if(userRestrictedPage && role!='admin' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/login');
					 
					 }
				     
	        	
	        	}
	        
	 }
	       );
	 
	 
	 // keep user logged in after page refresh
    $rootScope.currentUser = $cookieStore.get('currentUser') || {};
    if ($rootScope.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
    }

});


