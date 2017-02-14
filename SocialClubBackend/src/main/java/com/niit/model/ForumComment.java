package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="l_forum_comment")
public class ForumComment extends Error {
	
	@Id
	private int id;
	private int forumid;
	private String username;
	private String forum_comment;
	private Date commented_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getForum_comment() {
		return forum_comment;
	}
	public void setForum_comment(String forum_comment) {
		this.forum_comment = forum_comment;
	}
	public Date getCommented_date() {
		return commented_date;
	}
	public void setCommented_date(Date commented_date) {
		this.commented_date = commented_date;
	}
	@Override
	public String toString() {
		return "ForumComment [id=" + id + ", forumid=" + forumid + ", username=" + username + ", forum_comment="
				+ forum_comment + ", commented_date=" + commented_date + "]";
	}
	
	

}
