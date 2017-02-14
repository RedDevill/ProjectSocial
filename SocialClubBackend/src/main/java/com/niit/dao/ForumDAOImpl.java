package com.niit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	private static Logger log=LoggerFactory.getLogger(BlogDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}

	private Integer getMaxForumId()
	{
		Integer maxid=100;
		try {
			Session session=getSession();
			
			String hql="select max(forumid) from Forum";
			Query query=session.createQuery(hql);
			maxid=(Integer)query.uniqueResult();
			
		} catch (Exception e) {
			maxid=1;
			e.printStackTrace();
			return 100;
		}
		return maxid+1;
	}

	public boolean insertForum(Forum forum) {
		log.debug("Starting of the FORUMDAO Method INSERTFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			/*forum.setForumid(getMaxForumId());
			*/session.save(forum);
			tx.commit();
			session.close();
			log.debug("Ending of the FORUMDAO Method INSERTFORUM");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		log.debug("Starting of the FORUMDAO Method UPDATEFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(forum);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the FORUMDAO Method UPDATEFORUM");
		return false;
	}

	public boolean deleteForum(int forumid) {
		log.debug("Starting of the FORUMDAO Method DELETEFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Forum id=session.get(Forum.class, forumid);
			session.delete(id);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the FORUMDAO Method DELETEFORUM");
		return false;
	}

	public List<Forum> getForumList() {
		log.debug("Starting of the FORUMDAO Method GETFORUMLIST");
		Session session = getSession();
		// Transaction tx = session.beginTransaction();
		String s = "from Forum";
		Query query = session.createQuery(s);
		@SuppressWarnings("unchecked")
		List<Forum> b = query.list();
		// tx.commit();
		session.close();
		log.debug("Ending of the FORUMDAO Method GETFORUMLIST");
		return b;
	}

	public Forum getForumById(int forumid) {
		log.debug("Starting of the FORUMDAO method GETFORUMBYID");
		Session session=getSession();
		//Transaction tx=session.beginTransaction();
		Forum b=session.get(Forum.class, forumid);
		session.close();
		log.debug("Ending of the FORUMDAO method GETFORUMBYID");
		return b;
	}
	
	private Integer getMaxId()
	{
		Integer maxid;
		try {
			Session session=getSession();
			
			String hql="select max(commentid) from BlogComment";
			Query query=session.createQuery(hql);
			maxid=(Integer)query.uniqueResult();
			
		} catch (Exception e) {
			maxid=1;
			e.printStackTrace();
			return 1;
		}
		return maxid+1;
	}

	public boolean insertForumComment(ForumComment forumcomment, String username, int forumid) {
		log.debug("Starting of the FORUMDAO Method INSERTFORUMCOMMENT");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			forumcomment.setId(getMaxId());
			forumcomment.setForumid(forumid);
			forumcomment.setUsername(username);
			forumcomment.setCommented_date(new Date());
			session.save(forumcomment);
			tx.commit();
			session.close();
			log.debug("Ending of the FORUMDAO Method INSERTFORUMCOMMENT");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForumComment(ForumComment forumcomment) {
		log.debug("Starting of the FORUMDAO Method UPDATEFORUMCOMMENT");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(forumcomment);
			tx.commit();
			session.close();
			log.debug("Ending of the FORUMDAO Method UPDATEFORUMCOMMENT");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteForumComment(int forumid) {
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		ForumComment fc=session.get(ForumComment.class, forumid);
		session.delete(fc);
		tx.commit();
		session.close();
		
	}

	public ForumComment getForumCommentById(int forumid) {
		log.debug("Starting of the FORUMDAO method GETFORUMCOMMENTBYID");
		Session session=getSession();
		//Transaction tx=session.beginTransaction();
		ForumComment b=session.get(ForumComment.class, forumid);
		session.close();
		log.debug("Ending of the FORUMDAO method GETFORUMCOMMENTBYID");
		return b;
	}

	public List<ForumComment> getForumCommentList() {
		log.debug("Starting of the FORUMDAO Method GETFORUMCOMMENTLIST");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String s = "from ForumComment";
		Query query = session.createQuery(s);
		List<ForumComment> f = query.list();
		tx.commit();
		session.close();
		log.debug("Ending of the FORUMDAO Method GETFORUMCOMMENTLIST");
		return f;
	}

}
