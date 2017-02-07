'use strict'
app.factory('ForumService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8081/LetzchaatBackEnd';
 return{
	 
	 fetchAllForums:function(){
	 console.log('fetchAllforums Method in forumServices')
	 return $http.get(REST_SERVICE_URI+'/forums/')
	 .then(
	function(response){
		console.log('success in forumService',response.data)
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the forums');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 createForum:function(forum){
	 console.log('forum service')
	 return $http.post(REST_SERVICE_URI+'/createforum/',forum)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the forum');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
 },
 getForum:function(id){
	 return $http.get(REST_SERVICE_URI+'/getforum/'+id)
	 .then(function(response){
		 
		 $rootScope.selectedforum=response.data;
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting forum');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 deleteForum:function(id){
	 return $http.del(REST_SERVICE_URI+'/deleteforum/'+id)
	 .then(function(response){
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while deleting the forum');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 updateForum:function(forumid){
	 return $http.put(REST_SERVICE_URI+'/updateforum/'+forumid)
	 .then(function(response){
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while updating the forum');
		 return $q.reject(errResponse);
	 }
			 
	 );
 },
 
 createForumComment:function(forumcomment,forumid){
	 console.log('forum Comment service')
	 return $http.post(REST_SERVICE_URI+'/createforumcomment/'+forumid,forumcomment)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the forumcomment');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
	 
	 
 },
 updateForumComment:function(forumid){
	 return $http.put(REST_SERVICE_URI+'/updateforumcomment/'+forumid)
	 .then(function(response){
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while updating the forum comment');
		 return $q.reject(errResponse);
	 }
			 
	 );
 
 },
 deleteForumComment:function(id){
	 return $http.del(REST_SERVICE_URI+'/deleteforumcomment/'+id)
	 .then(function(response){
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while deleting the forumcomment');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 fetchAllForumsComment:function(){
	 console.log('fetchAllforums comment Method in forumServices')
	 return $http.get(REST_SERVICE_URI+'/forumscomment/')
	 .then(
	function(response){
		console.log('success in forumService',response.data)
		$rootScope.fetchComments=response.data;
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the forumsComment');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 getForumComment:function(id){
	 return $http.get(REST_SERVICE_URI+'/getforumcomment/'+id)
	 .then(function(response){
		 
		 $rootScope.selectedForumComment=response.data;
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting forumcomment');
		 return $q.reject(errResponse);
	 }
	 
	 );
 }
 
 
 
 }
 }]);