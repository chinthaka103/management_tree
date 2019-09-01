package com.chinthaka.kiwiplan;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinthaka.kiwiplan.model.Employee;
import com.chinthaka.kiwiplan.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KiwiPlanAssessmentApplicationTests {
	
	@Autowired
	EmployeeService employeeService;

	@Test(expected=Exception.class)
	public void testListWithNoRootManager() throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1,"Tom", 1));
		empList.add(new Employee(2,"Peter", 2));
		
		employeeService.validateEmployeeList(empList);
	}
	
	@Test(expected=Exception.class)
	public void testListWithMoreThanOneRootManager() throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1,"Tom", 0));
		empList.add(new Employee(2,"Peter", 0));
		
		employeeService.validateEmployeeList(empList);
	}
	
	@Test()
	public void testListWithOneRootManager() throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee(1,"Tom", 0));
		empList.add(new Employee(2,"Peter", 1));
		
		assertEquals(true, employeeService.validateEmployeeList(empList));
	}

}
