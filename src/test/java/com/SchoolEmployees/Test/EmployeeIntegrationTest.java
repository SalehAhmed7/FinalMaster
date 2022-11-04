package com.SchoolEmployees.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.SchoolEmployees.Domain.Employee;
import com.SchoolEmployees.Services.EmployeeServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:employee-schema.sql", "classpath:employee-data.sql"})
public class EmployeeIntegrationTest {

	@MockBean
	private EmployeeServices employeeService;
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		
		Employee testEmployee = new Employee(null, "Saleh", "Ahmed", "Saleh@Gmail.com", "07982172938", "Maths");
		String testEmployeeAsJSON = this.mapper.writeValueAsString(testEmployee);
		
		System.out.println("Request Body: " + testEmployeeAsJSON);
		
		RequestBuilder req = post("/employee/create").contentType(MediaType.APPLICATION_JSON).content(testEmployeeAsJSON); 
		
		testEmployee.setId(2L);
		String responseBody = this.mapper.writeValueAsString(testEmployee);
		
		System.out.println("Response Body: " + responseBody);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json("{}");
		
		String whatevs = this.mvc.perform(req).andReturn().getResponse().getContentAsString();
		System.out.println("hello" + whatevs);
		
	} 
	
	/* @Test
	  void testCreate() throws Exception {
		 final Employee TEST_EMPLOYEE = new Employee(null, "Simpson", "green", "water@gmail.com", "0298373293", "maths");
	 
	        this.mvc.perform(post("/employee/create").accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(TEST_EMPLOYEE)))
	            .andExpect(status().isCreated()); */
	
	@Test
	void testGet() throws Exception {
		Employee testEmployee = new Employee(3L, "Saleh", "Ahmed", "Saleh@gmail.com", "07982172938", "Maths");
		this.mvc.perform(get("/employee/get")).andExpect(status().isOk()).andExpect(content().json(null));
	
	}
	
	@Test
	void testDelete() {
	
		
	}
	
	@Test
	void testUpdate() {
	
	
}
}