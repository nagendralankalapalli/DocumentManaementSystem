package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequestDto {
	private String email;
	private String password;
}
