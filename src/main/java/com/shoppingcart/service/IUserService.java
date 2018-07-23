package com.shoppingcart.service;

import com.shoppingcart.model.User;

public interface IUserService {

	User findByEmailAndPassword(String email, String password);

	void registerUser(User user);

	void removeUser(User user);

	void resetPassword(User user);

	User findByEmail(String email);
}
