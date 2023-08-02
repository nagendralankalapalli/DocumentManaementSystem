package com.example.Service;

import com.example.dto.UserDto;

public interface UserService {
	public void userRegistration(UserDto request);
	public void userDetailseUpdate(UserDto request);
	public void loginCheck(String email, String password);
}
