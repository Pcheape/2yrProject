package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("manager")
public class Manager extends User {
	
	public String department;
	
	public Manager(String email, String name, String password, String department)
	{
		super(email, name, password);
		this.department = department;
	}
	
} 