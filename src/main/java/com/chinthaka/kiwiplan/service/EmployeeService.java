package com.chinthaka.kiwiplan.service;

import java.io.IOException;
import java.util.List;

import com.chinthaka.kiwiplan.model.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface EmployeeService {

	List<Employee> getEmployeeList() throws JsonParseException, JsonMappingException, IOException;
	
	boolean validateEmployeeList(List<Employee> empList) throws Exception;
	
	List<Employee> sortEmployeeList(List<Employee> empList);
	
	void displayManagementTree(List<Employee> empList);
}
