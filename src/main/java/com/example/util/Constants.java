package com.example.util;

public interface Constants extends DaoConstants {
	public static final String ADSS100 = "100";
	public static final String ADSS101 = "101";
	public static final String ADSS200 = "200";
	public static final String USER_NOT_NULL = "User name should not be null or empty";
	public static final String project_NOT_NULL = "Project name should not be null or empty";
	public static final String EMPLOYEE_ID_NOT_NULL = "EmployeeId should not be null or empty";
	public static final String PROJECT_NOT_VALID = "timehours should not be null or empty";
	public static final String EMAIL_ALREADY_EXISTS = "User already exists";
	public static final String Employee_ALREADY_EXISTS = " Check Your EmployeeId already exists";
	public static final String USER_NOT_EXISTS = "User not present please sign up";
	public static final String INVALID_USER = "Incorrect username Or password";
	public static final String INVALID_EMAIL = "Invalid Email";
	public static final String INVALID_INPUTS="Username should accept only alphabet";
	public static final String USERNAME_CHARACTER_LIMIT="Username should have at least min 2 characters and max 25 characters";
	public static final String COMPANY_CHARACTER_LIMIT="Company name should have at least min 2 characters and max 30 characters";
	public static final String VALID_PASSWORD="The password should have at least 6 characters min, 1 Upper case letter , 1 lower case letter,1 Number , 1 special characters";
	public static final String VALID_CONFIRM_PASSWORD="The confirm password should have at least 6 characters min, 1 Upper case letter , 1 lower case letter , 1 Number , 1 special characters";
	public static final String ALLOW_ALPHABETS_AND_DIGIT="Invalid EmployeeId";
	public static final String EMPLOYEE_ID_CHARACTER_LIMIT="EmployeeId should have at least min 2 characters and max 25 characters";
	public static final String INVALID_COMPANY_INPUTS="Invalid Company Name";
	public static final String COMPANY_NOT_NULL = "Company name should not be null or empty";
	public static final String EMAIL_NOT_NULL = "Email should not be null or empty";
	public static final String PASSWORD_NOT_NULL = "Password should not be null or empty";
	public static final String CONFIRM_PASSWORD_NOT_NULL = "Confirm password should not be null or empty";
	public static final String EMAIL_INVALID_INPUTS="Invalid Email";
	public static final String PENDING = "PENDING";
	public static final String FAIL = "FAIL";
	public static final String FAILED = "FAILED";
	public static final String SUCCESS = "SUCCESS";
	public static final String Deactivate_SUCCESS = "DEACTIVATE SUCCESSFULLY";
	public static final String Approval_Needed = "Need to get Approval From Admin";
	public static final String Approval = " Approval Success From Admin";
	public static final String Alredy_Exit = "This Project is Already Exist";
	public static final String NOT_Exit = "This Project is not Exist";
	public static final String UPDATE_SUCCESS = " UPDATE_SUCCESS";
	public static final String Uploaded_Successfully="Data Uploaded Successfully to GoogleDrive Excel Sheet";
	public static final String ALREADT_EXIST = "Servay id Alredy Exist";
	public static final String INCORRECT_CORPORATECODE = "Incorrect corporate code";
	public static final String INCORRECT_SHIPIMO = "Incorrect ship imo number";
	
	
	// JWT
	public static final String JWT_KEY = "jwt_key";
	public static final String JWT_EXPIRATION = "jwt_expiration";

	
	public static final String INVALID_USER1 = "Invalid User";
	public static final String REGISTERATION_SUCCESSFUL = "Registration successfully";
	public static final String LOGIN_SUCCESSFULL = "Login Successful";
	public static final String PASSWORD_DETAILS_UPDATED = "Password details updated";
	public static final String INCORRECT_PASSWORD = "The password should have at least 6 characters min, 1 Upper case letter , 1 lower case letter , 1 special characters, 1 numeric value";
	public static final String INCORRECT_CONFIRM_PASSWORD = "ConfirmPassword Should Not Be Null or Empty";
	public static final String Password_should_Be_Same="Password and confirm password should Be Same";
	public static final String Password_Should_Be_Unique="Password entered should be unique from the existing one";
	public static final String EMAIL_ID_IS_EMPTY = "Email Id should not empty";
	public static final String OTP_IS_EMPTY = "OTP cannot be null";
	public static final String INVALID = "Invalid";
	public static final String UNAUTHORIZED = "Unauthorized";
	
	public static final String SHIP_NAME_ALREADY_EXIST="ShipName already exists";
	public static final String INVALID_SHIP = "Invalid Ship";
	public static final String INVALID_SHIP_NAME = "Invalid ShipName";
	public static final String SHIP_ADDED="Ship details added successfully";
	public static final String SERVEY_NAME_ALREADY_EXIST="Survey name already exists";
	public static final String SERVEY_ADDED="Survey details added successfully";
	public static final String IMO_NOT_NULL = "IMO number should not be null or empty";
	public static final String INVALID_IMO_NUMBER="Invalid ship IMONumber";
	public static final String INVALID_SHIP_ID ="Invalide ShipId";
	public static final String INVALID_PORT_NAME ="Invalide PortName";
	public static final String INVALID_DISTANCE="Invalide Distance value";
	public static final String SHIP_ID_NOT_NULL="ShipId should not be null or empty";
	
	public static final String SURVEY_NAME_NOT_NULL="ServeyName should not be null or empty";
	public static final String SURVEY_NAME_LIMIT="Survey Name limit exceeded 30 characters";
	public static final String INVALID_SURVEY_NAME="Invalide SurveyName";
	public static final String PORT_NAME_LIMIT="Port Name limit exceeded 30 characters";
	
	public static final String Survey_Deleted="Survey Deleted Successfully";
	public static final String Survey_Details_Not_Found="Survey Details Not found";
	public static final String Survey_Details_Deleted_already="Survey Details Deleted already";
	
	public static final String Invalid_Verification_code="Incorrect Verification code. Enter Valid Verification code!";
	public static final String Ship_Imo_Null="Ship Imo Should Not Be Null or Empty";
	public static final String SurveyDetailsId_Null="Ship SurveyDetailsId Should Not BE  LessThen One or Null";
	public static final String FwdPortDraft_Null="FwdPortDraft Should Not Be Null or Empty";
	public static final String FwdStarboardDraft_Null="FwdStarboardDraft Should Not Be Null or Empty";
	public static final String MidPortDraft_Null="MidPortDraft Should Not Be Null or Empty";
	public static final String MidStarboardDraft_Null="MidStarboardDraft Should Not Be Null or Empty";
	public static final String AftPortDraft_Null="AftPortDraft Should Not Be Null or Empty";
	public static final String AftStarboardDraft_Null="AftStarboardDraft Should Not Be Null or Empty";
	public static final String Heel_Null="Heel Should Not Be Null or Empty";
	public static final String Trim_Null="Trim Should Not Be Null or Empty";
	public static final String CorrectedDraft_Null="CorrectedDraft Should Not Be Null or Empty";
	public static final String DisplacementDraft_Null="DisplacementDraft Should Not Be Null or Empty";
	public static final String NetDisplacement_Null="NetDisplacement_ Should Not Be Null or Empty";
	public static final String TotalCargo_Null="TotalCargo Should Not Be Null or Empty";
	public static final String ShipAuthSign_Null="ShipAuthSign Should Not Be Null or Empty";
	public static final String PortAuthSign_Null="PortAuthSign Should Not Be Null or Empty";
	
	
	
	public static final String ROLE_ID_IS_EMPTY = "Role Id should not empty";
	public static final String INVALID_ROLE_ID_ = "Role Id is invalid";
	public static final String CONFIRM_PASSWORD_NOT_MATCH = "Confirm password not match";
	public static final String Welcome="Successfully register as a Admin";
	public static final String welcome_As_User="Welcome to Beauto System";
}

