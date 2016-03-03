package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("customer")
public class Customer extends User {
	
	public String creditCard;
	
	public Customer(String email, String name, String password, String creditCard)
	{
		super(email, name, password);
		this.creditCard = creditCard;
	}
	
} 