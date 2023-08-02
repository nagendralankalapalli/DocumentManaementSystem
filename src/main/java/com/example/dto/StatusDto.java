package com.example.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(Include. NON_NULL)
public class StatusDto {
	String status;
	String code;
	String message;
	
}
