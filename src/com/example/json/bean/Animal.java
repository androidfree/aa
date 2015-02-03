package com.example.json.bean;

public class Animal {
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + "]";
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
}
