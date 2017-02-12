'use strict'
app.factory('BlogService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8080/DemoSocialBack';
 return{
	 
	 fetchAllBlogs:function(){
	 console.log('fetchAllBlogs Method in BlogServices')
	 return $http.get(REST_SERVICE_URI+'/blogs/')
	 .then(
	function(response){
		console.log('success in BlogService',response.data)
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the Blogs');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 createBlog:function(blog){
	 console.log('Blog service')
	 return $http.post(REST_SERVICE_URI+'/createblog/',blog)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the blog');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
 },
 getBlog:function(id){
	 return $http.get(REST_SERVICE_URI+'/getblog/'+id)
	 .then(function(response){
		 
		 $rootScope.selectedBlog=response.data;
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting blog');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 deleteBlog:function(id){
	 return $http.del(REST_SERVICE_URI+'/deleteblog/'+id)
	 .then(function(response){
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while deleting the blog');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 updateBlog:function(blogid){
	 return $http.put(REST_SERVICE_URI+'/updateblog/'+blogid)
	 .then(function(response){
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while updating the blog');
		 return $q.reject(errResponse);
	 }
			 
	 );
 },
 
 createBlogComment:function(blogcomment,blogid){
	 console.log('Blog Comment service')
	 return $http.post(REST_SERVICE_URI+'/createblogcomment/'+blogid,blogcomment)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the blogcomment');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
	 
	 
 },
 updateBlogComment:function(blogid){
	 return $http.put(REST_SERVICE_URI+'/updateblogcomment/'+blogid)
	 .then(function(response){
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while updating the blog comment');
		 return $q.reject(errResponse);
	 }
			 
	 );
 
 },
 deleteBlogComment:function(id){
	 return $http.del(REST_SERVICE_URI+'/deleteblogcomment/'+id)
	 .then(function(response){
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while deleting the blogcomment');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 fetchAllBlogsComment:function(){
	 console.log('fetchAllBlogs comment Method in BlogServices')
	 return $http.get(REST_SERVICE_URI+'/blogscomment/')
	 .then(
	function(response){
		console.log('success in BlogService',response.data)
		$rootScope.fetchComments=response.data;
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the BlogsComment');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 getBlogComment:function(id){
	 return $http.get(REST_SERVICE_URI+'/getblogcomment/'+id)
	 .then(function(response){
		 
		 $rootScope.selectedBlogComment=response.data;
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting blogcomment');
		 return $q.reject(errResponse);
	 }
	 
	 );
 }
 
 }
 }]);