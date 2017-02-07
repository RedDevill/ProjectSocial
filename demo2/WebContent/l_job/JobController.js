'use strict';
app.controller('JobController', ['$scope','$location','$rootScope', 'JobService', function($scope,$location,$rootScope, JobService){
	var self=this;
	self.job={
			jobid:'',
			job_title:'',
			qualification:'',
			status:'',
			description:'',
			date_time:'',
			errorCode:'',
			errorMessage:''
		};
	self.jobs=[];
	
	console.log('INSIDE job CONTROLLER')
	
	self.submit=function(){
		
		console.log('Saving new job',self.job);
		self.postJob(self.job);
	}
	/*self.reset();*/
	self.postJob=function(job){
		
		console.log('post jobs...',self.job);
		JobService.postJob(job)
		.then(
				function(d){
    				alert('Successfully posted the job')
    				/*$location.path("/");*/
    			},
				function(errResponse){
					console.error('Error while posting job.....');
				}
				
		);
		
	};
	
	self.fetch1=function(){
		self.fetchAllJobs();
	}
	
	console.log('fetchalljobs')
	self.fetchAllJobs=function(){
		console.log(' Inside FetchAlljobs method in job Controller ')
		JobService.fetchAllJobs()
		.then(function(d){
			self.jobs=d;
			
			console.log('value in jobs',self.jobs)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	
	self.getJobDetails=function(jobid){
		console.log(' Inside getJobDetails method in job Controller ',jobid)
		JobService.getJobDetails(jobid)
		.then(function(d){
			self.jobs=d;
			location.path('/view_jobdetails')
			console.log('value in jobs',self.jobs)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	self.applyForJob=function(jobid){
		console.log(' Inside applyForJob method in job Controller ',jobid)
		if(!$rootScope.loggedIn)
			{
			alert('Please login to apply for a job');
			console.log(' User not loggedin,cannot apply for a job')
			$location.path('/login')
			return
			
			}
		console.log($rootScope.loggedIn,'applying for a job',jobid);
		JobService.applyForJob(jobid)
		.then(function(d){
			self.job=d;
			
			console.log('value in jobs',self.job)
			console.log('You have successfully applied for job')
			alert('You have successfully applied for job')
			
		},
		
		function(errResponse){
			console.error('Error while applying for the job');
		}
		
		);
	};
	
	self.appliedJobs=function(){
		self.getMyAppliedJobs();
	}
	
	
	self.getMyAppliedJobs=function(){
		console.log('getMyAppliedjobs in Job Controller');
		JobService.getMyAppliedJobs()
		.then(function(d){
			self.jobs=d;
			
			console.log('value in getMYApplied jobs',self.jobs)
			
			
			
		},
		
		function(errResponse){
			console.error('Error while applying for the job');
		}
		
		);
		
	};
	
	self.rejectJob=function(username){
		var jobid=$rootScope.selectedJob.jobid;
		JobService.rejectJob(username,jobid)
		.then(function(d){
			
			self.jobs=d;
			alert('You have successfully rejected the job application');
			console.log('You have successfully rejected the job application',self.jobs)
		},
		function(errResponse)
		{
			console.error('Error while rejecting the job application')
		}
				
		)
		
	};
	
	self.callForInterview=function(username){
		var jobid=$rootScope.selectedJob.jobid;
		JobService.callForInterview(username,jobid)
		.then(function(d){
			
			self.jobs=d;
			alert('Application status changed as call for interview');
			console.log('Application status changed as call for interview',self.jobs)
		},
		function(errResponse)
		{
			console.error('Error while changing the status as call for interview in job application')
		}
				
		)
		
	};
	
	self.selectUser=function(username){
		var jobid=$rootScope.selectedJob.jobid;
		JobService.selectUser(username,jobid)
		.then(function(d){
			
			self.jobs=d;
			alert('Application status changed as Selected');
			console.log('Application status changed as Selected',self.jobs)
		},
		function(errResponse)
		{
			console.error('Error while changing the status as Selected in job application')
		}
				
		)
		
	};
	
	
	
	 self.reset=function(){
      	  console.log('resetting the form',self.job);
      	  self.job={
      			jobid:'',
    			job_title:'',
    			qualification:'',
    			status:'',
    			description:'',
    			date_time:'',
    			errorCode:'',
    			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
       
       		//calling the method when it will be exceute
	/*self.fetchAllJobs();*/
       		
       		self.getSelectedJob = getJob
       		function getJob(id){
          		console.log("--.getting job:"+id)
          		JobService.getJob(id)
          		.then(function(d){
          			//self.job=d;
          			console.log('getSelectedjob in jobController',self.job)
          			$location.path('/view_job');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching jobs");
          		}
          	);
          		};
          		
          		
          		
          		
          		 /*self.updatejob=function(jobid){
            	JobService.updateJob(jobid)
            	.then(
            		self.fetchAllJobs,
            		function(errResponse){
            			console.error('Error while updating job');
            		}
            	);
            	 };
            	 self.edit=function edit(jobid){
            		 console.log('jobid to be edited',jobid);
            		 for(var i = 0; i < self.jobs.length; i++){
           	            if(self.jobs[i].jobid === jobid) {
           	                self.job = angular.copy(self.jobs[i]);
           	                break;
           	            }
           	            }
            	 }
            	 self.deletejob = function(jobid){
           			JobService.deleteJob(jobid)
           			.then(
           					self.fetchAllJobs,
           					function(errResponse){
           						console.error("Error while deleting job");
           					});
           		};
           		console.log('jobs value',self.jobs)
           		*/
	
}]);