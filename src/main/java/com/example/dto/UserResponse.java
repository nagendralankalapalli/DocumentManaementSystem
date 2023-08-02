package com.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserResponse {
	private String code;
	private String message;
	private String email;
	private int rollId;

}
