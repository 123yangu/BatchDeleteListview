package com.ccy.entity;

public class Dog {
	private String name;
	private boolean isChecked;

	public Dog(String name, boolean isChecked) {
		super();
		this.name = name;
		this.isChecked = isChecked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
