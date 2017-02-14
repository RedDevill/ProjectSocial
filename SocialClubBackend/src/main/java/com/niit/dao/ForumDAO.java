package com.niit.dao;

import java.util.List;


import com.niit.model.Forum;
import com.niit.model.ForumComment;

public interface ForumDAO {
	
	public boolean insertForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(int forumid);
	public List<Forum> getForumList();
	public Forum getForumById(int forumid);
	
	public boolean insertForumComment(ForumComment forumcomment,String username,int forumid);
	public boolean updateForumComment(ForumComment forumcomment);
	public void deleteForumComment(int forumid);
	public ForumComment getForumCommentById(int forumid);
	public List<ForumComment> getForumCommentList();

}
