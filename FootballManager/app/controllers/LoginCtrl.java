package controllers;

import models.Login;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

// Import required classes
// Import models

public class LoginCtrl extends Controller {

    public Result login() {
				// Pass a login form to the login view and render
		    return ok(login.render(Form.form(Login.class), User.getLoggedIn(session().get("email"))));
    }

		// Process the user login form
		public Result authenticate() {
			// Bind form instance to the values submitted from the form  
			Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
 
			// Check for errors
			// Uses the validate method defined in the Login class
		  if (loginForm.hasErrors()) {
			  // If errors, show the form again
		      return badRequest(login.render(loginForm, User.getLoggedIn(session().get("email"))));
		  } 
			else {
					// User Logged in sucessfully
					// Clear the existing session
		      session().clear();
					// Store the logged in email in the session
		      session("email", loginForm.get().email);
			 
             // Check user type
             User u = User.getLoggedIn(loginForm.get().email);
             // If admin - go to admin section
             if (u != null && "admin".equals(u.getUserType())) {
                 return redirect(routes.AdminProductCtrl.index());
             }
                
             // Return to home page
		      return redirect(routes.Application.index());
		  }
		}	

		public Result logout() {
				session().clear();
				flash("success", "You've been logged out");
				return redirect(
				    routes.LoginCtrl.login()
				);
		}

}
