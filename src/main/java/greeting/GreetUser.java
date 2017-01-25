package greeting;

import entity.UserEntity;
import security.Secured;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/greet")
@Secured
public class GreetUser {

    @Context
    SecurityContext securityContext;

    @PersistenceContext(unitName = "HOME_DS")
    EntityManager entityManager;

    @GET
    @Produces("application/json")
    public Response greetUser() {
        String username = securityContext.getUserPrincipal().getName();
//        UserEntity user = entityManager.find(UserEntity.class, username);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Welcome Back " + username + "\n");
//        stringBuilder.append("Your hashed password is " + user.getPassword() + "\n");
//        stringBuilder.append("It's salted with " + user.getSalt());
        return Response.ok(stringBuilder.toString()).build();
    }

}
