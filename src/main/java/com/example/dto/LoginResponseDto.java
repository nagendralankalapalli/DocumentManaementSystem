package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResponseDto {
	private String code;
	private String message;
	private String token;
	private int roleId;
	private String emailId;
	private String employeeId;
	
	
}
