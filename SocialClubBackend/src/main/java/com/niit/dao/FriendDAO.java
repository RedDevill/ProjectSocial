package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;


public interface FriendDAO {
	
	public List<Friend> friendList(String username);
	public boolean updateFriend(Friend friend);
	public Friend getFriendRequest(String username, String friend_name);
	public User getFriendDetails(String friendname);
	public List<Friend> friendAcceptedList(String username);
	

}
