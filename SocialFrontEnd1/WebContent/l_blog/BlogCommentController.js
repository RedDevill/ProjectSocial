'use strict';
app.controller('BlogCommentController', ['$scope','$location', '$rootScope','BlogService', function($scope,$location,$rootScope, BlogService){
	var self=this;
	self.blogcomment={
			commentid:'',
			blogid:'',
			comments:'',
			username:'',
			commented_date:'',
			errorCode:'',
			errorMessage:''
		};
	
	self.blogcomments=[];
	
console.log('INSIDE BLOG COMMENT CONTROLLER')
	
	self.submit=function(blogid){
	
	self.blogcomment.blogid=blogid;
	console.log('blogid vlaue:',self.blogcomment.blogid)
		
		console.log('Saving new blog comment',self.blogcomment);
		self.createBlogComment(self.blogcomment,blogid);
	}
	self.createBlogComment=function(blogcomment,blogid){
		
		console.log(' Inside creatBlogComment method in BlogComment Controller ')
		if(!$rootScope.loggedIn)
			{
			alert('Please login to post comments');
			console.log(' User not loggedin,cannot post comments')
			$location.path('/login')
			return
			
			}	
		console.log('create blogs comment...',self.blogcomment);
		BlogService.createBlogComment(blogcomment,blogid)
		.then(
				function(d){
    				alert('Successfully created the blog comment')
    				$location.path("/");
    			},
				function(errResponse){
					console.error('Error while creating blog comment.....');
				}
				
		);
		
	};
	
	console.log('fetchallblogscomment')
	self.fetchAllBlogsComment=function(){
		console.log(' Inside FetchAllBlogs Comment method in BlogComment Controller ')
		BlogService.fetchAllBlogsComment()
		.then(function(d){
			self.blogcomments=d;
			
			console.log('value in blogcomments',self.blogcomments)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	
	 self.reset=function(){
      	  console.log('resetting the form',self.blogcomment);
      	  self.blogcomment={
      			commentid:'',
    			blogid:'',
    			comments:'',
    			username:'',
    			commented_date:'',
    			errorCode:'',
    			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
        self.updateBlogComment=function(blogid){
        	BlogService.updateBlogComment(blogid)
        	.then(
        		self.fetchAllBlogsComment,
        		function(errResponse){
        			console.error('Error while updating Blog comment');
        		}
        	);
        	 };
        	 self.edit=function edit(blogid){
        		 console.log('blogid to be edited',blogid);
        		 for(var i = 0; i < self.blogcomments.length; i++){
       	            if(self.blogcomments[i].blogid === blogid) {
       	                self.blogcomment = angular.copy(self.blogcomments[i]);
       	                break;
       	            }
       	            }
        	 }
        	 self.deleteBlogComment = function(blogid){
       			BlogService.deleteBlogComment(blogid)
       			.then(
       					self.fetchAllBlogsComment,
       					function(errResponse){
       						console.error("Error while deleting Blog Comment");
       					});
       		};
       		console.log('blogs Comment value',self.blogcomments)
       		
       		//calling the method when it will be exceute
	self.fetchAllBlogsComment();
       		
       		self.getSelectedBlogComment = getBlogComment
       		function getBlogComment(id){
          		console.log("--.getting blog comment:"+id)
          		BlogService.getBlogComment(id)
          		.then(function(d){
          			//self.blog=d;
          			console.log('getSelectedBlog Comment in BlogCommentController',self.blogcomment)
          			$location.path('/view_blog');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching BlogsComment");
          		}
          	);
          		};
	
}]);