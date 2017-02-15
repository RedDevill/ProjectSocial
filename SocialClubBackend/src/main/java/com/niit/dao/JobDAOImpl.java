package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
import com.niit.model.JobApplied;
@Repository("JobDAO")
public class JobDAOImpl implements JobDAO {
	
	private static Logger log=LoggerFactory.getLogger(BlogDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}
	
	private Integer getMaxId()
	{
		Integer maxid=1;
		try {
			Session session=getSession();
			
			String hql="select max(id) from JobApplied";
			Query query=session.createQuery(hql);
			maxid=(Integer)query.uniqueResult();
			
		} catch (Exception e) {
			maxid=1;
			e.printStackTrace();
			return 1;
		}
		return maxid+1;
	}

	public List<Job> getAllOpenedJobs() {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		String hql="from Job where status='V'";
		Query query=session.createQuery(hql);
		List<Job>j=query.list();
		tx.commit();
		session.close();
		
		return j;
	}

	public Job getJobDetails(String jobid) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		Job j=session.get(Job.class, jobid);
		tx.commit();
		session.close();
		return j;
	}

	public boolean updateJob(Job job) {
		log.debug("Starting of the JOBDAO Method UPDATEjob");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(job);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the JOBDAO Method UPDATEjob");
		return false;
	}

	public boolean updateJob(JobApplied jobapplied) {
		log.debug("Starting of the JOBDAO Method UPDATEjobapplied");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(jobapplied);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the JOBDAO Method UPDATEjobapplied");
		return false;
	}

	public boolean insertJob(Job job) {
		log.debug("Starting of the JOBDAO Method INSERTJOB");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			System.out.println("Details of insert job"+job);
			session.save(job);
			tx.commit();
			session.close();
			log.debug("Ending of the JOBDAO Method INSERTJOB");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertJobApplied(JobApplied jobapplied) {
		log.debug("Starting of the JOBDAO Method INSERTJOBAPPLIED");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			//jobapplied.setId(getMaxId());
			session.saveOrUpdate(jobapplied);
			tx.commit();
			session.close();
			log.debug("Ending of the JOBDAO Method INSERTJOBAPPLIED");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplied> getMyAppliedJob(String username) {
		
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		String hql="from Job where jobid in(select jobid from JobApplied where username='"+username+"')";
		Query query=session.createQuery(hql);
		List<JobApplied>j=query.list();
		tx.commit();
		session.close();
		
		return j;
	}

	public JobApplied getJobApplied(String username, String jobid) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		String hql="from JobApplied where username='"+username+"' and jobid='"+jobid+"'";
		Query query=session.createQuery(hql);
		JobApplied jobapplied=(JobApplied)query.uniqueResult();
		tx.commit();
		session.close();
		return jobapplied;
		
	}

	public JobApplied getJobApplied(String jobid) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		JobApplied j=session.get(JobApplied.class, jobid);
		tx.commit();
		session.close();
		return j;
	}

}
