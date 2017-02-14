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


import com.niit.dao.BlogDAOImpl;
import com.niit.model.Blog;
import com.niit.model.BlogComment;

@RestController
public class BlogController {
	private static Logger log=LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	BlogDAOImpl blogDAOImpl;
	@Autowired
	Blog blog;
	@Autowired
	BlogComment blogComment;
	
	@RequestMapping(value="/createblog/",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		/*if(username == null)
		{
			log.debug("Useris not Loggedin");
			blog.setErrorcode("404");
			blog.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
		
		else{*/
			blog.setDate_of_creation(new Date());
			blog.setUsername(username);
			if(blogDAOImpl.insertBlog(blog)==true)
			{
			blog.setErrorcode("200");
			blog.setErrormessage("The user has successfully created the blog");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
			else{
				log.debug("ENDING OF THE BLOG CONTROLLER METHOD => CREATEBLOG");
				log.debug("Could not complete the operation.Please contact Admin");
				blog.setErrorcode("404");
				blog.setErrormessage("Could not complete the operation.Please contact Admin");
				
				return new ResponseEntity<Blog>(HttpStatus.OK);
				
			}
		
		
	}
	@RequestMapping(value="/updateblog/{blogid}",method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable int blogid,HttpSession session,HttpServletRequest request){
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			blog.setErrorcode("404");
			blog.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
		else{
		blogDAOImpl.updateBlogById(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("The user has successfully updated the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
		}
		
		
	}
	
	@RequestMapping(value="/deleteblog/{blogid}",method=RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable int blogid,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			blog.setErrorcode("404");
			blog.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
		else{
		blogDAOImpl.deleteBlog(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("The user has successfully deleted the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getblog/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable int blogid)
	{
		blog=blogDAOImpl.getBlogById(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("Retrieved the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	@RequestMapping(value="/blogs/",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>>getAllBlogs()
	{
		 
		List<Blog>bloglist= blogDAOImpl.getBlogList();
		blog.setErrorcode("200");
		blog.setErrormessage("Fetched All the blogs");
		return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/createblogcomment/{blogid}",method=RequestMethod.POST)
	public ResponseEntity<BlogComment> createBlogcomment(@RequestBody BlogComment blogcomment,@PathVariable int blogid,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		
			/*blogComment.setCommented_date(new Date());
			blogComment.setUsername(username);*/
			if(blogDAOImpl.insertBlogComment(blogcomment,username,blogid)==true)
			{
				blogComment.setErrorcode("200");
				blogComment.setErrormessage("The user has successfully created the blogcomment");
			return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);
			
		}
			else{
				log.debug("ENDING OF THE BLOG CONTROLLER METHOD => CREATEBLOGCOMMENT");
				log.debug("Could not complete the operation.Please contact Admin");
				blogComment.setErrorcode("404");
				blogComment.setErrormessage("Could not complete the operation.Please contact Admin");
				
				return new ResponseEntity<BlogComment>(HttpStatus.OK);
				
			}
		
		
	}
	
	@RequestMapping(value="/updateblogcomment/",method=RequestMethod.PUT)
	public ResponseEntity<BlogComment> updateBlogComment(@RequestBody BlogComment blogcomment,HttpSession session,HttpServletRequest request){
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			blogComment.setErrorcode("404");
			blogComment.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<BlogComment>(HttpStatus.OK);
		}
		else{
		blogDAOImpl.updateBlogComment(blogcomment);
		blogComment.setErrorcode("200");
		blogComment.setErrormessage("The user has successfully updated the blogcomment");
		return new ResponseEntity<BlogComment>(blogcomment,HttpStatus.OK);
		
		}
		
		
	}
	@RequestMapping(value="/deleteblogcomment/{blogid}",method=RequestMethod.DELETE)
	public ResponseEntity<BlogComment> deleteBlogComment(@PathVariable int blogid,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			blogComment.setErrorcode("404");
			blogComment.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<BlogComment>(HttpStatus.OK);
		}
		else{
		blogDAOImpl.deleteBlogComment(blogid);
		blogComment.setErrorcode("200");
		blogComment.setErrormessage("The user has successfully deleted the blogcomment");
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		}
	}
	@RequestMapping(value="/getblogcomment/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<BlogComment> getBlogComment(@PathVariable int blogid)
	{
		blogComment=blogDAOImpl.getBlogCommentById(blogid);
		blogComment.setErrorcode("200");
		blogComment.setErrormessage("Retrieved the blogcomment");
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	@RequestMapping(value="/blogscomment/",method=RequestMethod.GET)
	public ResponseEntity<List<BlogComment>>getAllBlogsComment()
	{
		 
		List<BlogComment>bloglist= blogDAOImpl.getBlogCommentList();
		blogComment.setErrorcode("200");
		blogComment.setErrormessage("Fetched All the blogscomment");
		return new ResponseEntity<List<BlogComment>>(bloglist,HttpStatus.OK);
	}
	

}
