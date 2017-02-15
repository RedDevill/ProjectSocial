package com.niit.dao;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApplied;

public interface JobDAO {
	
	public List<Job> getAllOpenedJobs();
	public Job getJobDetails(String jobid);
	public boolean updateJob(Job job);
	public boolean updateJob(JobApplied jobapplied);
	public boolean insertJob(Job job);
	public boolean insertJobApplied(JobApplied jobapplied);
	public List<JobApplied> getMyAppliedJob(String username);
	public JobApplied getJobApplied(String username,String jobid);
	public JobApplied getJobApplied(String jobid);
	
	

}
