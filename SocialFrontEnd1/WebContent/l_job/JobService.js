'use strict'
app.factory('JobService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8081/LetzchaatBackEnd';
 return{
	 
	 fetchAllJobs:function(){
	 console.log('fetchAlljobs Method in jobServices')
	 return $http.get(REST_SERVICE_URI+'/getalljobs/')
	 .then(
	function(response){
		console.log('success in jobService',response.data)
		 $rootScope.AllJobs=response.data;
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the jobs');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 postJob:function(job){
	 console.log('PostJob method in job service')
	 return $http.post(REST_SERVICE_URI+'/postjob/',job)
	 .then(function(response){
		 console.log('Successfully posted the job',response.data)
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while posting the job');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
 },
 getJobDetails:function(jobid){
	 return $http.get(REST_SERVICE_URI+'/getjobdetails/'+jobid)
	 .then(function(response){
		 
		 $rootScope.selectedJob=response.data;
		 console.log('Job details in Job Service',response.data)
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting job');
		 /*return $q.reject(errResponse);*/
	 }
	 
	 );
 },
 
 applyForJob:function(jobid){
	 
	 return $http.post(REST_SERVICE_URI+'/applyjob/'+jobid)
	 .then(function(response){
		 console.log('Successfully applied for the job',response.data)
		 return response.data;
		 
		 
	 },
	 function(errResponse){
		 console.error('Error while applying for  job');
		 return $q.reject(errResponse);
	 }
			 )
 },
 
 getMyAppliedJobs:function(){
	 return $http.get(REST_SERVICE_URI+'/getmyappliedjobs/')
	 .then(function(response){
		 console.log('getMyApplied jobs',response.data)
		  $rootScope.getAppliedJob=response.data;
		 return response.data;
		 
		 
	 },
	 function(errResponse){
		 console.error('Error while getting my applied jobs');
		 return $q.reject(errResponse);
	 }
			 )
	 
 },
 
 rejectJob:function(username,jobid){
	 console.log('rejectJob in JobService')
	 return $http.put(REST_SERVICE_URI+'/rejectjobapplied/'+username+"/"+jobid)
	 .then(function(response){
		 console.log('rejectJob in JobService',response.data)
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while rejecting the job');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 
 callForInterview:function(username,jobid){
	 console.log('callforinterview in JobService')
	 return $http.put(REST_SERVICE_URI+'/callforinterview/'+username+"/"+jobid)
	 .then(function(response){
		 console.log('callforinterview in JobService',response.data)
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while changing the status callforinterview the job');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 
 selectUser:function(username,jobid){
	 console.log('selectUser in JobService')
	 return $http.put(REST_SERVICE_URI+'/selectuser/'+username+"/"+jobid)
	 .then(function(response){
		 console.log('selectUser in JobService',response.data)
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while changing the status selected the job');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 
 
 }
 }]);