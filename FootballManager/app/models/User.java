package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

//https://www.playframework.com/documentation/2.2.x/JavaGuide4

// Product entity managed by Ebean
@Entity
@Table(name = "user")  
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name = "userType") 
@DiscriminatorValue("user") 

public class User extends Model {

    //@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) 
	//public Long id;
	
	//@Constraints.Required
    @Id
    public String email;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;


    // Default constructor
    public User() {
    }
    // Constructor to initialise object
    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

		//Generic query helper for entity User with unique id String
    public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);

		//Find all Products in the database
		public static List<User> findAll() {
			return User.find.all();
		}
		// Static method to authenticate based on username and password
		// Returns user object if found, otherwise NULL
		public	static User authenticate(String email, String password) {
			// If found return the user object with matching username and password
			return find.where().eq("email", email).eq("password", password).findUnique();
		}

		// Check if a user is logged in (by id)
		public static User getLoggedIn(String id) {
			if (id == null)
					return null;
			else
				// Find user by id and return object
				return find.byId(id);
		}
		
		// Get the user type - from the discriminator value
		// http://stackoverflow.com/questions/3005383/how-to-get-the-discriminatorvalue-at-run-time
		// http://stackoverflow.com/questions/541749/how-to-determine-an-objects-class-in-java
		@Transient
		public String getUserType(){
			DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );
			return val == null ? null : val.value();
		}
        
}

