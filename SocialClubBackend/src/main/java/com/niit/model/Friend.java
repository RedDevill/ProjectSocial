package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="l_friend")
public class Friend extends Error {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String friend_name;
	private char friend_request;
	private char is_online;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public char getFriend_request() {
		return friend_request;
	}
	public void setFriend_request(char friend_request) {
		this.friend_request = friend_request;
	}
	public char getIs_online() {
		return is_online;
	}
	public void setIs_online(char is_online) {
		this.is_online = is_online;
	}
	@Override
	public String toString() {
		return "Friend [id=" + id + ", username=" + username + ", friend_name=" + friend_name + ", friend_request="
				+ friend_request + ", is_online=" + is_online + "]";
	}
	
	

}
