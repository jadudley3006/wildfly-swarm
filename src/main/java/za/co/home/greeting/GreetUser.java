package za.co.home.greeting;

import za.co.home.entity.UserEntity;
import za.co.home.security.Secured;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/greet")
@Secured
@ApplicationScoped
public class GreetUser {

    @Context
    SecurityContext securityContext;

    @PersistenceContext(unitName = "HOME_DS")
    EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response greetUser() {
        String username = securityContext.getUserPrincipal().getName();
//
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Welcome Back " + username + "\n\n");
        if (entityManager == null) {
            stringBuilder.append("entity manager is null :(");
        } else {
            TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.FIND_BY_USERNAME, UserEntity.class);
            query.setParameter("username", username);
            UserEntity user = query.getSingleResult();
            stringBuilder.append("Your hashed password is " + user.getPassword() + "\n");
            stringBuilder.append("It's salted with " + user.getSalt() + "\n");
            stringBuilder.append("\nno problems found.\n\n  :D");
        }
        return Response.ok(stringBuilder.toString()).build();
    }

}
