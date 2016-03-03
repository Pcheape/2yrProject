package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

		// Get the email (username) of the logged in user
		// null returned if no user logged in - onUnauthorized
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

		// If not logged in then open the login page
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginCtrl.login());
    }
    
}