'use strict';
app.controller('BlogController', ['$scope','$location', 'BlogService', function($scope,$location, BlogService){
	var self=this;
	self.blog={
			blogid:'',
			blogtitle:'',
			description:'',
			date_of_creation:'',
			username:'',
			errorCode:'',
			errorMessage:''
		};
	self.blogs=[];
	
	console.log('INSIDE BLOG CONTROLLER')
	
	self.submit=function(){
		
		console.log('Saving new blog',self.blog);
		self.createBlog(self.blog);
	}
	self.createBlog=function(blog){
		
		console.log('create blogs...',self.blog);
		BlogService.createBlog(blog)
		.then(
				function(d){
    				alert('Successfully created the blog')
    				$location.path("/");
    			},
				function(errResponse){
					console.error('Error while creating blog.....');
				}
				
		);
		
	};
	
	console.log('fetchallblogs')
	self.fetchAllBlogs=function(){
		console.log(' Inside FetchAllBlogs method in Blog Controller ')
		BlogService.fetchAllBlogs()
		.then(function(d){
			self.blogs=d;
			
			console.log('value in blogs',self.blogs)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	
	 self.reset=function(){
      	  console.log('resetting the form',self.blog);
      	  self.blog={
      			blogid : '',
      			blogtitle :'',
     			description : '',
     			date_of_creation : '',
     			username : '',
     			status : '',
     			errorCode:'',
     			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
        self.updateBlog=function(blogid){
        	BlogService.updateBlog(blogid)
        	.then(
        		self.fetchAllBlogs,
        		function(errResponse){
        			console.error('Error while updating Blog');
        		}
        	);
        	 };
        	 self.edit=function edit(blogid){
        		 console.log('blogid to be edited',blogid);
        		 for(var i = 0; i < self.blogs.length; i++){
       	            if(self.blogs[i].blogid === blogid) {
       	                self.blog = angular.copy(self.blogs[i]);
       	                break;
       	            }
       	            }
        	 }
        	 self.deleteBlog = function(blogid){
       			BlogService.deleteBlog(blogid)
       			.then(
       					self.fetchAllBlogs,
       					function(errResponse){
       						console.error("Error while deleting Blog");
       					});
       		};
       		console.log('blogs value',self.blogs)
       		
       		//calling the method when it will be exceute
	self.fetchAllBlogs();
       		
       		self.getSelectedBlog = getBlog
       		function getBlog(id){
          		console.log("--.getting blog:"+id)
          		BlogService.getBlog(id)
          		.then(function(d){
          			//self.blog=d;
          			console.log('getSelectedBlog in BlogController',self.blog)
          			$location.path('/view_blog');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching Blogs");
          		}
          	);
          		};
	
}]);