 package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
	String responseCode;
	String responsemessage;
	public void setStatus(String fail) {
		// TODO Auto-generated method stub
		
	}

	

}
