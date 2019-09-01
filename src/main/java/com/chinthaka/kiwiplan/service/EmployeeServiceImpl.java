package com.chinthaka.kiwiplan.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.chinthaka.kiwiplan.model.Employee;
import com.chinthaka.kiwiplan.model.EmployeeNode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private Environment evn;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<Employee> getEmployeeList() throws JsonParseException, JsonMappingException, IOException {

		TypeReference<List<Employee>> empTypeRef = new TypeReference<List<Employee>>() {};
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(evn.getProperty("employee.data.file"));

		return objectMapper.readValue(inputStream, empTypeRef);
	}

	@Override
	public boolean validateEmployeeList(List<Employee> empList) throws Exception {

		long rootEmpCount = empList.stream().filter(emp -> emp.getManagerId() == 0).count();
		if (rootEmpCount == 0) {
			throw new Exception("There is no root manager defined");
		} else if (rootEmpCount > 1) {
			throw new Exception("There is more than one root manager");
		}

		return true;
	}

	@Override
	public List<Employee> sortEmployeeList(List<Employee> empList) {
		return empList.stream().sorted(Comparator.comparingInt(Employee::getManagerId)).collect(Collectors.toList());
	}

	@Override
	public void displayManagementTree(List<Employee> empList) {
		System.out.println("Here is the management tree:");

		printBoarder();

		// map employees to employee nodes
		Map<Integer, EmployeeNode> empTreeMap = empList.stream()
				.collect(Collectors.toMap(Employee::getId, emp -> new EmployeeNode(emp)));

		// build the employee tree
		EmployeeNode root = null;
		for (Employee emp : empList) {
			int managerId = emp.getManagerId();
			EmployeeNode empNode = empTreeMap.get(emp.getId());

			if (managerId > 0) {

				EmployeeNode managerNode = empTreeMap.get(managerId);

				if (managerNode != null) {

					// add manager's sub ordinate
					managerNode.addEmployeeNode(empNode);

					// set employee's manager
					empNode.setManagerNode(managerNode);
					empNode.setLevel(managerNode.getLevel() + 1);

					empTreeMap.put(managerId, managerNode);
					empTreeMap.put(empNode.getEmployee().getId(), empNode);
				}
			} else {
				root = empNode;
				root.setLevel(1);
			}
		}

		System.out.print(root);

		printBoarder();
	}

	private static void printBoarder() {
		System.out.println("============================");
	}
}
