'use strict';
 
app.controller('UserController', ['$scope', 'UserService','$rootScope','$location','$cookieStore','$http', function($scope, UserService,$rootScope,$location,$cookieStore,$http) {
    var self = this;
    self.user={username:'',
    		password:'',
    		name:'',
    		email:'',
    		mobile:'',
    		address:'',
    		status:'',
    		role:'',
    		reason:'',
    		isonline:'',
    		errorcode:'',
    		errormessage:''
    		
    
    
    };
    self.users=[];
    
    console.log('INSIDE USER CONTROLLER')
    
   
    self.login=function(){
    	
    	console.log("Inside login controller");
    	console.log('login validation...',self.user);
    	self.authenticate(self.user);
    	
    }
    
    self.authenticate=function(user){
    	
    	UserService.authenticate(user).then(
    		function(d)	{
    			
    			self.user=d;
    			console.log("user.errorcode: " + self.user.errorcode)
    			if(self.user.errorcode=="404"){
    				
    				/*alert("Invalid Credentials...Please try again!")*/
    				alert(self.user.errormessage);
    				self.user.username = "";
					self.user.password = "";
    			}
    			
    			else{
    				console.log("Valid credentials. Navigating to home page.")
    				 /*self.fetchAllUsers();*/
    				$rootScope.currentUser=self.user;
    				
    				/*{
    						
    						username:self.user.username,
    						name:self.user.name,
    						role:self.user.role
    						
    						
    				};*/
    				 console.log("currentUser:" +$rootScope.currentUser)
    				/*$rootScope.currentUser=self.user;*/
    				
    				 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;//This is for converting into encryption but we have to add base64.just attached one word basic
    				 //Just like session.setAttribute("currentUser",user)
    				 $cookieStore.put('currentUser', $rootScope.currentUser);
    				 
    				console.log($rootScope.currentUser)
    				$location.path('/');
    			}
    			
    		},
    		function(errResponse){
				console.error('Error while aunthenticate users');
    		}
			
    	
    	)}
    
    /*If it is json object we have to use {}, 
     * if it is json array we have to use [],
     * if it is single json variable like username,name then we have to use '' */
    self.logout = function() {
		console.log("logout")
		$rootScope.currentUser = {};
		$rootScope.loggedIn = {};
		$cookieStore.remove('currentUser');
		UserService.logout()
		$location.path('/');

	}
    
    self.submit=function(){
    	console.log('Saving new Registration ',self.user);
    	self.createUser(self.user);
    }
    self.createUser=function(user){
    	console.log('Create User....');
    	UserService.createUser(user)
    	.then(
    			function(d){
    				alert('Thank you for Registration')
    				$location.path("/");
    			},
    			function(errResponse){
					console.error('Error while creating user.....');
    			}
    	);
    	
    };
    
    self.fetchAllUsers = function() {
		console.log("fetchAllUsers...")
		UserService
				.fetchAllUsers()
				.then(
						function(d) {
							self.users = d;
						},
						function(errResponse) {
							console
									.error('Error while fetching Users');
						});
	};
	
	 self.getAllUsers = function() {
			console.log("getAllUsers...")
			UserService
					.getAllUsers()
					.then(
							function(d) {
								self.users = d;
							},
							function(errResponse) {
								console
										.error('Error while getting Users');
							});
		};
	
	 self.getAllUsers();//Calling the method
	    
	    //better to call fetchAllUsers ==>after login
	 
	 self.friendRequest=function(friendname){
		 console.log('Friend request method in UserController')
		 UserService.friendRequest(friendname)
		 .then(
				 function(d){
						console.log(d);
						self.users=d;
						alert('Friend request Send')
						self.getAllUsers
						$location.path('/search_friend')
				 },
					function(response){
						console.log(response.data);
					}
				 
		 )
		 
	 };
	
	self.myProfile = function() {
		console.log("myProfile...")
		UserService
				.myProfile()
				.then(
						function(d) {
							self.users = d;
							$location.path("/myProfile")
						},
						function(errResponse) {
							console
									.error('Error while fetch profile.');
						});
	};
	
	self.accept = function(username) {
		console.log("accept...")
		UserService
				.accept(username)
				.then(
						function(d) {
							self.user = d;
							self.fetchAllUsers
							$location.path("/manage_users")
							alert(self.user.errorMessage)
							
						},
						
						function(errResponse) {
							console
									.error('Error while updating User.');
						});
	};
	
	self.reject = function( username) {
		console.log("reject...")
		var reason = prompt("Please enter the reason");
		UserService
				.reject(username,reason)
				.then(
						function(d) {
							self.user = d;
							self.fetchAllUsers
							$location.path("/manage_users")
							alert(self.user.errorMessage)
							
						},
						null );
	};

	self.updateUser = function(user) {
		console.log("updateUser...")
		UserService
				.updateUser(user)
				.then(
						self.fetchAllUsers,
						null);
	};
	
	self.reset = function() {
		self.user = {
				username:'',
	    		password:'',
	    		name:'',
	    		email:'',
	    		mobile:'',
	    		address:'',
	    		status:'',
	    		role:'',
	    		reason:'',
	    		isonline:'',
	    		errorcode:'',
	    		errormessage:''
		};
		$scope.myForm.$setPristine(); // reset Form
	};
    
    
    
}]);