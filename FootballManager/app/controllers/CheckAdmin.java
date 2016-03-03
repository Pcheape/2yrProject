package controllers;

import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/* - Docs -
https://alexgaribay.com/2014/06/16/authentication-in-play-framework-using-java/
https://www.playframework.com/documentation/2.2.x/JavaActionsComposition
*/

public class CheckAdmin extends Action.Simple {
    
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        
        String id = ctx.session().get("email");
        if (id != null) {
            User u = User.getLoggedIn(id);
            if ("admin".equals(u.getUserType())) {
                return delegate.call(ctx);
            }    
        }
        //Result unauthorized = Results.unauthorized("unauthorized");
        return F.Promise.pure(redirect("/login"));
    }
}