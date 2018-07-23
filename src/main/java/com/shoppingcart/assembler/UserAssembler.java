package com.shoppingcart.assembler;

import com.shoppingcart.dto.UserDto;
import com.shoppingcart.model.User;

public class UserAssembler {

	public User writeDto(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}

}
