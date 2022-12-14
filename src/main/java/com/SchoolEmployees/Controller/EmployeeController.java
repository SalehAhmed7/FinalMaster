package com.SchoolEmployees.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolEmployees.Domain.Employee;
import com.SchoolEmployees.Services.EmployeeServices;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private EmployeeServices service;
	
	public EmployeeController(EmployeeServices service) {
		super();
		this.service = service;
		
	}
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee newEmployee) {
		return this.service.createEmployee(newEmployee);
	}
	
	@GetMapping("/get")
	public List<Employee> getAllEmployees(){
		return this.service.getEmployees();
		
	}

/*	@GetMapping("/firstName/{firstName}")
public List<Employee> getAllEmployeeBy(@PathVariable("firstName")String firstName) {
    return this.service.getEmployees(firstName);
	}*/

	@GetMapping("/lastName/{lastName}")
public List<Employee> getAllEmployeeById(@PathVariable("lastName")String lastName) {
    return this.service.getEmployee(lastName);
	}
	
	
	@GetMapping("/getBySubject/{subject}")
	
	public List<Employee> getAllEmployeeBySubject(@PathVariable("subject")String subject) {
		return this.service.getEmployee(subject);
	}
	
	@GetMapping("/{id}")
	public Employee getAllEmployeeById(@PathVariable("id") Long id) {
		return this.service.getEmployee(id);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteAllEmployeeById(@PathVariable("id") Long id) {
		 service.deleteEmployee(id);
	}
	
	
	




	
		
	}
	
