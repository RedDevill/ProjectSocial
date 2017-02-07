'use strict';
app.controller('ForumController', ['$scope','$location', 'ForumService', function($scope,$location, ForumService){
	var self=this;
	self.forum={
			forumid:'',
			forum_topic:'',
			forum_question:'',
			created_date:'',
			username:'',
			errorCode:'',
			errorMessage:''
		};
	self.forums=[];
	
	console.log('INSIDE FORUM CONTROLLER')
	self.submit=function(){
		
		console.log('Saving new forum',self.forum);
		self.createForum(self.forum);
	}
	self.createForum=function(forum){
		
		console.log('create forums...',self.forum);
		ForumService.createForum(forum)
		.then(
				function(d){
    				alert('Successfully created the forum')
    				$location.path("/");
    			},
				function(errResponse){
					console.error('Error while creating forum.....');
				}
				
		);
		
	};
	
	console.log('fetchAllForums')
	self.fetchAllForums=function(){
		console.log(' Inside FetchAllforums method in forum Controller ')
		ForumService.fetchAllForums()
		.then(function(d){
			self.forums=d;
			/*location.path('/list_forum')*/
			console.log('value in forums',self.forums)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	self.fetchAllForums();
	
	 self.reset=function(){
      	  console.log('resetting the form',self.forum);
      	  self.forum={
      			forumid:'',
    			forum_topic:'',
    			forum_question:'',
    			created_date:'',
    			username:'',
    			errorCode:'',
    			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
        self.updateForum=function(forumid){
        	ForumService.updateForum(forumid)
        	.then(
        		self.fetchAllForums,
        		function(errResponse){
        			console.error('Error while updating forum');
        		}
        	);
        	 };
        	 self.edit=function edit(forumid){
        		 console.log('forumid to be edited',forumid);
        		 for(var i = 0; i < self.forums.length; i++){
       	            if(self.forums[i].forumid === forumid) {
       	                self.forum = angular.copy(self.forums[i]);
       	                break;
       	            }
       	            }
        	 }
        	 self.deleteforum = function(forumid){
       			ForumService.deleteForum(forumid)
       			.then(
       					self.fetchAllForums,
       					function(errResponse){
       						console.error("Error while deleting forum");
       					});
       		};
       		console.log('forums value',self.forums)
       		self.getSelectedForum = getForum
       		function getForum(id){
          		console.log("--.getting forum:"+id)
          		ForumService.getForum(id)
          		.then(function(d){
          			self.forums=d;
          			console.log('getSelectedforum in forumController',self.forums)
          			$location.path('/view_forum');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching forums");
          		}
          	);
          		};
	
}]);