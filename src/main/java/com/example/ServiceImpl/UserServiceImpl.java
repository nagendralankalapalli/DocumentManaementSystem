package com.example.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.Reposatory.UserRepository;
import com.example.Service.UserService;
import com.example.config.JwtUtil;
import com.example.dto.LoginRequestDto;
import com.example.dto.LoginResponseDto;
import com.example.dto.StatusDto;
import com.example.dto.UserDto;
import com.example.dto.UserResponse;
import com.example.model.User;
import com.example.util.Constants;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	 @Autowired
	    private JwtUtil jwtUtil;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	  
	   
	
	 public LoginResponseDto generateToken(LoginRequestDto request) throws Exception {
			LoginResponseDto response = new LoginResponseDto();
	        
	        User	user = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
	        if(user!=null) {
	        	try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        String token= jwtUtil.generateToken(request.getEmail());
	        response.setCode(Constants.ADSS200);
			response.setMessage(Constants.LOGIN_SUCCESSFULL);
			response.setToken(token);
			response.setEmployeeId(user.getEmployeeId());
			response.setEmailId(user.getEmail());
			response.setRoleId(user.getRollId());
			
	    }
	        else {
	        	 response.setCode(Constants.ADSS100);
	     		response.setMessage(Constants.USER_NOT_EXISTS);
	     		response.setToken(null);
	     		response.setEmailId(request.getEmail());
	     		
	        	
	        }
			return response;
		}
	
	
	

	@Override
	public ResponseEntity<Object> userRegistration(UserDto request) {
		User user = userRepository.getUserByEmail(request.getEmail());
		User user1 = userRepository.getUserByEmployeeId(request.getEmployeeId());
		UserResponse userresponse = new UserResponse();
		
		if (user != null) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.EMAIL_ALREADY_EXISTS);
			return ResponseEntity.ok(response);
		}
		if (user1 != null) {
			StatusDto response = new StatusDto();
			response.setStatus(Constants.FAIL);
			response.setCode(Constants.ADSS101);
			response.setMessage(Constants.Employee_ALREADY_EXISTS);
			return ResponseEntity.ok(response);

		}
		user=new User();
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
		userresponse.setCode(Constants.ADSS200);
		userresponse.setMessage(Constants.welcome_As_User);
		userresponse.setEmail(request.getEmail());
		userresponse.setRollId(request.getRollId());
		return ResponseEntity.ok(userresponse);
		

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
