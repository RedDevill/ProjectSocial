package com.niit.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="l_blog")
public class Blog extends Error {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogid;
	private String blogtitle;
	private String description;
	private Date date_of_creation;
	private String username;
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogtitle() {
		return blogtitle;
	}
	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_of_creation() {
		return date_of_creation;
	}
	public void setDate_of_creation(Date date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Blog [blogid=" + blogid + ", blogtitle=" + blogtitle + ", description=" + description
				+ ", date_of_creation=" + date_of_creation + ", username=" + username + "]";
	}
	
	
	

}
