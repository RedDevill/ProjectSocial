package com.niit.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDAOImpl;
import com.niit.model.Job;
import com.niit.model.JobApplied;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import sun.invoke.empty.Empty;



@RestController
public class JobController {
	
	private static Logger log=LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobDAOImpl jobDAOImpl;
	@Autowired
	Job job;
	@Autowired
	JobApplied jobapplied;
	@Autowired
	HttpSession session;
	
	//@CrossOrigin(origins="http://localhost:8088")==>if it is a diff port or ip address then we have to give the @CrossOrigin
	//if you want to apply for the methods then we have to use above the class.
	//@CrossOrigin(origins="http://snapdeal.com") ==>here if we are writing this,this method can only access for snapdeal only
	@RequestMapping(value="/getalljobs/",method=RequestMethod.GET)
	public ResponseEntity<List<Job>> getAllJobs()
	{
		log.debug("->->->->calling method listAllJobs");
		List<Job> job=jobDAOImpl.getAllOpenedJobs();
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
		
	}
	

	@RequestMapping(value="/getmyappliedjobs/",method=RequestMethod.GET)
	public ResponseEntity<List<JobApplied>> getMyAppliedJobs(HttpSession session)
	{
		String username = (String) session.getAttribute("username");
		
		log.debug("->->->->calling method getMyAppliedJobs");
		List<JobApplied>jobapplied=jobDAOImpl.getMyAppliedJob(username);
		return new ResponseEntity<List<JobApplied>>(jobapplied, HttpStatus.OK);
		
	}

	@RequestMapping(value="/getjobdetails/{jobid}",method=RequestMethod.GET)
	public ResponseEntity<Job> getJobDetails(@PathVariable("jobid") String jobid)
	{
		//Date format should be based locale and language
				//en-us Internationalization => i18n
		/*Date datetime=job.getDate_time();
		SimpleDateFormat format=new SimpleDateFormat("DD/MM/YY");
		String convertedDate=format.format(datetime);*/
		
		log.debug("->->->->calling method getJobDetails");
		
		Job job=jobDAOImpl.getJobDetails(jobid);
		return new ResponseEntity<Job>(job, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/selectuser/{username}/{jobid}/{remark}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplied> selectuser(@PathVariable("username") String username,@PathVariable("jobid") String jobid,@PathVariable("remark") String remark) {
		log.debug("->->->->calling method jobUpdate");
		jobapplied=updateJobAppliedStatus(username, jobid, 'S', remark);
		return new ResponseEntity<JobApplied>(jobapplied,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/callforinterview/{username}/{jobid}/{remark}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplied> callForInterview(@PathVariable("username") String username,@PathVariable("jobid") String jobid,@PathVariable("remark") String remark) {
		log.debug("->->->->calling method jobUpdate");
		jobapplied=updateJobAppliedStatus(username, jobid, 'C', remark);
		return new ResponseEntity<JobApplied>(jobapplied,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rejectjobapplied/{username}/{jobid}/{remark}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplied> rejectJobApplied(@PathVariable("username") String username,@PathVariable("jobid") String jobid,@PathVariable("remark") String remark) {
		log.debug("->->->->calling method jobUpdate");
		jobapplied=updateJobAppliedStatus(username, jobid, 'R', remark);
		return new ResponseEntity<JobApplied>(jobapplied,HttpStatus.OK);
	}
	
	
	private JobApplied updateJobAppliedStatus(String username, String jobid, char status,String reason) {
		log.debug("Starting of the method updateStatus");
		
		
		if(isUserAppliedForTheJob(username,jobid))
		{
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage(username+" not applied for this job"+jobid);
			return jobapplied;
		}
		
		String role=(String)session.getAttribute("role");
		if(role==null ||role.isEmpty()){
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage("You are not logged in");
			return jobapplied;
		}
		if(!role.equalsIgnoreCase("admin"))
		{
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage("You are not admin.You cannot do this operation");
			log.debug("You are not admin.You cannot do this operation");
			return jobapplied;
		}

		jobapplied=jobDAOImpl.getJobApplied(username, jobid);
		/*if(jobapplied==null)
		{
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage(username+"has not applied for this job"+jobid);
		}
		else{*/
		jobapplied.setStatus(status);
		jobapplied.setRemarks(reason);
		if(jobDAOImpl.updateJob(jobapplied))
		{
			jobapplied.setErrorcode("200");
			jobapplied.setErrormessage("Successfully udated the status as"+status);
			log.debug("Successfully udated the status as"+status);
			
		}
		else
		{
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage("Not able to change the application status"+status);
			log.debug("Not able to change the application status"+status);
		}
		/*}*/
		return jobapplied;

	}
	
	@RequestMapping(value="/postjob/",method=RequestMethod.POST)
	public ResponseEntity<Job> postJob(@RequestBody Job job)
	{
		job.setStatus('V');
		job.setDate_time(new Date());
		if(jobDAOImpl.insertJob(job)==false)
		{
			job.setErrorcode("404");
			job.setErrormessage("Not able to post a job");
			
		}
		else
		{
			job.setErrorcode("200");
			job.setErrormessage("Successfully posted the job");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	

	@RequestMapping(value="/applyjob/{jobid}",method=RequestMethod.POST)
	public ResponseEntity<JobApplied> applyJob(@PathVariable("jobid") String jobid,HttpSession session)
	
	{
		String username = (String) session.getAttribute("username");
		
		if(username==null)
		{
			jobapplied.setErrorcode("404");
			jobapplied.setErrormessage("Please login in to apply for a job");
			
		}
		else
		{
			
			if(jobDAOImpl.getJobApplied(username, jobid)==null)
			{
				jobapplied.setJobid(jobid);
				jobapplied.setUsername(username);
				jobapplied.setStatus('N'); //N=>Newly Applied,C=>Call for interview ,S=>selected
				jobapplied.setDate_applied(new Date(System.currentTimeMillis()));
				log.debug("Applied date:"+jobapplied.getDate_applied());
				
			if(jobDAOImpl.insertJobApplied(jobapplied))
			{
			jobapplied.setErrorcode("200");
			jobapplied.setErrormessage("Successfully applied for  the job");
			}
			}
			else{
				//If the user already applied for the job{
				jobapplied.setErrorcode("404");
				jobapplied.setErrormessage("You already applied for  the job"+jobid);
				log.debug("Not able to apply for the job");
			}
			
		}
		return new ResponseEntity<JobApplied>(jobapplied,HttpStatus.OK);
	}
	
	private boolean isUserAppliedForTheJob(String username,String jobid){
		
		if(jobDAOImpl.getJobApplied(username, jobid)==null)
		{
			return true;
		}
		else
			return false;
		
	}

}
