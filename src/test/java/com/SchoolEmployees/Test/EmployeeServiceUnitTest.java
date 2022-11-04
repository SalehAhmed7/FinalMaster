package com.SchoolEmployees.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.SchoolEmployees.Domain.Employee;
import com.SchoolEmployees.Repo.EmployeeRepo;
import com.SchoolEmployees.Services.EmployeeServices;

@SpringBootTest
public class EmployeeServiceUnitTest {
	
	
	@Autowired
	private EmployeeServices service;
	
	
	
	@MockBean
	private EmployeeRepo repo;
	
	  					

	
	
	@Test
	void testCreate() {
		//GIVEN
		final Employee TEST_EMPLOYEE = new Employee(null, "Bukhari", "Huraira", "Bull@gmail.com", "07123458934", "Physics");
		final Employee TEST_SAVED_EMPLOYEE = new Employee(1L, "Bukhari", "Huraira", "Bull@gmail.com", "07123458934", "Physics");
		//WHEN
		Mockito.when(this.repo.save(TEST_EMPLOYEE))
		.thenReturn(TEST_SAVED_EMPLOYEE);
		//THEN
		Assertions.assertThat(this.service.createEmployee(TEST_EMPLOYEE)).isEqualTo(TEST_SAVED_EMPLOYEE);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_EMPLOYEE);
		
		}
	
	
	@Test
	void testGetEmployee() {
		final Employee TEST_EMPLOYEE = new Employee(null, "The", "Man", "Theman@Gmail.com", "0292838849", "Geometry");
		Long id = 2L;
		
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(new Employee(id, "The", "Man", "Theman@Gmail.com", 
				"0292838849", "Geometry")));
		
		//THEN
		assertEquals(TEST_EMPLOYEE, this.service.getEmployee(id));
		
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		
		
		
	}
	@Test
	void testUpdate() {
		
		Employee testEmployee = new Employee(7L, "New", "Person", "new@gmail.com", "0798374632", "Maths");
		
		Mockito.when(this.repo.findById(7L)).thenReturn(Optional.of(new Employee(7L, "Saleh", "Ahmed", "SalehLOL@gmail.com", "08948384", "PE")));
		
		Mockito.when(this.repo.save(testEmployee)).thenReturn(testEmployee);
		
		assertEquals(testEmployee, this.service.udpateEmployee(testEmployee, 7L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(7L);
	
		
	}
	
	@Test
	void testDelete() {
		
		Mockito.when(this.repo.existsById(5L)).thenReturn(false);
		
		assertTrue(this.service.deleteEmployee(5L));
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(5L);
		
		
	}
	

		
		
	}


