package com.example.Reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User getUserByEmail(String email);
	public User getUserByEmployeeId(String employeeId);
	public User findByEmailAndPassword(String email,String password);
//	public List<User> findByEmployeeId(String employeeId);
//	public List<User>findByDepartment(String department);
	

}
