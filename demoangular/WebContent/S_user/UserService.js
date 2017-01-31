'use strict';
 
app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var BASE_URL ='http://localhost:8080/SocialClubBackend';
 return{
        authenticate: function(user){
        	console.log('calling authenticate method');
    	return $http.post(BASE_URL+'/login/',user)
    	.then(
    		function(response){
    			
    			if(response.data.errorMessage==""){
					$rootScope.currentUser = {
							name:response.data.name,
							username:response.data.username,
							role:response.data.role
					};
				}
				return response.data;
			},
    		function(errResponse){
    			
				$rootScope.userLoggedIn=false;
				console.error('Error while getting user');
				return $q.reject(errResponse);
    		});
    	},
 
 createUser:function(user){
	 console.log('createUser Method in UserService')
	 return $http.post(BASE_URL+'/register/',user)
	 .then(
			 function(response){
				 return response.data;
			 },
			 function(errResponse){
				 console.error('Error while creating the User');
				 return $q.reject(errResponse);
				 
			 });
	 },
	 
	 fetchAllUsers: function() {
     	console.log("calling fetchAllUsers ")
             return $http.get(BASE_URL+'/users')
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                            null
                     );
     },
     
     getAllUsers: function() {
      	console.log("calling getAllUsers in Userservice ")
              return $http.get(BASE_URL+'/getUsers')
                      .then(
                              function(response){
                                  return response.data;
                              }, 
                             null
                      );
      },
     myProfile: function() {
     	console.log("calling fetchAllUsers ")
             return $http.get(BASE_URL+'/myProfile')
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                            null
                     );
     },
     accept: function(username) {
     	console.log("calling approve ")
             return $http.get(BASE_URL+'/accept/'+username)
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while accept registration');
                                
                             }
                     );
     },
     reject: function(username, reason) {
     	console.log("calling reject ")
             return $http.get(BASE_URL+'/reject/'+username+'/'+reason)
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while reject registration');
                                 return $q.reject(errResponse);
                             }
                     );
     },
     updateUser: function(user){
     	console.log("calling fetchAllUsers ")
             return $http.put(BASE_URL+'/user/', user)  //2
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while updating user');
                                 return $q.reject(errResponse);
                             }
                     );
     },
      
       
     logout: function(){
     	console.log('logout....')
         return $http.get(BASE_URL+'/user/logout')
                 .then(
                         function(response){
                             return response.data;
                         }, 
                       null
                 );
 },
 
 friendRequest:function(friendname){
	 console.log('friend Request Method in UserService')
	 return $http.post(BASE_URL+'/friendRequest/',friendname)
	 .then(
			 function(response){
                 return response.data;
             }, 
             function(errResponse){
                 console.error('Error while sending the friendRequest');
                
             }
     );
 }
 
 
 }}]);