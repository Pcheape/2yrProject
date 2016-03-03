package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Administrator extends User {

	
	public Administrator(String email, String name, String password)
	{
		super(email, name, password);
	}
	
} 