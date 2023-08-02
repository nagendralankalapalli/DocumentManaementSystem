package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="userDetails")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String employeeId;
	private String userName;
	private String designation;
	private String email;
	private String password;
	private int rollId;
	private String approveFlag;
	private String department;

	private String deleteFlag;

}
