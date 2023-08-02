package com.example.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reposatory.UserRepository;
import com.example.Service.UserService;
import com.example.dto.LoginRequestDto;
import com.example.dto.UserDto;
import com.example.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void userRegistration(UserDto request) {
		User user = new User();
		user.setEmployeeId(request.getEmployeeId());
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setDesignation(request.getDesignation());
		user.setRollId(request.getRollId());
		
		user.setDeleteFlag("Flase");
		
		user.setDepartment(request.getDepartment());

		if (request.getPassword().equals(request.getConfirmPassword())) {
			// model.setPassword(employee.getPassword());
			user.setPassword(request.getPassword());

		}

		userRepository.save(user);

	}

	@Override
	public void userDetailseUpdate(UserDto request) {
		User user = userRepository.getById(request.getUserId());

		user.setEmployeeId(request.getEmployeeId());
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setDesignation(request.getDesignation());
		user.setRollId(request.getRollId());

		if (request.getPassword().equals(request.getConfirmPassword())) {
			// model.setPassword(employee.getPassword());
			user.setPassword(request.getPassword());

		}

		if (user != null) {
			userRepository.save(user);

		}
	}


	
	
	
	@Override
	public void loginCheck(String email, String password) {
		User user = new User();
		user = userRepository.findByEmailAndPassword(email, password);
		System.out.println(user);
		LoginRequestDto request = new LoginRequestDto();
		if (user != null) {
			request.setEmail(user.getEmail());
			request.setPassword(user.getPassword());
		}
	}

	
}
