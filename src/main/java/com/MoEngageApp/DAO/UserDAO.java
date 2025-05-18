package com.MoEngageApp.DAO;

import java.util.List;

import com.MoEngageApp.model.User;
	

public interface UserDAO {
		
	 int insertUser(User user);
	 List<User> getAllUser();
     User getUserById(int id);
     User getUserByEmail(String email);
     int deleteUserById(int id);
		
}


