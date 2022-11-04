package com.SchoolEmployees.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolEmployees.Domain.Employee;
import com.SchoolEmployees.Repo.EmployeeRepo;

@Service
public class EmployeeServices {
	
	
	@Autowired
	private EmployeeRepo repo;

	public EmployeeServices(EmployeeRepo repo) {
		super();
		this.repo = repo;
	
	}
	
	//get an employee
	public Employee getEmployee(long id) {
		return this.repo.findById(id).orElseThrow();
	}
	
	public List<Employee> getEmployee(String subject) {
		return this.repo.findBySubject(subject);
		
	}
	
	/*public List<Employee> getEmployees(String firstName) {
		return this.repo.findByFirstName(firstName);
	}*/
	
	public Employee createEmployee(Employee employee) {
		return this.repo.save(employee);
	}
	
	
	//get All Employees
	public List<Employee> getEmployees(){
		return this.repo.findAll();
	}
	//Adding a new employee
	public Employee udpateEmployee(Employee newEmployee, long id) {
		Employee found = repo.findById(id).get();
		
		found.setFirstName(newEmployee.getFirstName());
		found.setLastName(newEmployee.getLastName());
		found.setEmail(newEmployee.getEmail());
		found.setPhoneNumber(newEmployee.getPhoneNumber());
		found.setSubject(newEmployee.getSubject());
		
		return repo.save(found);
	}
	
	public boolean deleteEmployee(long id) {
		repo.deleteById(id);
		boolean deleted = !this.repo.existsById(id);
		return deleted;
		
	}
}

	
	
	
	
	
	
	

