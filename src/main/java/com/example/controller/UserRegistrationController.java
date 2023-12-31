package com.example.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reposatory.UserRepository;
import com.example.Service.UserService;
import com.example.ServiceImpl.CustomUserDetailsService;
import com.example.config.JwtUtil;
import com.example.dto.LoginRequestDto;
import com.example.dto.LoginResponseDto;
import com.example.dto.ResponseMessage;
import com.example.dto.StatusDto;
import com.example.dto.UserDto;
import com.example.dto.UserResponse;
import com.example.model.User;
import com.example.util.Constants;



@RestController
public class UserRegistrationController {
	@Autowired
	private UserService userService;
	


	@PostMapping("/login")
    public LoginResponseDto generateToken(@RequestBody LoginRequestDto request) throws Exception {
		
	return	userService.generateToken(request);
       
	}
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody UserDto request) {
		
		StatusDto responseDto = new StatusDto();

		if (request.getUserName() == null || request.getUserName().equalsIgnoreCase("")
				|| request.getUserName().equalsIgnoreCase(" ")) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.USER_NOT_NULL);
			return ResponseEntity.ok(response);

		}
		if (null == request.getEmployeeId() || request.getEmployeeId().equalsIgnoreCase("")) {

			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.EMPLOYEE_ID_NOT_NULL);
			return ResponseEntity.ok(response);

		}
		if (null == request.getEmail() || request.getEmail().equalsIgnoreCase("")) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.EMAIL_NOT_NULL);
			return ResponseEntity.ok(response);
		}
		if (null == request.getPassword() || request.getPassword().equalsIgnoreCase("")
				|| request.getPassword().equalsIgnoreCase(" ")) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.PASSWORD_NOT_NULL);
			return ResponseEntity.ok(response);
		}
		if (null == request.getConfirmPassword() || request.getConfirmPassword().equalsIgnoreCase("")
				|| request.getConfirmPassword().equalsIgnoreCase(" ")) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.CONFIRM_PASSWORD_NOT_NULL);
			return ResponseEntity.ok(response);
		}
		if (!request.getConfirmPassword().equals(request.getPassword())) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.CONFIRM_PASSWORD_NOT_MATCH);
			return ResponseEntity.ok(response);

		}
		Pattern email = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher matchEmail = email.matcher(request.getEmail());
		if (!matchEmail.matches()) {

			responseDto.setCode(Constants.ADSS101);
			responseDto.setStatus(Constants.FAIL);
			responseDto.setMessage(Constants.EMAIL_INVALID_INPUTS);
			return ResponseEntity.ok(responseDto);
		}
		Pattern name = Pattern.compile("^[A-Za-z ]*$");
		Matcher matcher2 = name.matcher(request.getUserName());
		if (!matcher2.matches()) {

			responseDto.setCode(Constants.ADSS101);
			responseDto.setStatus(Constants.FAIL);
			responseDto.setMessage(Constants.INVALID_INPUTS);
			return ResponseEntity.ok(responseDto);
		}
		if (request.getUserName().length() >= 2 && request.getUserName().length() <= 25) {

		} else {
			responseDto.setStatus(Constants.FAIL);
			responseDto.setCode(Constants.ADSS101);
			responseDto.setMessage(Constants.USERNAME_CHARACTER_LIMIT);
			return ResponseEntity.ok(responseDto);
		}
		Pattern empId = Pattern.compile("^[a-zA-Z0-9]*$");
		Matcher matchEmp = empId.matcher(request.getEmployeeId());
		if (!matchEmp.matches()) {
			responseDto.setCode(Constants.ADSS101);
			responseDto.setStatus(Constants.FAIL);
			responseDto.setMessage(Constants.ALLOW_ALPHABETS_AND_DIGIT);
			return ResponseEntity.ok(responseDto);
		}

		Pattern pattern = Pattern
				.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$");
		Matcher matcher = pattern.matcher(request.getPassword());
		if (!matcher.matches()) {
			responseDto.setCode(Constants.ADSS101);
			responseDto.setStatus(Constants.FAIL);
			responseDto.setMessage(Constants.INCORRECT_PASSWORD);
			return ResponseEntity.ok(responseDto);
		}
		Matcher confirmPass = pattern.matcher(request.getConfirmPassword());
		if (!confirmPass.matches()) {
			responseDto.setCode(Constants.ADSS101);
			responseDto.setStatus(Constants.FAIL);
			responseDto.setMessage(Constants.INCORRECT_CONFIRM_PASSWORD);
			return ResponseEntity.ok(responseDto);
		}
		if (0 == request.getRollId()) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.ROLE_ID_IS_EMPTY);
			return ResponseEntity.ok(responseDto);
		}

		// Check if the rollId is within the allowed range
		if (request.getRollId() < 1 || request.getRollId() > 3) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.INVALID_ROLE_ID_);
			return ResponseEntity.ok(response);
		}
		if(request.getRollId()==1) {
			userService.userRegistration(request);
			StatusDto response = new StatusDto();
			response.setStatus(Constants.SUCCESS);
			response.setCode(Constants.ADSS200);
			response.setMessage(Constants.Welcome);
			return ResponseEntity.ok(response);
		}
		
		else {
			return	userService.userRegistration(request);
			
		}
		 
	}
	
	

	@PostMapping("/updateUser")
	public ResponseEntity<Object> userDetailseUpdate(@RequestBody UserDto request) {
		UserResponse userresponse = new UserResponse();

		userService.userDetailseUpdate(request);
		userresponse.setCode(Constants.ADSS200);
		userresponse.setMessage(Constants.UPDATE_SUCCESS);
		userresponse.setEmail(request.getEmail());
		userresponse.setRollId(request.getRollId());

		return ResponseEntity.ok(userresponse);

	}



	  
}
