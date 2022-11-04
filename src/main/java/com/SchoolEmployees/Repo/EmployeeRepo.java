package com.SchoolEmployees.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolEmployees.Domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository <Employee, Long> {

	List<Employee> findBySubject(String subject);
	
	List<Employee> findByFirstName(String firstName);	
	

	
	}
	
	


