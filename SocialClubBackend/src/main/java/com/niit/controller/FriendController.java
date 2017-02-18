package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.niit.dao.FriendDAOImpl;
import com.niit.model.Friend;
import com.niit.model.User;



@RestController
public class FriendController {
	
	private static Logger log=LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	FriendDAOImpl friendDAOImp;
	@Autowired
	Friend friend;
	@Autowired
	HttpSession session;
	@Autowired
	User user;
	
	
	@RequestMapping(value = "/pendingusers", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> pendingFriendRequest() {

		log.debug("->->->->calling method listAllUsers");
		String username=(String)session.getAttribute("username");
		log.debug("Username in FriendController is"+username);
		List<Friend> friends=friendDAOImp.friendList(username);

		// errorCode :200 :404
		// errorMessage :Success :Not found
		log.debug("List of friends in FRiendController"+friends);

		if (friends.isEmpty()||friends==null) {
			friend.setErrorcode("404");
			friend.setErrormessage("No friends are available");
			friends.add(friend);
			
		}
		else
		{
			friend.setErrorcode("200");
			friend.setErrormessage("Successfully retrieved the values");
			friends.add(friend);
		}

		return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/acceptedusers", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> AcceptedFriendRequest() {

		log.debug("->->->->calling method listAllUsers");
		String username=(String)session.getAttribute("username");
		log.debug("Username in FriendController is"+username);
		List<Friend> friends=new ArrayList<Friend>();
		
		if(username==null)
		{
			friend.setErrorcode("404");
			friend.setErrormessage("Please login to know your friends");
			friends.add(friend);
			return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
		}
		
		 friends=friendDAOImp.friendAcceptedList(username);

		// errorCode :200 :404
		// errorMessage :Success :Not found
		log.debug("Accepted List of friends in FRiendController"+friends);

		if (friends.isEmpty()||friends==null) {
			friend.setErrorcode("404");
			friend.setErrormessage("No friends are available");
			friends.add(friend);
			
		}
		else
		{
			friend.setErrorcode("200");
			friend.setErrormessage("Successfully retrieved the values");
			friends.add(friend);
		}

		return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/acceptFriend/{username}", method = RequestMethod.PUT)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("username") String friendname) {
		log.debug("->->->->calling method jobUpdate");
		String username=(String)session.getAttribute("username");
		if(username==null || username.isEmpty())
		{
			friend.setErrorcode("404");
			friend.setErrormessage("Please login");
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
			
		}
		
		friend=friendDAOImp.getFriendRequest(username, friendname);
		System.out.println("Friend data"+friend);
		friend.setFriend_request('A');
		System.out.println("Friend data 1"+friend);
		if(friendDAOImp.updateFriend(friend))
		{
			log.debug("Accept friend requets"+friend);
			friend.setErrorcode("200");
			friend.setErrormessage("Successfully udated the status as");
		}
		else
		{
			friend.setErrorcode("404");
			friend.setErrormessage("Not able to change the friend request status");
			log.debug("Not able to change the application status");
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rejectFriend/{username}", method = RequestMethod.PUT)
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("username") String friendname) {
		log.debug("->->->->calling method jobUpdate");
		String username=(String)session.getAttribute("username");
		if(username==null || username.isEmpty())
		{
			friend.setErrorcode("404");
			friend.setErrormessage("Please login");
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
			
		}
		friend=friendDAOImp.getFriendRequest(username, friendname);
		friend.setFriend_request('R');
		if(friendDAOImp.updateFriend(friend))
		{
			log.debug("Friend request status"+friend);
			friend.setErrorcode("200");
			friend.setErrormessage("Successfully updated friend request the status as");
		}
		else
		{
			friend.setErrorcode("404");
			friend.setErrormessage("Not able to change the friend request status");
			log.debug("Not able to change the application status");
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/friendProfile/{friend_name}", method = RequestMethod.GET)
	public ResponseEntity<User> friendProfile(@PathVariable("friend_name") String friendname) {
		log.debug("->->calling method myProfile");
		
		user = friendDAOImp.getFriendDetails(friendname);
		if (user == null) {
			log.debug("->->->-> User does not exist wiht id" + user);
			
			user.setErrorcode("404");
			user.setErrormessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("->->->-> User exist with username" + friendname);
		log.debug(user.getUsername());
		friend.setErrorcode("200");
		friend.setErrormessage("Successfully retrieved the friend values");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

}
