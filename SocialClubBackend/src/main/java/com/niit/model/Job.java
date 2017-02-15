package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="l_job")
public class Job extends Error {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobid;
	private String job_title;
	private String qualification;
	private char status;
	private String description;
	private Date date_time;
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", job_title=" + job_title + ", qualification=" + qualification + ", status="
				+ status + ", description=" + description + ", date_time=" + date_time + "]";
	}
	
	

}
