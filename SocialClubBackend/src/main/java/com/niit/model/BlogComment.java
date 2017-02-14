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
@Table(name="l_blog_comment")
public class BlogComment extends Error {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commentid;
	private int blogid;
	private String comments;
	private String username;
	private Date commented_date;
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCommented_date() {
		return commented_date;
	}
	public void setCommented_date(Date commented_date) {
		this.commented_date = commented_date;
	}
	@Override
	public String toString() {
		return "BlogComment [commentid=" + commentid + ", blogid=" + blogid + ", comments=" + comments + ", username="
				+ username + ", commented_date=" + commented_date + "]";
	}
	
	
	
}
