package com.chinthaka.kiwiplan.model;

public class Employee {

	private int id;
	private String name;
	private int managerId;

	public Employee() {
		super();
	}

	public Employee(int id, String name, int managerId) {
		super();
		this.id = id;
		this.name = name;
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
}
