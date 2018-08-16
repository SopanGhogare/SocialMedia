package com.socialmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.dao.UserDAO;
import com.socialmedia.entity.User;
import com.socialmedia.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public User getUserById(int userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public synchronized boolean addUser(User user) {
		if (userDAO.userExists(user.getFullName(), user.getCountry())) {
			return false;
		} else {
			userDAO.addUser(user);
			return true;
		}
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);
	}
}
