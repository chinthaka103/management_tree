package com.chinthaka.kiwiplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chinthaka.kiwiplan.model.Employee;
import com.chinthaka.kiwiplan.service.EmployeeService;

@SpringBootApplication
public class KiwiPlanAssessmentApplication implements CommandLineRunner {
	
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(KiwiPlanAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Employee> empList = employeeService.getEmployeeList();
		
		employeeService.validateEmployeeList(empList);
		
		empList = employeeService.sortEmployeeList(empList);
		
		employeeService.displayManagementTree(empList);

		
	}

}
