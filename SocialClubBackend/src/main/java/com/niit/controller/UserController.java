package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.niit.dao.FriendDAO;
import com.niit.dao.UserDAO;
//import com.niit.collaboration.model.Friend;
import com.niit.model.User;

@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	User user;

	/*@Autowired
	FriendDAO friendDAO;*/

	//Admin should able to make one of the employee as admin
	@RequestMapping(value = "/makeAdmin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("id") String empID) {

		logger.debug("calling the method makeAdmin");
		user = userDAO.get(empID);

		if (user == null) {
			logger.debug("Employee does not exist with the id : " + empID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK); // 200

		}
		
		if(user.getRole()!="Employee")
		{
			logger.debug("We cannot make thhis user as admin: " + empID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("We cannot make thhis user as admin: " + empID);
			return new ResponseEntity<User>(user, HttpStatus.OK); // 200
			
		}
		
		
		
		
		
		
		user.setRole("Admin");
		userDAO.update(user);
		user.setErrorCode("200");
		user.setErrorMessage("Successfully assign Admin role to the employy :" +user.getName());
		logger.debug("Employee role updated as admin successfully " + empID);

		return new ResponseEntity<User>(user, HttpStatus.OK); // 200

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		logger.debug("->->->->calling method listAllUsers");
		List<User> users = userDAO.list();

		// errorCode :200 :404
		// errorMessage :Success :Not found

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No users are available");
			users.add(user);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// http://localhost:8080/Collaboration/user/
	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.debug("->->->->calling method createUser");
		if (userDAO.get(user.getUsername()) == null) {
			logger.debug("->->->->User is going to create with id:" + user.getUsername());
			user.setIsOnline('N');
			user.setStatus('N');
			  if (userDAO.save(user) ==true)
			  {
				  user.setErrorCode("200");
					user.setErrorMessage("Thank you  for registration. You have successfully registered as " + user.getRole());
			  }
			  else
			  {
				  user.setErrorCode("404");
					user.setErrorMessage("Could not complete the operatin please contact Admin");
		
				  
			  }
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		logger.debug("->->->->User already exist with id " + user.getUsername());
		user.setErrorCode("404");
		user.setErrorMessage("User already exist with id : " + user.getUsername());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// http://localhost:8080/Collaboration/user/
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		logger.debug("->->->->calling method updateUser");
		if (userDAO.get(user.getUsername()) == null) {
			logger.debug("->->->->User does not exist with id " + user.getUsername());
			user = new User(); // ?
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with id " + user.getUsername());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		userDAO.update(user);
		logger.debug("->->->->User updated successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	// http://localhost:8081/CollaborationBackEnd/user/abbas
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		logger.debug("->->calling method getUser");
		logger.debug("->->id->->" + id);
		User user = userDAO.get(id);
		if (user == null) {
			logger.debug("->->->-> User does not exist wiht id" + id);
			user = new User(); //To avoid NLP - NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		logger.debug("->->->-> User exist wiht id" + id);
		logger.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("id") String id) {
		logger.debug("Starting of the method accept");

		user = updateStatus(id, 'A', "");
		logger.debug("Ending of the method accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/reject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("id") String id, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method reject");

		user = updateStatus(id, 'R', reason);
		logger.debug("Ending of the method reject");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	private User updateStatus(String id, char status, String reason) {
		logger.debug("Starting of the method updateStatus");

		logger.debug("status: " + status);
		user = userDAO.get(id);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status to " + status);
		} else {

			user.setStatus(status);
			user.setReason(reason);
			
			userDAO.update(user);
			
			user.setErrorCode("200");
			user.setErrorMessage("Updated the status successfully");
		}
		logger.debug("Ending of the method updateStatus");
		return user;

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session) {
		logger.debug("->->calling method myProfile");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		User user = userDAO.get(loggedInUserID);
		if (user == null) {
			logger.debug("->->->-> User does not exist wiht id" + loggedInUserID);
			user = new User(); // It does not mean that we are inserting new row
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		logger.debug("->->->-> User exist with id" + loggedInUserID);
		logger.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// http://localhost:8081/CollaborationBackEnd/user/authenticate/
	/*
	 * { "id": "alm1", "password": "alm1" }
	 */
	@RequestMapping(value = "/user/authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
		logger.debug("->->->->calling method authenticate");
		user = userDAO.authenticate(user.getUsername(), user.getPassword());
		if (user == null) {
			user = new User(); // Do wee need to create new user?
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials.  Please enter valid credentials");
			logger.debug("->->->->In Valid Credentials");

		} else

		{
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");
			user.setIsOnline('Y');
			logger.debug("->->->->Valid Credentials");
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserID", user.getUsername());

			//friendDAO.setOnline(user.getId());
			userDAO.setOnline(user.getUsername());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		logger.debug("->->->->calling method logout");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		//friendDAO.setOffLine(loggedInUserID);
		userDAO.setOffLine(loggedInUserID);

		session.invalidate();

		user.setErrorCode("200");
		user.setErrorMessage("You have successfully logged");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	};

}
