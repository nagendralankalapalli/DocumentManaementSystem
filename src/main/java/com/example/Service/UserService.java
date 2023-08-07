package com.example.Service;

import org.springframework.http.ResponseEntity;

import com.example.dto.LoginRequestDto;
import com.example.dto.LoginResponseDto;
import com.example.dto.UserDto;

public interface UserService {
	public ResponseEntity<Object> userRegistration(UserDto request);
	public void userDetailseUpdate(UserDto request);
	public void loginCheck(String email, String password);
	public LoginResponseDto generateToken(LoginRequestDto request) throws Exception;
}
