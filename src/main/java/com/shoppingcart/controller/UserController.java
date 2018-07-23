package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.assembler.UserAssembler;
import com.shoppingcart.dto.UserDto;

import com.shoppingcart.exception.UserNotFoundException;
import com.shoppingcart.model.User;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.response.Response;
import com.shoppingcart.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;
	UserAssembler userAssembler = new UserAssembler();
	@Autowired
	private UserRepository userRepository;

	Response response = new Response();

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();

	}

	@GetMapping("/user/{email}")
	public User retrieveUser(@PathVariable String email) {
		User user = userRepository.findByEmail(email);

		if (user == null)
			throw new UserNotFoundException(email, "user not found");

		return user;
	}

	@PostMapping("/add")
	public Response add(@RequestBody UserDto userDto) {
		User user = userAssembler.writeDto(userDto);
		User user1 = userRepository.findByEmail(userDto.getEmail());

		if (user1 != null) {
			response.setCode(HttpStatus.CONFLICT.value());
			response.setMessage("Already  registered");
			return response;

		} else {
			user.setCount(0);
			userService.registerUser(user);
			response.setCode(HttpStatus.ACCEPTED.value());
			response.setMessage("Register successful");
			response.setResponse("You are allowed to Login Now");
			return response;
		}

	}

	@DeleteMapping("/remove")
	public Response remove(@RequestBody UserDto userDto) {
		User user = userAssembler.writeDto(userDto);
		userService.removeUser(user);
		response.setCode(HttpStatus.ACCEPTED.value());
		response.setMessage("Unregistered successful");
		response.setResponse("You need to register again to LogIn");
		return response;

	}

	@PostMapping("/resetPassword")
	public Response resetPassword(@RequestBody UserDto userDto) {
		User user = userAssembler.writeDto(userDto);
		User user1 = userRepository.findByEmail(userDto.getEmail());

		

		if (user1 == null) {
			response.setCode(HttpStatus.CONFLICT.value());
			response.setMessage("Email doesnot Exist");
			return response;

		} else {
			user1.setPassword(userDto.getPassword());
			userService.registerUser(user1);
			response.setCode(HttpStatus.ACCEPTED.value());
			response.setMessage("Password successfully changed");
			response.setResponse("Login with New credentials now");
			return response;
		}

	}

}
