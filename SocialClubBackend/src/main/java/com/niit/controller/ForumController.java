package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.niit.dao.ForumDAOImpl;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

@RestController
public class ForumController {
	private static Logger log=LoggerFactory.getLogger(BlogController.class);
	@Autowired
	ForumDAOImpl forumDAOImpl;
	@Autowired
	Forum forum;
	@Autowired
	ForumComment forumComment;
	
	@RequestMapping(value="/createforum/",method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			forum.setErrorcode("404");
			forum.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Forum>(HttpStatus.OK);
		}
		
		else{
			forum.setCreated_date(new Date());
			forum.setUsername(username);
			forumDAOImpl.insertForum(forum);
			forum.setErrorcode("200");
			forum.setErrormessage("The user has successfully created the Forum");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			
		}
	}
	
	@RequestMapping(value="/updateforum/{forumid}",method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable int forumid){
		
		forumDAOImpl.updateForum(forum);
		forum.setErrorcode("200");
		forum.setErrormessage("The user has successfully updated the forum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);

	}
	
	@RequestMapping(value="/deleteforum/{forumid}",method=RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable int forumid)
	{
		forumDAOImpl.deleteForum(forumid);
		forum.setErrorcode("200");
		forum.setErrormessage("The user has successfully deleted the forum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getforum/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum>getForum(@PathVariable int forumid)
	{
		forum=forumDAOImpl.getForumById(forumid);
		forum.setErrorcode("200");
		forum.setErrormessage("Retrieved the Forum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		
	}
	@RequestMapping(value="/forums/",method=RequestMethod.GET)
	public ResponseEntity<List<Forum>>getAllForum()
	{
		 
		List<Forum>forumlist=forumDAOImpl.getForumList();
		forum.setErrorcode("200");
		forum.setErrormessage("Fetched All the blogs");
		return new ResponseEntity<List<Forum>>(forumlist,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/createforumcomment/{forumid}",method=RequestMethod.POST)
	public ResponseEntity<ForumComment> createForumcomment(@RequestBody ForumComment forumcomment,@PathVariable int forumid,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		
			/*blogComment.setCommented_date(new Date());
			blogComment.setUsername(username);*/
			if(forumDAOImpl.insertForumComment(forumcomment,username,forumid)==true)
			{
				forumcomment.setErrorcode("200");
				forumcomment.setErrormessage("The user has successfully created the forumcomment");
			return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
			
		}
			else{
				log.debug("ENDING OF THE forum CONTROLLER METHOD => CREATEforumCOMMENT");
				log.debug("Could not complete the operation.Please contact Admin");
				forumcomment.setErrorcode("404");
				forumcomment.setErrormessage("Could not complete the operation.Please contact Admin");
				
				return new ResponseEntity<ForumComment>(HttpStatus.OK);
				
			}
		
		
	}
	
	@RequestMapping(value="/updateforumcomment/",method=RequestMethod.PUT)
	public ResponseEntity<ForumComment> updateForumComment(@RequestBody ForumComment forumcomment,HttpSession session,HttpServletRequest request){
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			forumcomment.setErrorcode("404");
			forumcomment.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<ForumComment>(HttpStatus.OK);
		}
		else{
			forumDAOImpl.updateForumComment(forumcomment);
			forumcomment.setErrorcode("200");
			forumcomment.setErrormessage("The user has successfully updated the forumcomment");
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		
		}
		
		
	}
	@RequestMapping(value="/deleteforumcomment/{forumid}",method=RequestMethod.DELETE)
	public ResponseEntity<ForumComment> deleteBlogComment(@PathVariable int forumid,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			forumComment.setErrorcode("404");
			forumComment.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<ForumComment>(HttpStatus.OK);
		}
		else{
			forumDAOImpl.deleteForumComment(forumid);
			forumComment.setErrorcode("200");
			forumComment.setErrormessage("The user has successfully deleted the blogcomment");
		return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/getforumcomment/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<ForumComment> getBlogComment(@PathVariable int forumid)
	{
		forumComment=forumDAOImpl.getForumCommentById(forumid);
		forumComment.setErrorcode("200");
		forumComment.setErrormessage("Retrieved the blogcomment");
		return new ResponseEntity<ForumComment>(forumComment,HttpStatus.OK);
		
	}
	@RequestMapping(value="/forumscomment/",method=RequestMethod.GET)
	public ResponseEntity<List<ForumComment>>getAllForumsComment()
	{
		 
		List<ForumComment>forumlist= forumDAOImpl.getForumCommentList();
		forumComment.setErrorcode("200");
		forumComment.setErrormessage("Fetched All the forumscomment");
		forumlist.add(forumComment);
		return new ResponseEntity<List<ForumComment>>(forumlist,HttpStatus.OK);
	}

	
	

}
