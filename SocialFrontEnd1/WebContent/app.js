var app=angular.module('myApp',['ngRoute','ui.bootstrap','ngCookies']);

app.config(function($routeProvider){
	console.log("APP.js ");
	
$routeProvider

.when('/',{
	templateUrl : 'l_common/body.html',
	
	
})

/*
 * 
 * Admin Mapping*/

.when('/manage_users',{
templateUrl : 'l_admin/manage_users.html',
controller : 'UserController'
})

/*
 * 
 * User Mapping*/


.when('/login',{
templateUrl : 'l_user/login.html',
controller : 'UserController'
})

.when('/logout',{
templateUrl : '/',
controller : 'UserController'
})

.when('/registration',{
templateUrl : 'l_user/registration.html',
controller : 'UserController'
})

.when('/myprofile',{
templateUrl : 'l_user/myprofile.html',
controller : 'UserController'
})

/*
 * Blog Mapping
 * */

.when('/create_blog',{
templateUrl : 'l_blog/create_blog.html',
controller : 'BlogController'
})

.when('/list_blog',{
templateUrl : 'l_blog/list_blog.html',
controller : 'BlogController'
})
.when('/view_blog',{
templateUrl : 'l_blog/view_blog.html',
controller : 'BlogController'
})




/*
 * Forum Mapping
 * */

.when('/create_forum',{
templateUrl : 'l_forum/create_forum.html',
controller : 'ForumController'
})

.when('/view_forum',{
templateUrl : 'l_forum/view_forum.html',
controller : 'ForumController'
})
.when('/list_forum',{
templateUrl : 'l_forum/list_forum.html',
controller : 'ForumController'
})


/*
 * Event Mapping
 * */

.when('/create_event',{
templateUrl : 'l_event/create_event.html',
controller : 'EventController'
})

.when('/view_event',{
templateUrl : 'l_event/view_event.html',
controller : 'EventController'
})

/*
 * 
 * Friend Mapping*/
.when('/search_friend',{
templateUrl : 'l_friend/search_friend.html',
controller : 'UserController'
})
.when('/friend_list',{
templateUrl : 'l_friend/friend_list.html',
controller : 'FriendController'
})
.when('/pending_request',{
templateUrl : 'l_friend/pending_request.html',
controller : 'FriendController'
})
.when('/friend_details',{
templateUrl : 'l_friend/friend_details.html',
controller : 'FriendController'
})

/*
 * 
 * jOB Mapping*/

.when('/post_job',{
templateUrl : 'l_job/post_job.html',
controller : 'JobController'
})
.when('/view_applied_job',{
templateUrl : 'l_job/view_applied_job.html',
controller : 'JobController'
})
.when('/view_jobdetails',{
templateUrl : 'l_job/view_jobdetails.html',
controller : 'JobController'
})
.when('/search_job',{
templateUrl : 'l_job/search_job.html',
controller : 'JobController'
})

/*
 * 
 * Chat Mapping*/

.when('/chat',{
templateUrl : 'l_chat/chat.html',

})


.otherwise({redirectTo: '/'});
});

app.controller('CarouselDemoCtrl', ['$scope',function($scope) {
	 
	  $scope.myInterval = 50000;
	  $scope.slides = [
	     { image1: 'lib/image/banner1.jpg' },
	    { image1: 'lib/image/banner2.jpg'},
	    { image1: 'lib/image/banner3.jpg' },
	    { image1: 'lib/image/banner4.jpg' },
	    
	  ];
}]);
app.run( function ($rootScope, $location,$cookieStore, $http) {

	//$on is monitoring
	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		 //http://localhost:8080/Collaboration/addjob
	        // redirect to login page if not logged in and trying to access a restricted page
		 //$.inArray(x,A)==1 =>it will return 1(true) or -1(false).If the x is there in array A it will return true otherwise false.
		 //Without Login they can access these pages.
		 //If the location.path is not equal to /search_job or '/view_blog' then this is restricted page.
	        var restrictedPage = $.inArray($location.path(), ['','/','/search_job','/manage_users','/view_jobdetails','/post_job','/view_applied_job','/view_blog','/login', '/registration','/list_blog','/create_blog','/create_forum','/view_forum','/list_forum','/search_friend','/pending_request','/friend_details','/friend_list']) === -1;
		 console.log("Navigating to page :" + $location.path())
	        console.log("restrictedPage:" +restrictedPage)
	        console.log("currentUser:" +$rootScope.currentUser.username)
	        var loggedIn = $rootScope.currentUser.username;
		 $rootScope.loggedIn=loggedIn;
		/* var loggedInRole=$rootScope.currentUser.role;
		 if(loggedInRole=='admin')
			 {$rootScope.loggedInRole=loggedInRole;}*/
		 
		 console.log('value of loggedin',$rootScope.loggedIn)
	        
	        console.log("loggedIn:" +loggedIn)
	        
	        if(!loggedIn)
	        	{
	        	
	        	 if (restrictedPage) {
		        	  console.log("Navigating to login page:")
		        	

						            $location.path('/');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 /*var userRestrictedPage = $.inArray($location.path(), ["/post_job"]) == 0;
				 
				 if(userRestrictedPage && role!='admin' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/login');
					 
					 }*/
				     
	        	
	        	}
	        
	 }
	       );
	 
	 
	 // keep user logged in after page refresh
    $rootScope.currentUser = $cookieStore.get('currentUser') || {};
    if ($rootScope.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
    }

});

