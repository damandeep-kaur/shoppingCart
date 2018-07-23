package com.shoppingcart.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.model.User;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmailAndPassword(String email, String password) {

		return userRepository.findByEmailAndPassword(email, password);

	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void removeUser(User user) {
		userRepository.delete(user);

	}

	@Override
	public void resetPassword(User user) {

		userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
