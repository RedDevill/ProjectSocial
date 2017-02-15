'use strict';
 
app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var BASE_URL = 'http://localhost:8080/DemoSocialBack';
 return{
         
	 fetchAllPendingFriends: function() {
     	console.log("calling fetchAllPendingFriends ")
             return $http.get(BASE_URL+'/pendingusers')
                     .then(
                             function(response){
                            	 console.log('fetchAllPendingFriends details iin FriendController',response.data)
                            	  $rootScope.selectedPending=response.data;
                                 return response.data;
                                 
                             }, 
                            function(errResponse){
                                 console.error('Error while fetching all  friend request method in Friend Service');
                                
                             }
                     );
     },
     
     fetchAllAcceptedFriends: function() {
      	console.log("calling fetchAllAcceptedFriends ")
              return $http.get(BASE_URL+'/acceptedusers')
                      .then(
                              function(response){
                             	 console.log('fetchAllAcceptedFriends details iin FriendController',response.data)
                             	 $rootScope.selectedAccepted=response.data;
                             	 console.log('SelectAccepted',$rootScope.selectedAccepted)
                                  return response.data;
                                  
                              }, 
                             function(errResponse){
                                  console.error('Error while fetching all  friend request method in Friend Service');
                                 
                              }
                      );
      },
      
     
     
   
     myProfile: function(friend_name) {
     	console.log("calling user details in FriendService ")
             return $http.get(BASE_URL+'/friendProfile/'+friend_name)
                     .then(
                    		
                             function(response){
                            	 console.log('Friend name in FriendService',friend_name)
                                 return response.data;
                             },
                             function(errResponse){
                            	 console.log('Friend name in FriendService',friend_name)
                                 console.error('Error while fetching friend details  in Friend Service');
                                
                             }
                           
                     );
     },
     accept: function(username) {
     	console.log("calling accept friend request method in Friend Service ")
             return $http.put(BASE_URL+'/acceptFriend/'+username)
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while accepting friend request method in Friend Service');
                                
                             }
                     );
     },
     reject: function(username) {
     	console.log("calling reject friend request method in Friend Service ")
             return $http.put(BASE_URL+'/rejectFriend/'+username)
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while reject the friend request method in Friend Service');
                                 return $q.reject(errResponse);
                             }
                     );
     },
    
       
    
 

 
 
 }}]);