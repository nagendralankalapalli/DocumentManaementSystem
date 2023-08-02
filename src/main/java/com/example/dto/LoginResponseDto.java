package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResponseDto {
	private String code;
	private String message;
	private int roleId;
	private String employeeId;
	private String designation;
	private String username;
	private String createdBy;
	
}
