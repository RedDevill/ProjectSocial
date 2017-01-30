'use strict';
 
app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("UserService...")
	
	var BASE_URL='http://localhost:8081/CollaborationBackEnd'
		
    return {
         
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
            
            accept: function(id) {
            	console.log("calling approve ")
                    return $http.get(BASE_URL+'/accept/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while accept registration');
                                       
                                    }
                            );
            },
            
            reject: function(id, reason) {
            	console.log("calling reject ")
                    return $http.get(BASE_URL+'/reject/'+id+'/'+reason)
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
             
            createUser: function(user){
            	console.log("calling create user")
                    return $http.post(BASE_URL+'/user/', user) //1
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateUser: function(user, id){
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
        
        
            
            authenticate: function(user){
            	   console.log("Calling the method authenticate with the user :"+user)
          		 
                return $http.post(BASE_URL+'/user/authenticate/',user)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                               null
                        );
        }
         
    };
 
}]);