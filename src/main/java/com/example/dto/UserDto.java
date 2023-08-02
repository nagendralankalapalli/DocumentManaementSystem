package com.example.dto;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class UserDto {
	private long userId;
	private String employeeId;
	private String userName;
	private String designation;
	private String email;
	private String password;
	private String confirmPassword;
	private int rollId;
	private String approveFlag;
	private String department;
	private String deleteFlag;

	

}
