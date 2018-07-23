package com.shoppingcart.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.assembler.UserAssembler;
import com.shoppingcart.dto.UserDto;
import com.shoppingcart.model.User;
import com.shoppingcart.response.Response;
import com.shoppingcart.service.IRedis;
import com.shoppingcart.service.IUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api1")
public class AuthenticationController {
	@Autowired
	private IUserService userService;

	@Autowired
	private IRedis redis;
	Response response = new Response();
	UserAssembler userAssembler = new UserAssembler();

	@PostMapping("/login")
	public Response login(@RequestBody UserDto userDto) {
		User user = userAssembler.writeDto(userDto);
		User user1 = userService.findByEmail(user.getEmail());

		if (user1 == null) {
			response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
			response.setMessage("Wrong Email Id");
			response.setResponse("Please Enter right credentials ");

		}

		else {
			Integer count = user1.getCount();
			if (count < 5) {
				if (user1.getPassword().equals(user.getPassword())) {
					try {
						Object token = Jwts.builder().setSubject("users/TzMUocMF4p").setExpiration(new Date(1300819380))
								.claim("name", "Robert Token Man").claim("scope", "self groups/admins")
								.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8")).compact();
						redis.setValue(user.getEmail(), token);
						user.setCount(0);
						userService.registerUser(user);
						response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
						response.setMessage("You have successfully logIn");
						response.setResponse("You can enter the products now ");

					} catch (UnsupportedEncodingException e) {

						e.printStackTrace();
					}
				} else {
					response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
					response.setMessage("wrong password");
					response.setResponse("try again");
					count++;
					user1.setCount(count);
					userService.registerUser(user1);
				}
			} else {
				response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
				response.setMessage("Blocked");
				response.setResponse("Blocked ");

			}
		}
		return response;

	}

	@PostMapping("/logout")
	public Response logout(@RequestBody UserDto userDto) {
		User user = userAssembler.writeDto(userDto);
		redis.deleteValue(user.getEmail());

		response.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		response.setMessage("You have successfully logOut");
		response.setResponse("You have to logIn again to enter the products ");
		return response;

	}

}
