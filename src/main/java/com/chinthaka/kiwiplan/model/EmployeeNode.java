package com.chinthaka.kiwiplan.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeNode {

	private Employee employee;

	private EmployeeNode managerNode;

	private List<EmployeeNode> employeeNodeList;

	private int level;

	public EmployeeNode(Employee employee) {
		super();
		this.employee = employee;
		employeeNodeList = new ArrayList<EmployeeNode>();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public EmployeeNode getManagerNode() {
		return managerNode;
	}

	public void setManagerNode(EmployeeNode managerNode) {
		this.managerNode = managerNode;
	}

	public List<EmployeeNode> getEmployeeNodeList() {
		return employeeNodeList;
	}

	public void setEmployeeNodeList(List<EmployeeNode> employeeNodeList) {
		this.employeeNodeList = employeeNodeList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void addEmployeeNode(EmployeeNode empNode) {
		if (empNode != null && !this.employeeNodeList.contains(empNode)) {
			this.employeeNodeList.add(empNode);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < level; x++) {
			sb.append("->");
		}
		sb.append(employee.getName() + "\n");

		if (!this.employeeNodeList.isEmpty()) {
			for (EmployeeNode emp : employeeNodeList) {
				sb.append(emp);
			}
		}
		return sb.toString();
	}

}
